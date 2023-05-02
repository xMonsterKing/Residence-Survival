package net.extraherz.survival.commands;

import net.extraherz.survival.Survival;
import net.extraherz.survival.utils.Utils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RestartCommand implements CommandExecutor {

    private final MiniMessage mm = MiniMessage.miniMessage();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof final Player player)) {
            sender.sendMessage(mm.deserialize("<!i>Nur als Spieler ausführbar"));
            return true;
        }

        if (command.getName().equalsIgnoreCase("nightrestart")) {
            if (!Utils.isPlayerInGroup(player, "queen") || !Utils.isPlayerInGroup(player, "xi") || !player.getName().equals("Extraherz")) {
                player.sendMessage(mm.deserialize("<!i><red>Dazu hast du keine Rechte."));
            } else {
                if (args.length != 1) {
                    player.sendMessage(mm.deserialize("<!i><white>Nutze: <blue>/nightrestart <minutes>"));
                    return true;
                }

                int restartDelay;
                try {
                    restartDelay = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    player.sendMessage(mm.deserialize("<!i><red>Ungültige Zahl: " + args[0]));
                    return true;
                }
                Bukkit.broadcast(mm.deserialize("<!i><gold>[<dark_red>Broadcast<gold>] <green>Der Server startet in " + restartDelay + " Minute/n neu."));
                Bukkit.getScheduler().runTaskLater(Survival.getInstance(), () -> {
                    // Kick all players 1 second before shutdown
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.kick(mm.deserialize("<!i><red>Der Server startet gerade neu, versuche in wenigen Sekunden zu rejoinen."));
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
                            Bukkit.broadcast(mm.deserialize("<!i><gold>[<dark_red>Broadcast<gold>] <green>Der Server startet in " + timerMinute + " Minute/n neu."));
                        }, secondsLeft * 20L - 20L);
                    }
                }

                // Schedule last 5 seconds message
                if (restartDelay > 0) {
                    Bukkit.getScheduler().runTaskLater(Survival.getInstance(), () -> {
                        Bukkit.broadcast(mm.deserialize("<!i><gold>[<dark_red>Broadcast<gold>] <green>Der Server startet in 4 Sekunden neu."));
                    }, restartDelay * 60 * 20L - 100L);
                }
            }
        }

        return false;
    }
}
