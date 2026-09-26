package net.sssubtlety.chicken_nerf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public final class ChickenNerf {
	public static final String NAMESPACE = "chicken_nerf";
	public static final Logger LOGGER = LogManager.getLogger();

	private ChickenNerf() {}

	@SuppressWarnings("UnusedReturnValue")
	public static <E extends Entity> int spawnEntities(
		EntityType<E> entityType, double x, double y, double z, float yaw, Level level, Consumer<E> postCreation
	) {
		final int entityCount = generateEntitySpawnCount(level.getRandom());

		for (int i = 0; i < entityCount; i++) {
			final E entity = entityType.create(level, EntitySpawnReason.TRIGGERED);
			if (entity == null) {
				break;
			}
			postCreation.accept(entity);
			entity.snapTo(x, y, z, yaw, 0.0F);
			level.addFreshEntity(entity);
		}

		return entityCount;
	}

	/**
	 * Single-trial hatch (DarthJahus 1.2.1-chance): 0 or 1 chick, no geometric loop.
	 */
	public static int generateEntitySpawnCount(RandomSource random) {
		return random.nextFloat() < FeatureControl.getEggSuccessChance() ? 1 : 0;
	}
}
