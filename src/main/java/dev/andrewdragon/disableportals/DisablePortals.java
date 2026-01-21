package dev.andrewdragon.disableportals;

import dev.andrewdragon.disableportals.listeners.*;
import dev.andrewdragon.disableportals.commands.*;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisablePortals extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Plugin Enabled");

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PlayerUseEndPortal(this), this);
        getServer().getPluginManager().registerEvents(new PlayerUseNetherPortal(this), this);

        final PluginCommand toggleEndPortalsCommand = getCommand("toggleendportals");
        final PluginCommand toggleNetherPortalsCommand = getCommand("togglenetherportals");

        if (toggleEndPortalsCommand != null)
            toggleEndPortalsCommand.setExecutor(new ToggleEndPortals(this));

        if (toggleNetherPortalsCommand != null)
            toggleNetherPortalsCommand.setExecutor(new ToggleNetherPortals(this));
    }
}
