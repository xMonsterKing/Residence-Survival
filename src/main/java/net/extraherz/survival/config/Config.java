package net.extraherz.survival.config;

import net.extraherz.survival.utils.Messages;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class Config {

    private File getFile() {
        return new File("plugins/Survival", "messages.yml");
    }

    private FileConfiguration getFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getFile());
    }

    public void setStandard() {
        FileConfiguration fileConfiguration = getFileConfiguration();

        fileConfiguration.options().copyDefaults(true);

        fileConfiguration.options().setHeader(List.of("EN: Here you can edit any message that is output by this plugin.", "DE: Hier können Sie jede Nachricht, die von diesem Plugin ausgegeben wird, bearbeiten."));

        fileConfiguration.addDefault("Message.Particle.NoBoosterRank", "<!i><white>Booste den Server, um auch Zugriff auf die Partikeleffekte zu erhalten.");
        fileConfiguration.addDefault("Message.Particle.TryToEnable.CurrentlyOn", "<!i><white>Deine Partikel sind schon <green>eingeschaltet.");
        fileConfiguration.addDefault("Message.Particle.TryToEnable.CurrentlyOff", "<!i><white>Deine Partikel wurden erfolgreich <green>eingeschaltet");
        fileConfiguration.addDefault("Message.Particle.TryToDisable.CurrentlyOff", "<!i><white>Deine Partikel sind schon <#990f02>ausgeschaltet.");
        fileConfiguration.addDefault("Message.Particle.TryToDisable.CurrentlyOn", "<!i><white>Deine Partikel wurden erfolgreich <#990f02>ausgeschaltet");

        fileConfiguration.addDefault("Message.PlayTime.ActualPlayTime", "<!i><white>Deine aktuelle Spielzeit beträgt: %playtime% <#542391>");
        fileConfiguration.addDefault("Message.PlayTime.Actionbar", "<!i><gradient:#542391:#9163ca> %playtime% </gradient>");
        fileConfiguration.addDefault("Message.PlayTime.Reloaded", "<!i><red>Playtime wurde erfolgreich neu geladen!");

        fileConfiguration.addDefault("Message.Restart.StartTimer", "<!i><gold>[<dark_red>Broadcast<gold>] <green>Der Server startet in %delay% Minute/n neu.");
        fileConfiguration.addDefault("Message.Restart.TimerLastMinutes", "<!i><gold>[<dark_red>Broadcast<gold>] <green>Der Server startet in %minutes% Minute/n neu.");
        fileConfiguration.addDefault("Message.Restart.TimerLastFourSeconds", "<!i><gold>[<dark_red>Broadcast<gold>] <green>Der Server startet in 4 Sekunden neu.");
        fileConfiguration.addDefault("Message.Restart.Kick", "<!i><red>Der Server startet gerade neu, versuche in wenigen Sekunden zu rejoinen.");

        fileConfiguration.addDefault("Message.FirstPlayerJoin", "<!i><#5555FF>%player% <#ffffff>hat den Nightloft Minecraft Server zum ersten mal betreten, herzlich Willkommen!");
        fileConfiguration.addDefault("Message.PlayerJoin", "<!i><#ffffff>Hey <#5555FF>%player%<#ffffff>, willkommen zurück auf dem Nightloft Minecraft Server!");
        fileConfiguration.addDefault("Message.PlayerDisconnect", "<!i><#5555FF>%player% <#ffffff>verlässt den Nightloft Minecraft Server!");

        fileConfiguration.addDefault("Message.Command.PlayTime", "<!i><white>Nutze: <blue>/playtime oder /playtime reload");
        fileConfiguration.addDefault("Message.Command.Particle", "<!i><white>Nutze: <#990f02>/particle on/off");
        fileConfiguration.addDefault("Message.Command.Restart", "<!i><white>Nutze: <blue>/nightrestart <minutes>");


    }

    public void readData() {
        FileConfiguration fileConfiguration = getFileConfiguration();
        Messages.particleBoosterRank = fileConfiguration.getString("Message.Particle.NoBoosterRank");
        Messages.particleCurrentlyOnToOn = fileConfiguration.getString("Message.Particle.TryToEnable.CurrentlyOn");
        Messages.particleCurrentlyOnToOff = fileConfiguration.getString("Message.Particle.TryToEnable.CurrentlyOff");
        Messages.particleCurrentlyOffToOff = fileConfiguration.getString("Message.Particle.TryToDisable.CurrentlyOff");
        Messages.particleCurrentlyOffToOn = fileConfiguration.getString("Message.Particle.TryToDisable.CurrentlyOn");
        Messages.actualPlayTime = fileConfiguration.getString("Message.PlayTime.ActualPlayTime");
        Messages.playTimeActionbar = fileConfiguration.getString("Message.PlayTime.Actionbar");
        Messages.playTimeReloaded = fileConfiguration.getString("Message.PlayTime.Reloaded");
        Messages.startRestartTimer = fileConfiguration.getString("Message.Restart.StartTimer");
        Messages.timerLastMinutes = fileConfiguration.getString("Message.Restart.TimerLastMinutes");
        Messages.timerLastFourSeconds = fileConfiguration.getString("Message.Restart.TimerLastFourSeconds");
        Messages.playerKick = fileConfiguration.getString("Message.Restart.Kick");
        Messages.firstPlayerJoin = fileConfiguration.getString("Message.FirstPlayerJoin");
        Messages.playerJoin = fileConfiguration.getString("Message.PlayerJoin");
        Messages.playerDisconnect = fileConfiguration.getString("Message.PlayerDisconnect");
        Messages.playTimeCommand = fileConfiguration.getString("Message.Command.PlayTime");
        Messages.particleCommand = fileConfiguration.getString("Message.Command.Particle");
        Messages.restartCommand = fileConfiguration.getString("Message.Command.Restart");
    }

}
