package net.sssubtlety.chicken_nerf;

import net.sssubtlety.chicken_nerf.config.SimpleConfig;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class FeatureControl {
	private static final Map<Class<? extends Animal>, Supplier<Item>> EGG_BY_ANIMAL = new HashMap<>();

	static {
		EGG_BY_ANIMAL.put(Chicken.class, () -> Items.EGG);
	}

	private FeatureControl() {}

	public static void init() {
		SimpleConfig.get();
	}

	public static int generateEggCount(RandomSource random) {
		SimpleConfig c = SimpleConfig.get();
		return Mth.nextInt(random, c.minLaidEggs, c.maxLaidEggs);
	}

	public static double getEggSuccessChance() {
		return SimpleConfig.get().eggSuccessChance;
	}

	@Nullable
	public static Item getEggForAnimal(Class<?> animalClass) {
		Supplier<Item> s = EGG_BY_ANIMAL.get(animalClass);
		return s == null ? null : s.get();
	}

	public static void mapAnimalEggItem(Class<? extends Animal> animalClass, Item eggItem) {
		EGG_BY_ANIMAL.put(animalClass, () -> eggItem);
	}
}
