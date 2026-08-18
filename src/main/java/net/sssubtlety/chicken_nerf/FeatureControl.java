package net.sssubtlety.chicken_nerf;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
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

public class FeatureControl {
    private static Config CONFIG_INSTANCE;

    private static final Map<Class<? extends AnimalEntity>, Function<RandomGenerator, ItemConvertible>>
        EGG_SELECTORS_BY_ANIMAL = new HashMap<>();

    static {
        mapAnimalEggItem(ChickenEntity.class, Items.EGG);
        // On initialise la config directement via AutoConfig (Cloth Config est requis pour l'UI)
        try {
            CONFIG_INSTANCE = AutoConfig.register(Config.class, GsonConfigSerializer::new).getConfig();
        } catch (Exception e) {
            CONFIG_INSTANCE = null;
        }
    }

    public static void init() { }

    public static int generateEggCount(RandomGenerator random) {
        int min = (CONFIG_INSTANCE != null) ? CONFIG_INSTANCE.minLaidEggs : 1;
        int max = (CONFIG_INSTANCE != null) ? CONFIG_INSTANCE.maxLaidEggs : 3;
        return MathHelper.nextInt(random, min, max);
    }

    public static double getEggSuccessChance() {
        return (CONFIG_INSTANCE != null) ? CONFIG_INSTANCE.eggSuccessChance : 0.5;
    }

    @Nullable
    public static Item getEggForAnimal(Class<?> animalClass, RandomGenerator random) {
        final Function<RandomGenerator, ItemConvertible> eggFxn = EGG_SELECTORS_BY_ANIMAL.get(animalClass);
        return eggFxn == null ? null : eggFxn.apply(random).asItem();
    }

    public static void mapAnimalEggItem(Class<? extends AnimalEntity> animalClass, Item eggItem) {
        EGG_SELECTORS_BY_ANIMAL.put(animalClass, (random) -> eggItem);
    }

    public static boolean isConfigLoaded() {
        return CONFIG_INSTANCE != null;
    }
}
