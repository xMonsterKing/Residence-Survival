package net.extraherz.survival.utils;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class Utils {

    public static BukkitScheduler scheduler = Bukkit.getScheduler();
    public static BukkitTask onlineTimeTask;

    public static String tickToTime(int duration) {
        int seconds = duration / 20;
        int minutes = seconds / 60;
        int hours = minutes / 60;
        int days = hours / 24;

        seconds %= 60;
        minutes %= 60;
        hours %= 24;

        return String.format("%d Tage %02d:%02d:%02d", days, hours, minutes, seconds);
    }

    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }

    public static String getPlayerGroup(Player player) {
        LuckPerms api = LuckPermsProvider.get();
        User user = api.getUserManager().getUser(player.getUniqueId());
        if (user == null) {
            return "";
        }
        return user.getPrimaryGroup();
    }

}
