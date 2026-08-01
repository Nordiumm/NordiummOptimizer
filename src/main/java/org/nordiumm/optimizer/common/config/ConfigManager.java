package org.nordiumm.optimizer.common.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ConfigManager {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final File CONFIG_FILE =
            FabricLoader.getInstance()
                    .getConfigDir()
                    .resolve("nordiummoptimizer.json")
                    .toFile();


    public static void save() {

        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {

            GSON.toJson(new ConfigData(), writer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void load() {

        if (!CONFIG_FILE.exists()) {
            save();
            return;
        }

        try (FileReader reader = new FileReader(CONFIG_FILE)) {

            ConfigData data = GSON.fromJson(reader, ConfigData.class);

            OptimizerConfig.enabled = data.enabled;

            OptimizerConfig.showTotalEntities = data.showTotalEntities;
            OptimizerConfig.showPlayers = data.showPlayers;
            OptimizerConfig.showItemFrames = data.showItemFrames;
            OptimizerConfig.showArmorStands = data.showArmorStands;
            OptimizerConfig.showOther = data.showOther;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static class ConfigData {

        boolean enabled = OptimizerConfig.enabled;

        boolean showTotalEntities = OptimizerConfig.showTotalEntities;
        boolean showPlayers = OptimizerConfig.showPlayers;
        boolean showItemFrames = OptimizerConfig.showItemFrames;
        boolean showArmorStands = OptimizerConfig.showArmorStands;
        boolean showOther = OptimizerConfig.showOther;

    }
}