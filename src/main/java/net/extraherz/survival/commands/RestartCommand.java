package net.extraherz.survival.commands;

import net.extraherz.survival.Survival;
import net.extraherz.survival.utils.Messages;
import net.extraherz.survival.utils.Utils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

public class RestartCommand implements CommandExecutor {

    private final MiniMessage mm = MiniMessage.miniMessage();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        final Player player = (Player) sender;

        var groups = Set.of("queen", "xi");
        var players = Set.of(UUID.fromString("1e001e65-c775-46ee-abd6-be8f70f1218a"), UUID.fromString("49d9cfe2-8ef6-4b3f-9ec9-fff38914b9f9"), UUID.fromString("2def97cf-a44e-415d-b69a-76b3f7ffe217"));

        if (command.getName().equalsIgnoreCase("nightrestart")) {
            if (!groups.contains(Utils.getPlayerGroup(player)) && !players.contains(player.getUniqueId())) {
                player.sendMessage(mm.deserialize(Messages.noPermission));
            } else {
                if (args.length != 1) {
                    player.sendMessage(mm.deserialize(Messages.restartCommand));
                    return true;
                }

                int restartDelay;
                try {
                    restartDelay = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    player.sendMessage(mm.deserialize("<!i><red>Ungültige Zahl: " + args[0]));
                    return true;
                }
                Bukkit.broadcast(mm.deserialize(Messages.startRestartTimer));
                Bukkit.getScheduler().runTaskLater(Survival.getInstance(), () -> {
                    // Kick all players 1 second before shutdown
                    for (Player targets : Bukkit.getOnlinePlayers()) {
                        targets.kick(mm.deserialize(Messages.playerKick));
                    }

                    Bukkit.getScheduler().runTaskLater(Survival.getInstance(), () -> {
                        // Shutdown server
                        Bukkit.getServer().shutdown();
                    }, 20L);
                }, restartDelay * 60 * 20L - 20L);

                // Schedule timer messages
                int[] timerMinutes = {5, 2, 1};
                for (int timerMinute : timerMinutes) {
                    if (restartDelay > timerMinute) {
                        int secondsLeft = (restartDelay - timerMinute) * 60;
                        Bukkit.getScheduler().runTaskLater(Survival.getInstance(), () -> {
                            Bukkit.broadcast(mm.deserialize(Messages.timerLastMinutes.replaceAll("%delay%", Arrays.toString(timerMinutes))));
                        }, secondsLeft * 20L - 20L);
                    }
                }

                // Schedule last 5 seconds message
                if (restartDelay > 0) {
                    Bukkit.getScheduler().runTaskLater(Survival.getInstance(), () -> {
                        Bukkit.broadcast(mm.deserialize(Messages.timerLastFourSeconds));
                    }, restartDelay * 60 * 20L - 100L);
                }
            }
        }

        return false;
    }
}
