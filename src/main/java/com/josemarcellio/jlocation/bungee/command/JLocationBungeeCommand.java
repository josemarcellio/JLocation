package com.josemarcellio.jlocation.bungee.command;

import com.josemarcellio.jlocation.bungee.JLocationBungee;
import com.josemarcellio.jlocation.location.Location;
import com.josemarcellio.jlocation.util.LocationUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

public class JLocationBungeeCommand extends Command {

    private final JLocationBungee plugin;

    public JLocationBungeeCommand(
            JLocationBungee plugin) {

        super("jlocation");
        this.plugin = plugin;
    }

    @Override
    public void execute(
            CommandSender sender, String[] args) {

        if (args.length == 1) {

            if (sender.hasPermission("jlocation.admin")) {

                ProxiedPlayer targetPlayer = ProxyServer
                        .getInstance().getPlayer(args[0]);

                if (targetPlayer != null) {

                    InetSocketAddress socketAddress =
                            (InetSocketAddress) targetPlayer
                                    .getSocketAddress();

                    InetAddress inetAddress = socketAddress.getAddress();
                    String ip = inetAddress.getHostAddress();

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

                        try {

                            Configuration configuration = ConfigurationProvider
                                    .getProvider(YamlConfiguration.class).load(
                                            new File(plugin.getDataFolder(),
                                                    "config.yml"));

                            List<String> messages = configuration
                                .getStringList("Messages");

                            messages.replaceAll(line -> line
                                .replace("{player}", playerName)
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

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    sender.sendMessage(
                            new TextComponent(
                                    ChatColor.translateAlternateColorCodes('&',
                                            "&6&lJLocation &7> &ePlayer not found!")));
                }
            } else {
                sender.sendMessage(
                        new TextComponent(
                                ChatColor.translateAlternateColorCodes('&',
                                        "&6&lJLocation &7> &eYou don't " +
                                                "have a permission!")));
            }
        } else {
            sender.sendMessage(
                    new TextComponent(
                            ChatColor.translateAlternateColorCodes('&',
                                    "&6&lJLocation &7> &e/jlocation <player>")));
        }
    }

    @SuppressWarnings("deprecation")
    private void sendMessage(
            CommandSender sender, List<String> message) {

        for (String line : message) {
            sender.sendMessage(
                    ChatColor.translateAlternateColorCodes('&',
                            line));
        }
    }
}