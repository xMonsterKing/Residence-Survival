package net.extraherz.survival.commands;

import net.extraherz.survival.Survival;
import net.extraherz.survival.utils.Messages;
import net.extraherz.survival.utils.Utils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ParticleCommand implements CommandExecutor {

    private final MiniMessage mm = MiniMessage.miniMessage();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof final Player player)) {
            sender.sendMessage(mm.deserialize("<!i>Nur als Spieler ausführbar"));
            return true;
        }

        if (command.getName().equalsIgnoreCase("particle")) {
            if (!Utils.isPlayerInGroup(player, "booster")) {
                player.sendMessage(mm.deserialize(Messages.particleBoosterRank));
            } else {
                switch (args.length) {
                    case 0 -> {
                        player.sendMessage(mm.deserialize(Messages.particleCommand));
                    }
                    case 1 -> {
                        switch (args[0]) {
                            case "on" -> {
                                if (Survival.getInstance().getSettingsDataYaml().particleState(player.getUniqueId())) {
                                    player.sendMessage(mm.deserialize(Messages.particleCurrentlyOnToOn));
                                } else {
                                    Survival.getInstance().getSettingsDataYaml().setParticles(player.getUniqueId(), true);
                                    player.sendMessage(mm.deserialize(Messages.particleCurrentlyOnToOff));
                                }
                            }
                            case "off" -> {
                                if (!Survival.getInstance().getSettingsDataYaml().particleState(player.getUniqueId())) {
                                    player.sendMessage(mm.deserialize(Messages.particleCurrentlyOffToOff));
                                } else {
                                    Survival.getInstance().getSettingsDataYaml().setParticles(player.getUniqueId(), false);
                                    player.sendMessage(mm.deserialize(Messages.particleCurrentlyOffToOn));
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
}
