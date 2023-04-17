package net.extraherz.survival;

import lombok.SneakyThrows;
import net.extraherz.survival.commands.ParticleCommand;
import net.extraherz.survival.config.PlayerDataYaml;
import net.extraherz.survival.config.SettingsDataYaml;
import net.extraherz.survival.tabcompletion.ParticleTab;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

public final class Survival extends JavaPlugin {

    private static Survival instance;

    private PlayerDataYaml playerDataYaml;
    private SettingsDataYaml settingsDataYaml;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        playerDataYaml = new PlayerDataYaml();
        settingsDataYaml = new SettingsDataYaml();
        loadListeners(this, "net.extraherz.survival.listener");
        loadCommands();
    }

    @Override
    public void onDisable() {}

    @SneakyThrows
    private void loadListeners(JavaPlugin plugin, String path) {
        Reflections reflections = new Reflections(path);
        reflections.getSubTypesOf(Listener.class).forEach(clazz -> {
            System.out.println("Try to register class with name " + clazz.getSimpleName());
            try {
                plugin.getServer().getPluginManager().registerEvents(clazz.newInstance(), plugin);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadCommands() {
        getCommand("particle").setExecutor(new ParticleCommand());
        getCommand("particle").setTabCompleter(new ParticleTab());
    }

    public static Survival getInstance() {
        return instance;
    }

    public PlayerDataYaml getPlayerDataYaml() {
        return playerDataYaml;
    }

    public SettingsDataYaml getSettingsDataYaml() {
        return settingsDataYaml;
    }
}
