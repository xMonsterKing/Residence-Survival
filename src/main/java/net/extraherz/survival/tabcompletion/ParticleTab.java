package net.extraherz.survival.tabcompletion;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ParticleTab implements TabCompleter {

    List<String> arguments = new ArrayList<>();

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("on"); arguments.add("off");
        }

        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String argument : arguments) {
                if (argument.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(argument);
                }
            }
            return result;
        }

        return null;
    }
}
