package com.josemarcellio.jlocation;

import com.josemarcellio.jlocation.command.JLocationCommand;
import com.josemarcellio.jlocation.listener.PlayerJoinQuitListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class JLocation extends JavaPlugin {

    @Override
    public void onEnable() {

        new Metrics(this, 17303);

        getLogger().info("JLocation by JoseMarcellio");

        saveDefaultConfig();

        PlayerJoinQuitListener playerJoinQuitListener =
                new PlayerJoinQuitListener(this);
        getServer().getPluginManager().registerEvents(
                playerJoinQuitListener, this);

        JLocationCommand jLocationCommand =
                new JLocationCommand(this);
        getCommand("jlocation").setExecutor(
                jLocationCommand);
    }

    @Override
    public void onDisable() {
        getLogger().info("JLocation by JoseMarcellio");
    }
}
