package net.sssubtlety.chicken_nerf;

//import dev.itsmeow.betteranimalsplus.api.ModEventBus;
//import dev.itsmeow.betteranimalsplus.common.entity.EntityGoose;
//import dev.itsmeow.betteranimalsplus.common.entity.EntityPheasant;
//import dev.itsmeow.betteranimalsplus.common.entity.EntityTurkey;
//import dev.itsmeow.betteranimalsplus.init.ModItems;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.version.VersionPredicate;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.random.RandomGenerator;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static net.sssubtlety.chicken_nerf.ChickenNerf.LOGGER;

public class FeatureControl {
    private static final @Nullable Config CONFIG_INSTANCE;

    private static final Map<Class<? extends AnimalEntity>, Function<RandomGenerator, ItemConvertible>>
        EGG_SELECTORS_BY_ANIMAL = new HashMap<>();

    static {
        mapAnimalEggItem(ChickenEntity.class, Items.EGG);

        final boolean shouldLoadConfig = FabricLoader.getInstance().getModContainer("cloth-config")
            .map(ModContainer::getMetadata)
            .map(ModMetadata::getVersion)
            .filter(version -> {
                try {
                    return VersionPredicate.parse(">=7.0.72").test(version);
                } catch (VersionParsingException e) {
                    LOGGER.error("Failed to parse version predicate", e);

                    return false;
                }
            })
            .isPresent();

        CONFIG_INSTANCE = shouldLoadConfig ?
            AutoConfig.register(Config.class, GsonConfigSerializer::new).getConfig() :
            null;

//        final Optional<ModContainer> optBAPContainer = FabricLoader.getInstance().getModContainer("betteranimalsplus");
//        if (optBAPContainer.isPresent()) {
//            try {
//                if (VersionPredicate.parse(">=1.19-11.0.6").test(optBAPContainer.get().getMetadata().getVersion())) {
//                    // cancel BAP random egg laying
//                    ModEventBus.LayEggTickEvent.subscribe(event -> event.setCanceled(true));
//                    // ensure BAP eggs always try to spawn babies
//                    ModEventBus.ShouldEggSpawnEntitiesEvent.subscribe(event -> event.setShouldSpawnEntities(true));
//                    // modify the number of baby entities from a BAP egg
//                    ModEventBus.EggThrowSpawnCountEvent.subscribe(event ->
//                            event.setSpawnCount(getNumEntitiesToSpawn(event.getEntity().world.random)));
//
//                    // map BAP egg-layers to their eggs
//                    mapAnimalToEggFxn(EntityGoose.class, (random) -> random.nextInt(128) == 0 ?
//                            ModItems.GOLDEN_GOOSE_EGG.get() : ModItems.GOOSE_EGG.get());
//                    mapAnimalToEggFxn(EntityPheasant.class, (random) -> ModItems.PHEASANT_EGG.get());
//                    mapAnimalToEggFxn(EntityTurkey.class, (random) -> ModItems.TURKEY_EGG.get());
//                }
//            } catch (VersionParsingException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public static int generateEggCount(RandomGenerator random) {
        final int min, max;
        if (CONFIG_INSTANCE == null) {
            min = Defaults.MIN_LAID_EGGS;
            max = Defaults.MAX_LAID_EGGS;
        } else {
            min = CONFIG_INSTANCE.minLaidEggs;
            max = CONFIG_INSTANCE.maxLaidEggs;
        }

        return MathHelper.nextInt(random, min, max);
    }

    public interface Defaults {
        int MIN_LAID_EGGS = 1;
        int MAX_LAID_EGGS = 3;
        double AVERAGE_CHICKENS_FROM_EGG = 0.6;
    }

    private static final double DEFAULT_EGG_SUCCESS_CHANCE =
        calculateEggSuccessChance(Defaults.AVERAGE_CHICKENS_FROM_EGG);

    public static void init() { }

    public static @Nullable Item getEggForAnimal(Class<?> animalClass, RandomGenerator random) {
        final Function<RandomGenerator, ItemConvertible> eggFxn = EGG_SELECTORS_BY_ANIMAL.get(animalClass);
        return eggFxn == null ? null : eggFxn.apply(random).asItem();
    }

    @SuppressWarnings("UnusedReturnValue")
    public static boolean mapAnimalEggItem(Class<? extends AnimalEntity> animalClass, Item eggItem) {
        return mapAnimalEggSelector(animalClass, (random) -> eggItem);
    }

    public static boolean mapAnimalEggSelector(
        Class<? extends AnimalEntity> animalClass,
        Function<RandomGenerator, ItemConvertible> eggSelector
    ) {
        return EGG_SELECTORS_BY_ANIMAL.putIfAbsent(animalClass, eggSelector) == null;
    }

    public static boolean isConfigLoaded() {
        return CONFIG_INSTANCE != null;
    }

    public static int getMinLaidEggs() {
        return CONFIG_INSTANCE == null ? Defaults.MIN_LAID_EGGS : CONFIG_INSTANCE.minLaidEggs;
    }

    public static int getMaxLaidEggs() {
        return CONFIG_INSTANCE == null ? Defaults.MAX_LAID_EGGS : CONFIG_INSTANCE.maxLaidEggs;
    }

    public static double getEggSuccessChance() {
        return  CONFIG_INSTANCE == null ? DEFAULT_EGG_SUCCESS_CHANCE :
            calculateEggSuccessChance(CONFIG_INSTANCE.averageChickensFromEgg);
    }

    private static double calculateEggSuccessChance(double averageChickensFromEgg) {
        return averageChickensFromEgg / (averageChickensFromEgg + 1);
    }
}
