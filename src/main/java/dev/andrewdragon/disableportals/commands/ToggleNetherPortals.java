package dev.andrewdragon.disableportals.commands;

import dev.andrewdragon.disableportals.DisablePortals;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleNetherPortals implements CommandExecutor {

    private final DisablePortals plugin;

    public ToggleNetherPortals(DisablePortals plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender,Command command, String s, String[] args) {
        boolean netherPortalsEnabled = plugin.getConfig().getBoolean("nether-portals.enabled");

        this.plugin.getConfig().set("nether-portals.enabled", !netherPortalsEnabled);
        this.plugin.saveConfig();

        if (!netherPortalsEnabled){
            sender.sendMessage("Enabled Nether Portals");
        } else {
            sender.sendMessage("Disabled Nether Portals");
        }

        return true;
    }
}
