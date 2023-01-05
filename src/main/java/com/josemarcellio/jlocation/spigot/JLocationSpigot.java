package com.josemarcellio.jlocation.spigot;

import com.josemarcellio.jlocation.spigot.command.JLocationCommand;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class JLocationSpigot extends JavaPlugin {

    @Override
    public void onEnable() {

        new Metrics(this, 17303);

        getLogger().info("JLocation by JoseMarcellio");

        saveDefaultConfig();

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
