package net.extraherz.survival.listener;

import net.extraherz.survival.Survival;
import net.extraherz.survival.utils.Messages;
import net.extraherz.survival.utils.Utils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final MiniMessage mm = MiniMessage.miniMessage();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (!Survival.getInstance().getPlayerDataYaml().playerExists(player.getUniqueId())) {
            event.joinMessage(mm.deserialize(Messages.firstPlayerJoin.replaceAll("%player%", player.getName())));
            Survival.getInstance().getPlayerDataYaml().createPlayer(player.getUniqueId());
        } else {
            event.joinMessage(mm.deserialize(Messages.playerJoin.replaceAll("%player%", player.getName())));
            Survival.getInstance().getPlayerDataYaml().addJoins(player.getUniqueId(), 1);
        }

        if (!Survival.getInstance().getSettingsDataYaml().playerExists(player.getUniqueId()) && !player.hasPermission("booster.particles")) {
            Survival.getInstance().getSettingsDataYaml().createPlayer(player.getUniqueId());
        }

        Utils.onlineTimeTask = Utils.scheduler.runTaskTimer(Survival.getInstance(), () -> player.sendActionBar(mm.deserialize(Messages.playTimeActionbar.replaceAll("%playtime%", Utils.tickToTime(player.getStatistic(Statistic.PLAY_ONE_MINUTE))))), 0L, 20L);
    }

}
