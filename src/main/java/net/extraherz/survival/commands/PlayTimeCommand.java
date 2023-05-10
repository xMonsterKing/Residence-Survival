package net.extraherz.survival.commands;

import net.extraherz.survival.Survival;
import net.extraherz.survival.utils.Messages;
import net.extraherz.survival.utils.Utils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayTimeCommand implements CommandExecutor {

    private final MiniMessage mm = MiniMessage.miniMessage();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof final Player player)) {
            sender.sendMessage(mm.deserialize("<!i>Nur als Spieler ausführbar"));
            return true;
        }

        if (command.getName().equalsIgnoreCase("playtime")) {
            switch (args.length) {
                case 0 -> {
                    player.sendMessage(mm.deserialize(Messages.actualPlayTime.replaceAll("%playtime%", Utils.tickToTime(player.getStatistic(Statistic.PLAY_ONE_MINUTE)))));
                }
                case 1 -> {
                    if (args[0].equalsIgnoreCase("reload")) {
                        Utils.scheduler.cancelTask(Utils.onlineTimeTask.getTaskId());
                        Utils.onlineTimeTask = Utils.scheduler.runTaskTimer(Survival.getInstance(), () -> player.sendActionBar(mm.deserialize(Messages.playTimeActionbar.replaceAll("%playtime%", Utils.tickToTime(player.getStatistic(Statistic.PLAY_ONE_MINUTE))))), 0L, 20L);
                        player.sendMessage(mm.deserialize("<!i><red>Playtime wurde erfolgreich neu geladen!"));
                    }
                }
                default -> {
                    player.sendMessage(mm.deserialize("<!i><white>Nutze: <blue>/playtime oder /playtime reload"));
                }
            }
        }

        return false;
    }
}
