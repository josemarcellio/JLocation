package com.josemarcellio.jlocation.bungee;

import com.google.common.io.ByteStreams;
import com.josemarcellio.jlocation.bungee.command.JLocationBungeeCommand;
import net.md_5.bungee.api.plugin.Plugin;
import org.bstats.bungeecord.Metrics;

import java.io.*;
import java.nio.file.Files;

public class JLocationBungee extends Plugin {

    @Override
    public void onEnable() {

        getLogger().info("JLocation by JoseMarcellio");

        new Metrics(this, 17303);

        setupConfig();

        JLocationBungeeCommand jLocationBungeeCommand =
                new JLocationBungeeCommand(this);
        getProxy().getPluginManager()
                .registerCommand(this, jLocationBungeeCommand);
    }

    private void setupConfig() {

        if (!getDataFolder().exists()) {
            if (!getDataFolder().mkdir()) {
                throw new RuntimeException(
                        "Unable to create plugin data folder");
            }
        }
        File configFile = new File(
                getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            try {
                if (!configFile.createNewFile()) {
                    throw new RuntimeException(
                            "Unable to create configuration file");
                }
                try (InputStream is = getResourceAsStream(
                        "config.yml");
                     OutputStream os = Files.newOutputStream(
                             configFile.toPath())) {
                    ByteStreams.copy(is, os);
                }
            } catch (IOException e) {
                throw new RuntimeException(
                        "Unable to create configuration file", e);
            }
        }
    }
}
