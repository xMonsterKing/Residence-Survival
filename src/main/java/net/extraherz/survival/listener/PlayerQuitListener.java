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

        event.quitMessage(mm.deserialize("<!i><#5555FF>" + player.getName() + " <#ffffff>verlässt den Nightloft Minecraft Server!"));
        Utils.scheduler.cancelTask(Utils.onlineTimeTask.getTaskId());
    }

}
