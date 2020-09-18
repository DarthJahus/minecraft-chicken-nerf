package net.sssubtlety.chicken_nerf;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Config(name = "chicken_nerf")
public class ChickenNerfConfig implements ConfigData {
    @ConfigEntry.Gui.Excluded
    protected static final Logger LOGGER = LogManager.getLogger();

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.BoundedDiscrete(max = 10)
    int minLayedEggs = 1;

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.BoundedDiscrete(max = 10)
    int maxLayedEggs = 3;

    @ConfigEntry.Gui.Tooltip()
    double averageChickensFromEgg = 1.2;

    @ConfigEntry.Gui.Excluded
    private double eggSuccessChance = 1 - 1/averageChickensFromEgg;

    public int getMinLayedEggs() {
        return minLayedEggs;
    }

    public int getMaxLayedEggs() {
        return maxLayedEggs;
    }

    public double getEggSuccessChance() {
        return eggSuccessChance;
    }

    @Override
    public void validatePostLoad() {
        if(minLayedEggs < 0) {
            LOGGER.warn("ChickenNerfConfig found negative minLayedEggs. Defaulting to 0. ");
            minLayedEggs = 0;
        } else if (minLayedEggs > 10) {
            LOGGER.warn("ChickenNerfConfig found minLayedEggs > 10. Defaulting to 10. ");
            minLayedEggs = 10;
        }

        if (maxLayedEggs < 0) {
            LOGGER.warn("ChickenNerfConfig found negative maxLayedEggs. Defaulting to 0. ");
            maxLayedEggs = 0;
        } else if (maxLayedEggs > 10) {
            LOGGER.warn("ChickenNerfConfig found maxLayedEggs > 10. Defaulting to 10. ");
            maxLayedEggs = 10;
        }

        if (minLayedEggs > maxLayedEggs) {
            LOGGER.warn("ChickenNerfConfig found minLayedEggs > maxLayedEggs. Swapping. ");
            int temp = minLayedEggs;
            minLayedEggs = maxLayedEggs;
            maxLayedEggs = temp;
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
