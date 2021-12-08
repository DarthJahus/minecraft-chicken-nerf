package net.sssubtlety.chicken_nerf;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Config(name = "chicken_nerf")
public class ChickenNerfConfig implements ConfigData {
    @ConfigEntry.Gui.Excluded
    protected static final Logger LOGGER = LogManager.getLogger();

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.BoundedDiscrete(max = 10)
    int minLaidEggs = 1;

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.BoundedDiscrete(max = 10)
    int maxLaidEggs = 3;

    @ConfigEntry.Gui.Tooltip()
    double averageChickensFromEgg = 0.6;

    public int getMinLaidEggs() {
        return minLaidEggs;
    }

    public int getMaxLaidEggs() {
        return maxLaidEggs;
    }

    public double getEggSuccessChance() {
        return averageChickensFromEgg / (averageChickensFromEgg + 1);
    }

    @Override
    public void validatePostLoad() {
        if(minLaidEggs < 0) {
            LOGGER.warn("ChickenNerfConfig found negative minLaidEggs. Defaulting to 0. ");
            minLaidEggs = 0;
        } else if (minLaidEggs > 10) {
            LOGGER.warn("ChickenNerfConfig found minLaidEggs > 10. Defaulting to 10. ");
            minLaidEggs = 10;
        }

        if (maxLaidEggs < 0) {
            LOGGER.warn("ChickenNerfConfig found negative maxLaidEggs. Defaulting to 0. ");
            maxLaidEggs = 0;
        } else if (maxLaidEggs > 10) {
            LOGGER.warn("ChickenNerfConfig found maxLaidEggs > 10. Defaulting to 10. ");
            maxLaidEggs = 10;
        }

        if (minLaidEggs > maxLaidEggs) {
            LOGGER.warn("ChickenNerfConfig found minLaidEggs > maxLaidEggs. Swapping. ");
            int temp = minLaidEggs;
            minLaidEggs = maxLaidEggs;
            maxLaidEggs = temp;
        }

        if (averageChickensFromEgg <= 0) {
            LOGGER.warn("ChickenNerfConfig found averageChickensFromEgg <= 0. Defaulting to 0.01. ");
            averageChickensFromEgg = 0.01;
        }

        if (averageChickensFromEgg > 100) {
            LOGGER.warn("ChickenNerfConfig found averageChickensFromEgg > 100. Defaulting to 100. ");
            averageChickensFromEgg = 100;
        }
    }
}
