package net.sssubtlety.chicken_nerf;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.function.Consumer;

public class ChickenNerf {
	public static final String NAMESPACE = "chicken_nerf";
	public static final Logger LOGGER = LogManager.getLogger();

	@SuppressWarnings("UnusedReturnValue")
	public static <E extends Entity> int spawnEntities(EntityType<E> entityType, double x, double y, double z, float yaw, World world, Consumer<E> postCreation) {
		int numEntities = getNumEntitiesToSpawn(world.random);

		for (int i = 0; i < numEntities; i++) {
			E entity = entityType.create(world);
			if(entity == null) break;
			else {
				postCreation.accept(entity);
				entity.refreshPositionAndAngles(x, y, z, yaw, 0.0F);
				world.spawnEntity(entity);
			}
		}
		return numEntities;
	}

	public static int getNumEntitiesToSpawn(RandomGenerator random) {
		int numEntities = 0;
		while(random.nextFloat() < FeatureControl.getEggSuccessChance()) {
			numEntities++;
		}

		return numEntities;
	}

	public static ItemStack getLayedEggStack(Item eggItem, RandomGenerator random) {
		return new ItemStack(eggItem, MathHelper.nextInt(random, FeatureControl.getMinLaidEggs(), FeatureControl.getMaxLaidEggs()));
	}

}
