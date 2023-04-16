package net.extraherz.survival.listener;

import net.extraherz.survival.Survival;
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

        Utils.onlineTimeTask = Utils.scheduler.runTaskTimer(Survival.getInstance(), () -> {
            player.sendActionBar(mm.deserialize("<!i><gradient:#9c0505:#f72525>" + Utils.tickToTime(player.getStatistic(Statistic.PLAY_ONE_MINUTE)) + "</gradient>"));
        }, 0L, 20L);
    }

}
