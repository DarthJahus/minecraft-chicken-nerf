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

    int minLayedEggs = 1;
    int maxLayedEggs = 3;
    double eggSuccessChance = 0.3333;

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
        }

        if (maxLayedEggs < 0) {
            LOGGER.warn("ChickenNerfConfig found negative maxLayedEggs. Defaulting to 0. ");
            maxLayedEggs = 0;
        }

        if (minLayedEggs > maxLayedEggs) {
            LOGGER.warn("ChickenNerfConfig found minLayedEggs > maxLayedEggs. Swapping. ");
            int temp = minLayedEggs;
            minLayedEggs = maxLayedEggs;
            maxLayedEggs = temp;
        }

        if (eggSuccessChance <= 0) {
            LOGGER.warn("ChickenNerfConfig found eggSuccessChance <= 0. Defaulting to 0.01 ");
            eggSuccessChance = 0.01;
        }

        if (eggSuccessChance >= 1) {
            LOGGER.warn("ChickenNerfConfig found eggSuccessChance >= 1. Defaulting to 0.9 ");
            eggSuccessChance = 0.9;
        }
    }

}
