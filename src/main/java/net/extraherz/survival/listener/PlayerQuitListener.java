package net.extraherz.survival.listener;

import net.extraherz.survival.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        Utils.scheduler.cancelTask(Utils.onlineTimeTask.getTaskId());
    }

}
