package net.sssubtlety.chicken_nerf;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.api.metadata.version.VersionPredicate;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FeatureControl {
    private static final @Nullable Config CONFIG_INSTANCE;

    static {
        boolean shouldLoadConfig = false;

        final Optional<ModContainer> optModContainer = FabricLoader.getInstance().getModContainer("cloth-config");
        if (optModContainer.isPresent()) {
            try {
                shouldLoadConfig = VersionPredicate.parse(">=6.1.48").test(optModContainer.get().getMetadata().getVersion());
            } catch (VersionParsingException e) {
                e.printStackTrace();
            }
        }

        CONFIG_INSTANCE = shouldLoadConfig ?
                AutoConfig.register(Config.class, GsonConfigSerializer::new).getConfig() : null;
    }

    public interface Defaults {
        int minLaidEggs = 1;
        int maxLaidEggs = 3;
        double averageChickensFromEgg = 0.6;
        boolean enableTranslationFetching = true;
    }

    private static final double defaultEggSuccessChance = Defaults.averageChickensFromEgg / (Defaults.averageChickensFromEgg + 1);

    public static void init() { }

    public static boolean isConfigLoaded() {
        return CONFIG_INSTANCE != null;
    }

    public static int getMinLaidEggs() {
        return CONFIG_INSTANCE == null ? Defaults.minLaidEggs : CONFIG_INSTANCE.minLaidEggs;
    }

    public static int getMaxLaidEggs() {
        return CONFIG_INSTANCE == null ? Defaults.maxLaidEggs : CONFIG_INSTANCE.maxLaidEggs;
    }

    public static double getEggSuccessChance() {
        return  CONFIG_INSTANCE == null ? defaultEggSuccessChance :
                CONFIG_INSTANCE.averageChickensFromEgg / (CONFIG_INSTANCE.averageChickensFromEgg + 1);
    }

    public static boolean isTranslationFetchingEnabled() {
        return CONFIG_INSTANCE == null ? Defaults.enableTranslationFetching : CONFIG_INSTANCE.enableTranslationFetching;
    }
}
