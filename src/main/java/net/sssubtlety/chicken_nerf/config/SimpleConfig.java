package net.sssubtlety.chicken_nerf.config;

import net.fabricmc.loader.api.FabricLoader;
import net.sssubtlety.chicken_nerf.ChickenNerf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Plain .conf under config/chicken_nerf.conf — no Cloth / ModMenu / MidnightLib.
 */
public final class SimpleConfig {
	private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("chicken_nerf.conf");

	public int minLaidEggs = 1;
	public int maxLaidEggs = 3;
	/** Probability that a thrown egg spawns exactly one chick (0–1). */
	public double eggSuccessChance = 0.5;

	private static SimpleConfig instance;

	private SimpleConfig() {}

	public static SimpleConfig get() {
		if (instance == null) {
			instance = new SimpleConfig();
			instance.load();
		}
		return instance;
	}

	public void load() {
		if (!Files.isRegularFile(PATH)) {
			save();
			return;
		}
		Properties props = new Properties();
		try (var in = Files.newInputStream(PATH)) {
			props.load(in);
			minLaidEggs = parseInt(props.getProperty("minLaidEggs"), 1);
			maxLaidEggs = parseInt(props.getProperty("maxLaidEggs"), 3);
			eggSuccessChance = parseDouble(props.getProperty("eggSuccessChance"), 0.5);
			validate();
		} catch (IOException e) {
			ChickenNerf.LOGGER.warn("Failed to read {}, using defaults", PATH, e);
		}
	}

	public void save() {
		validate();
		Properties props = new Properties();
		props.setProperty("minLaidEggs", Integer.toString(minLaidEggs));
		props.setProperty("maxLaidEggs", Integer.toString(maxLaidEggs));
		props.setProperty("eggSuccessChance", Double.toString(eggSuccessChance));
		try {
			Files.createDirectories(PATH.getParent());
			try (var out = Files.newOutputStream(PATH)) {
				props.store(out, "Chicken Nerf (chance fork)\nminLaidEggs / maxLaidEggs: eggs dropped when breeding chickens\neggSuccessChance: probability (0-1) that a thrown egg hatches 1 chick");
			}
		} catch (IOException e) {
			ChickenNerf.LOGGER.warn("Failed to write {}", PATH, e);
		}
	}

	private void validate() {
		if (minLaidEggs < 0) minLaidEggs = 0;
		if (maxLaidEggs < 0) maxLaidEggs = 0;
		if (minLaidEggs > maxLaidEggs) {
			int t = minLaidEggs;
			minLaidEggs = maxLaidEggs;
			maxLaidEggs = t;
		}
		if (eggSuccessChance < 0.0 || eggSuccessChance > 1.0) {
			eggSuccessChance = 0.5;
		}
	}

	private static int parseInt(String s, int def) {
		if (s == null) return def;
		try { return Integer.parseInt(s.trim()); } catch (NumberFormatException e) { return def; }
	}

	private static double parseDouble(String s, double def) {
		if (s == null) return def;
		try { return Double.parseDouble(s.trim()); } catch (NumberFormatException e) { return def; }
	}
}
