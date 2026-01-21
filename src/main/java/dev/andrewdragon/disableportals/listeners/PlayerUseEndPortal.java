package dev.andrewdragon.disableportals.listeners;

import dev.andrewdragon.disableportals.DisablePortals;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerUseEndPortal implements Listener {

    private final DisablePortals plugin;

    public PlayerUseEndPortal(DisablePortals plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEndPortalCreate(PortalCreateEvent e) {
        boolean endPortalsEnabled = plugin.getConfig().getBoolean("end-portals.enabled");

        if (!endPortalsEnabled && e.getReason().equals(PortalCreateEvent.CreateReason.END_PLATFORM)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerEnterEndPortal(PlayerTeleportEvent e) {
        boolean endPortalsEnabled = plugin.getConfig().getBoolean("end-portals.enabled");
        String endDisabledMessage = plugin.getConfig().getString("end-portals.disabled-message");
        Player player = e.getPlayer();

        if (!endPortalsEnabled && e.getCause().equals(PlayerTeleportEvent.TeleportCause.END_PORTAL)) {
            e.setCancelled(true);
            if (endDisabledMessage != null) {
                player.sendRichMessage(endDisabledMessage);
            } else {
                plugin.getLogger().warning("Unable to read end-portals.disabled-message. Using Default.");
                player.sendRichMessage("<red>End Portals have been disabled.</red>");
            }
        }
    }

//    @EventHandler
//    public void onEntityEnterNetherPortal(EntityPortalEvent e) {
//        boolean endPortalsEnabled = plugin.getConfig().getBoolean("end-portals.enabled.entities");
//        if (!endPortalsEnabled && e.getPortalType().equals(PortalType.ENDER)) {
//            e.setCancelled(true);
//        }
//    }
}