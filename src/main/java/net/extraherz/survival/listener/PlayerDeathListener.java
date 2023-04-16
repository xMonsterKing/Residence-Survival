package net.extraherz.survival.listener;

import net.extraherz.survival.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        final Player player = event.getEntity();

        Utils.scheduler.cancelTask(Utils.onlineTimeTask.getTaskId());
    }


}
