package net.extraherz.survival.listener;

import net.extraherz.survival.utils.Messages;
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

        event.quitMessage(mm.deserialize(Messages.playerDisconnect.replaceAll("%player%", player.getName())));
        Utils.scheduler.cancelTask(Utils.onlineTimeTask.getTaskId());
    }

}
