package com.josemarcellio.jlocation.spigot.command;

import com.josemarcellio.jlocation.spigot.JLocationSpigot;
import com.josemarcellio.jlocation.location.Location;
import com.josemarcellio.jlocation.util.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class JLocationCommand
        implements CommandExecutor {

    private final JLocationSpigot plugin;

    public JLocationCommand(
            JLocationSpigot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            CommandSender sender, Command command, String label,
            String[] args) {

        if (args.length == 1) {

            if (sender.hasPermission("jlocation.admin")) {

                Player targetPlayer = Bukkit.getPlayer(args[0]);
                FileConfiguration configuration = plugin.getConfig();

                if (targetPlayer != null) {

                    String ip = targetPlayer.getAddress().getAddress()
                            .getHostAddress();
                    Location location = LocationUtils.getLocation(ip);

                    if (location != null) {

                        String playerName = targetPlayer.getName();
                        String city = location.getCity();
                        String as = location.getAs();
                        String asName = location.getAsname();
                        String country = location.getCountry();
                        String regionName = location.getRegionName();
                        String isp = location.getIsp();
                        String lat = location.getLat();
                        String lon = location.getLon();


                        List<String> messages = configuration
                                .getStringList("Messages");

                        messages.replaceAll(line ->
                                line.replace("{player}", playerName)
                                        .replace("{city}", city)
                                        .replace("{as}", as)
                                        .replace("{as_name}", asName)
                                        .replace("{country}", country)
                                        .replace("{region_name}", regionName)
                                        .replace("{isp}", isp)
                                        .replace("{lat}", lat)
                                        .replace("{lon}", lon)
                                        .replace("{ip}", ip));

                        sendMessage(sender, messages);
                    }
                } else {
                    sender.sendMessage(
                            ChatColor.translateAlternateColorCodes('&',
                                    "&6&lJLocation &7> &ePlayer not found!"));
                }
            } else {
                sender.sendMessage(
                        ChatColor.translateAlternateColorCodes('&',
                                "&6&lJLocation &7> &eYou don't " +
                                        "have a permission!"));
            }
        } else {
            sender.sendMessage(
                    ChatColor.translateAlternateColorCodes('&',
                            "&6&lJLocation &7> &e/jlocation <player>"));
        }
        return false;
    }

    private void sendMessage(
            CommandSender sender, List<String> message) {

        for (String line : message) {
            sender.sendMessage(
                    ChatColor.translateAlternateColorCodes('&',
                            line));
        }
    }
}