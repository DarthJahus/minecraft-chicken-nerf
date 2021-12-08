package net.sssubtlety.chicken_nerf;

import de.guntram.mcmod.crowdintranslate.CrowdinTranslate;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChickenNerf {
	public static final String NAMESPACE = "chicken_nerf";
	public static final Logger LOGGER = LogManager.getLogger();

	public static class Init implements ModInitializer {
		@Override
		public void onInitialize () {
			ChickenNerfConfig.init();
		}
	}

	public static class ClientInit implements ClientModInitializer {
		@Override
		public void onInitializeClient() {
			CrowdinTranslate.downloadTranslations("chicken-nerf", NAMESPACE);
		}
	}
}
