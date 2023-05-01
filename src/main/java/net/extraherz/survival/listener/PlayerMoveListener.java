package net.extraherz.survival.listener;

import net.extraherz.survival.Survival;
import net.extraherz.survival.utils.Utils;
import net.luckperms.api.model.user.User;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();

        switch (Utils.getPlayerGroup(player)) {
            case "queen", "xi" -> {
                if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                    player.getWorld().spawnParticle(Particle.REDSTONE, location, 3, 0, 0, 0.2D, new Particle.DustOptions(Color.fromRGB(168, 0, 0), 1));
                }
            }
            case "administrator" -> {
                if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                    player.getWorld().spawnParticle(Particle.REDSTONE, location, 3, 0, 0, 0.2D, new Particle.DustOptions(Color.fromRGB(251, 84, 84), 1));
                }
            }
            case "management" -> {
                if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                    player.getWorld().spawnParticle(Particle.REDSTONE, location, 3, 0, 0, 0.2D, new Particle.DustOptions(Color.fromRGB(251, 168, 0), 1));
                }
            }
            case "mod" -> {
                if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                    player.getWorld().spawnParticle(Particle.REDSTONE, location, 3, 0, 0, 0.2D, new Particle.DustOptions(Color.fromRGB(0, 0, 168), 1));
                }
            }
            case "developer" -> {
                if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                    player.getWorld().spawnParticle(Particle.REDSTONE, location, 3, 0, 0, 0.2D, new Particle.DustOptions(Color.fromRGB(84, 251, 251), 1));
                }
            }
            case "helfer" -> {
                if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                    player.getWorld().spawnParticle(Particle.REDSTONE, location, 3, 0, 0, 0.2D, new Particle.DustOptions(Color.fromRGB(84, 84, 251), 1));
                }
            }
            case "booster" -> {
                if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                    player.getWorld().spawnParticle(Particle.REDSTONE, location, 3, 0, 0, 0.2D, new Particle.DustOptions(Color.fromRGB(255, 85, 255), 1));
                }
            }
        }
    }

}
