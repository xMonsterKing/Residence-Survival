package net.extraherz.survival.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class SettingsDataYaml {

    private final File file;
    private final YamlConfiguration yamlConfiguration;

    public SettingsDataYaml() {
        File dir = new File("./plugins/Survival");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        file = new File(dir, "settings.yml");

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
        return yamlConfiguration.contains("Players." + uuid + ".Particles");
    }

    public void createPlayer(UUID uuid) {
        if (!playerExists(uuid)) {
            yamlConfiguration.set("Players." + uuid + ".Particles", true);
            save();
        }
    }

    public boolean particleState(UUID uuid) {
        return yamlConfiguration.getBoolean("Players." + uuid + ".Particles");
    }

    public void setParticles(UUID uuid, boolean value) {
        if (playerExists(uuid)) {
            yamlConfiguration.set("Players." + uuid + ".Particles", value);
            save();
        } else {
            createPlayer(uuid);
            setParticles(uuid, value);
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
