package dev.andrewdragon.disableportals;

import dev.andrewdragon.disableportals.listeners.*;
import dev.andrewdragon.disableportals.commands.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisablePortals extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getLogger().info("Plugin Enabled");

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PlayerUseEndPortal(this), this);
        getServer().getPluginManager().registerEvents(new PlayerUseNetherPortal(this), this);

        getCommand("toggleendportals").setExecutor(new ToggleEndPortals(this));
        getCommand("togglenetherportals").setExecutor(new ToggleNetherPortals(this));
    }
}
