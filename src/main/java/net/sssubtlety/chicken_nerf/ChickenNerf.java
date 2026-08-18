package net.sssubtlety.chicken_nerf;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

public class ChickenNerf {
	public static final String NAMESPACE = "chicken_nerf";
	public static final Logger LOGGER = LogManager.getLogger();

	@SuppressWarnings("UnusedReturnValue")
	public static <E extends Entity> int spawnEntities(
		EntityType<E> entityType, double x, double y, double z, float yaw, World world, Consumer<E> postCreation
	) {
		final int entityCount = generateEntitySpawnCount(world.random);

		for (int i = 0; i < entityCount; i++) {
			final E entity = entityType.create(world, SpawnReason.TRIGGERED);
			if (entity == null) {
                break;
            } else {
				postCreation.accept(entity);
				entity.refreshPositionAndAngles(x, y, z, yaw, 0.0F);
				world.spawnEntity(entity);
			}
		}

		return entityCount;
	}

	public static int generateEntitySpawnCount(RandomGenerator random) {
		return random.nextFloat() < FeatureControl.getEggSuccessChance() ? 1 : 0;
	}
}
