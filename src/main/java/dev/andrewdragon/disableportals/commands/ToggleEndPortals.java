package dev.andrewdragon.disableportals.commands;

import dev.andrewdragon.disableportals.DisablePortals;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleEndPortals implements CommandExecutor {

    private final DisablePortals plugin;

    public ToggleEndPortals(DisablePortals plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender,Command command, String s, String[] args) {
        boolean endPortalsEnabled = plugin.getConfig().getBoolean("end-portals.enabled");

        this.plugin.getConfig().set("end-portals.enabled", !endPortalsEnabled);
        this.plugin.saveConfig();

        if (!endPortalsEnabled){
            sender.sendMessage("Enabled End Portals");
        } else {
            sender.sendMessage("Disabled End Portals");
        }

        return true;
    }
}
