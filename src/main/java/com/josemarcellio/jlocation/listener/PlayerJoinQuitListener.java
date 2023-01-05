package com.josemarcellio.jlocation.listener;

import com.josemarcellio.jlocation.JLocation;
import com.josemarcellio.jlocation.location.Location;
import com.josemarcellio.jlocation.util.LocationUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitListener
        implements Listener {

    private final JLocation plugin;

    public PlayerJoinQuitListener(
            JLocation plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(
            PlayerJoinEvent event) {

        Player player = event.getPlayer();

        FileConfiguration configuration = plugin.getConfig();

        boolean joinQuitEnable = configuration
                .getBoolean("JoinQuit.Enable");

        if (joinQuitEnable) {

            String ip = player.getAddress().getAddress()
                    .getHostAddress();

            Location location = LocationUtils
                    .getLocation(ip);

            if (location != null) {
                String playerName = player.getName();
                String city = location.getCity();
                String as = location.getAs();
                String asName = location.getAsname();
                String country = location.getCountry();
                String regionName = location.getRegionName();
                String isp = location.getIsp();
                String lat = location.getLat();
                String lon = location.getLon();

                String message = configuration
                        .getString("JoinQuit.Join-Message");
                message = message
                        .replace("{player}", playerName)
                        .replace("{city}", city)
                        .replace("{as}", as)
                        .replace("{as_name}", asName)
                        .replace("{country}", country)
                        .replace("{region_name}", regionName)
                        .replace("{isp}", isp)
                        .replace("{lat}", lat)
                        .replace("{lon}", lon)
                        .replace("{ip}", ip);

                event.setJoinMessage(
                        ChatColor.translateAlternateColorCodes('&',
                                message));
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(
            PlayerQuitEvent event) {

        Player player = event.getPlayer();

        FileConfiguration configuration = plugin.getConfig();

        boolean joinQuitEnable = configuration
                .getBoolean("JoinQuit.Enable");

        if (joinQuitEnable) {

            String ip = player.getAddress().getAddress()
                    .getHostAddress();

            Location location = LocationUtils
                    .getLocation(ip);

            if (location != null) {
                String playerName = player.getName();
                String city = location.getCity();
                String as = location.getAs();
                String asName = location.getAsname();
                String country = location.getCountry();
                String regionName = location.getRegionName();
                String isp = location.getIsp();
                String lat = location.getLat();
                String lon = location.getLon();

                String message = configuration
                        .getString("JoinQuit.Quit-Message");
                message = message
                        .replace("{player}", playerName)
                        .replace("{city}", city)
                        .replace("{as}", as)
                        .replace("{as_name}", asName)
                        .replace("{country}", country)
                        .replace("{region_name}", regionName)
                        .replace("{isp}", isp)
                        .replace("{lat}", lat)
                        .replace("{lon}", lon)
                        .replace("{ip}", ip);

                event.setQuitMessage(
                        ChatColor.translateAlternateColorCodes('&',
                                message));
            }
        }
    }
}
