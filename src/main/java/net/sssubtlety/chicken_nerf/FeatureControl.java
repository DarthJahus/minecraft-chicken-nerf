package net.sssubtlety.chicken_nerf;

import dev.itsmeow.betteranimalsplus.api.ModEventBus;
import dev.itsmeow.betteranimalsplus.common.entity.EntityGoose;
import dev.itsmeow.betteranimalsplus.common.entity.EntityPheasant;
import dev.itsmeow.betteranimalsplus.common.entity.EntityTurkey;
import dev.itsmeow.betteranimalsplus.init.ModItems;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.api.metadata.version.VersionPredicate;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.sssubtlety.chicken_nerf.ChickenNerf.getNumEntitiesToSpawn;

public class FeatureControl {
    private static final @Nullable Config CONFIG_INSTANCE;
    private static final Map<Class<? extends AnimalEntity>, Function<Random, ItemConvertible>> ANIMAL2EGG_FXN_MAP = new HashMap<>();

    static {
        mapAnimalToEgg(ChickenEntity.class, Items.EGG);

        boolean shouldLoadConfig = false;
        final Optional<ModContainer> optModContainer = FabricLoader.getInstance().getModContainer("cloth-config");
        if (optModContainer.isPresent()) {
            try {
                shouldLoadConfig = VersionPredicate.parse(">=6.1.48").test(optModContainer.get().getMetadata().getVersion());
            } catch (VersionParsingException e) {
                e.printStackTrace();
            }
        }

        CONFIG_INSTANCE = shouldLoadConfig ?
                AutoConfig.register(Config.class, GsonConfigSerializer::new).getConfig() : null;

        final Optional<ModContainer> optBAPContainer = FabricLoader.getInstance().getModContainer("betteranimalsplus");
        if (optBAPContainer.isPresent()) {
            try {
                if (VersionPredicate.parse(">=1.18.1-11.0.4").test(optBAPContainer.get().getMetadata().getVersion())) {
                    // cancel BAP random egg laying
                    ModEventBus.LayEggTickEvent.subscribe(event -> event.setCanceled(true));
                    // ensure BAP eggs always try to spawn babies
                    ModEventBus.ShouldEggSpawnEntitiesEvent.subscribe(event -> event.setShouldSpawnEntities(true));
                    // modify the number of baby entities from a BAP egg
                    ModEventBus.EggThrowSpawnCountEvent.subscribe(event ->
                            event.setSpawnCount(getNumEntitiesToSpawn(event.getEntity().world.random)));
                    // map BAP egg-layers to their eggs
                    mapAnimalToEggSupplier(EntityGoose.class, (random) ->
                            random.nextInt(128) == 0 ? ModItems.GOLDEN_GOOSE_EGG.get() : ModItems.GOOSE_EGG.get());
                    mapAnimalToEgg(EntityPheasant.class, ModItems.PHEASANT_EGG.get());
                    mapAnimalToEgg(EntityTurkey.class, ModItems.TURKEY_EGG.get());

                }
            } catch (VersionParsingException e) {
                e.printStackTrace();
            }
        }
    }

    public interface Defaults {
        int minLaidEggs = 1;
        int maxLaidEggs = 3;
        double averageChickensFromEgg = 0.6;
        boolean enableTranslationFetching = true;
    }

    private static final double defaultEggSuccessChance = Defaults.averageChickensFromEgg / (Defaults.averageChickensFromEgg + 1);

    public static void init() { }

    public static @Nullable Item getEggForAnimal(Class<?> animalClass, Random random) {
        final Function<Random, ItemConvertible> eggFxn = ANIMAL2EGG_FXN_MAP.get(animalClass);
        return eggFxn == null ? null : eggFxn.apply(random).asItem();
    }

    public static boolean mapAnimalToEgg(Class<? extends AnimalEntity> animalClass, Item eggItem) {
        return mapAnimalToEggSupplier(animalClass, (random) -> eggItem);
    }

    public static boolean mapAnimalToEggSupplier(Class<? extends AnimalEntity> animalClass, Function<Random, ItemConvertible> eggFxn) {
        return ANIMAL2EGG_FXN_MAP.putIfAbsent(animalClass, eggFxn) == null;
    }

    public static boolean isConfigLoaded() {
        return CONFIG_INSTANCE != null;
    }

    public static int getMinLaidEggs() {
        return CONFIG_INSTANCE == null ? Defaults.minLaidEggs : CONFIG_INSTANCE.minLaidEggs;
    }

    public static int getMaxLaidEggs() {
        return CONFIG_INSTANCE == null ? Defaults.maxLaidEggs : CONFIG_INSTANCE.maxLaidEggs;
    }

    public static double getEggSuccessChance() {
        return  CONFIG_INSTANCE == null ? defaultEggSuccessChance :
                CONFIG_INSTANCE.averageChickensFromEgg / (CONFIG_INSTANCE.averageChickensFromEgg + 1);
    }

    public static boolean isTranslationFetchingEnabled() {
        return CONFIG_INSTANCE == null ? Defaults.enableTranslationFetching : CONFIG_INSTANCE.enableTranslationFetching;
    }
}
