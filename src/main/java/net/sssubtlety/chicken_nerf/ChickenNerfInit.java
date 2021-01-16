package net.sssubtlety.chicken_nerf;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class ChickenNerfInit implements ModInitializer {
	public static String MOD_ID = "chicken_nerf";
	private static ChickenNerfConfig CONFIG;
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		//Register config class
		AutoConfig.register(ChickenNerfConfig.class, GsonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(ChickenNerfConfig.class).getConfig();
	}

	public static ChickenNerfConfig getCONFIG() {
		return CONFIG;
	}
}
