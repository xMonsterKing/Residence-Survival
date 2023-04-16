package net.extraherz.survival.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerDataYaml {

    private final File file;
    private final YamlConfiguration yamlConfiguration;

    public PlayerDataYaml() {
        File dir = new File("./plugins/Survival");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        file = new File(dir, "playerdata.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public boolean playerExists(UUID uuid) {
        return yamlConfiguration.contains(uuid + ".Joins");
    }

    public void createPlayer(UUID uuid) {
        if (!playerExists(uuid)) {
            yamlConfiguration.set(uuid + ".Joins", 1);
            save();
        }
    }

    public int getJoins(UUID uuid) {
        return yamlConfiguration.getInt(uuid + ".Joins");
    }

    public void addJoins(UUID uuid, int value) {
        if (playerExists(uuid)) {
            yamlConfiguration.set(uuid + ".Joins", getJoins(uuid) + value);
            save();
        } else {
            createPlayer(uuid);
            addJoins(uuid, value);
        }
    }

    public void save() {
        try {
            yamlConfiguration.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
