package me.erano.com.api.performance;

import java.util.Locale;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.erano.com.api.CorePlugin;

public class ShowTPSCommand implements CommandExecutor {

    private final CorePlugin plugin;

    public ShowTPSCommand(CorePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        double[] tps = plugin.getTPSHandler().getTPS();
        sender.sendMessage("TPS 1m: " + formatTps(tps[0]));
        sender.sendMessage("TPS 5m: " + formatTps(tps[1]));
        sender.sendMessage("TPS 15m: " + formatTps(tps[2]));
        return true;
    }

    public static String formatTps(double tps) {
        String shortenedTPS = String.format(Locale.ENGLISH, "%.1f", tps);
        if (tps >= 20) {
            return ChatColor.GREEN + "" + ChatColor.BOLD + "*20.0";
        } else if (tps >= 19) {
            return ChatColor.GREEN + shortenedTPS;
        } else if (tps >= 15) {
            return ChatColor.YELLOW + shortenedTPS;
        } else if (tps >= 10) {
            return ChatColor.RED + shortenedTPS;
        } else {
            return ChatColor.RED + "" + ChatColor.BOLD + shortenedTPS;
        }
    }

}
