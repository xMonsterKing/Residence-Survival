package net.extraherz.survival.listener;

import net.extraherz.survival.utils.Utils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final MiniMessage mm = MiniMessage.miniMessage();

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        event.quitMessage(mm.deserialize("<!i><#990f02>" + player.getName() + " <#ffffff>verlässt den Residence Minecraft Server!"));
        Utils.scheduler.cancelTask(Utils.onlineTimeTask.getTaskId());
    }

}
