package net.sssubtlety.chicken_nerf;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import de.guntram.mcmod.crowdintranslate.CrowdinTranslate;
import dev.itsmeow.betteranimalsplus.api.ModEventBus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.fabricmc.loader.api.metadata.version.VersionPredicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.function.Consumer;

import static net.sssubtlety.chicken_nerf.FeatureControl.isTranslationFetchingEnabled;

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

	public static int getNumEntitiesToSpawn(Random random) {
		int numEntities = 0;
		while(random.nextFloat() < FeatureControl.getEggSuccessChance()) {
			numEntities++;
		}

		LOGGER.error("numEntities: " + numEntities);
		return numEntities;
	}

	public static ItemStack getLayedEggStack(Item eggItem, Random random) {
		return new ItemStack(eggItem, MathHelper.nextInt(random, FeatureControl.getMinLaidEggs(), FeatureControl.getMaxLaidEggs()));
	}

	public static class Init implements ModInitializer {
		@Override
		public void onInitialize () {
			// reference FeatureControl class so it consistently loads at this point
			FeatureControl.init();
		}
	}

	public static class ClientInit implements ClientModInitializer {
		@Override
		public void onInitializeClient() {
			if (isTranslationFetchingEnabled())
				CrowdinTranslate.downloadTranslations("chicken-nerf", NAMESPACE);
		}
	}

	public static class PreLaunch implements PreLaunchEntrypoint {
		@Override
		public void onPreLaunch() {
			MixinExtrasBootstrap.init();
		}
	}
}
