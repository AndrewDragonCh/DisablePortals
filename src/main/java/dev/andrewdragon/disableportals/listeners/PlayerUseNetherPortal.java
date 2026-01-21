package dev.andrewdragon.disableportals.listeners;

import dev.andrewdragon.disableportals.DisablePortals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.world.PortalCreateEvent;

public class PlayerUseNetherPortal implements Listener {

    private final DisablePortals plugin;

    public PlayerUseNetherPortal(DisablePortals plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onNetherPortalCreate(PortalCreateEvent e) {
        boolean netherPortalsEnabled = plugin.getConfig().getBoolean("nether-portals.enabled");
        String netherDisabledMessage = plugin.getConfig().getString("nether-portals.disabled-message");
        Entity entity = e.getEntity();

        if (!netherPortalsEnabled && e.getReason().equals(PortalCreateEvent.CreateReason.FIRE)) {
            e.setCancelled(true);
            if (entity instanceof Player) {
                if (netherDisabledMessage != null) {
                    entity.sendRichMessage(netherDisabledMessage);
                } else {
                    plugin.getLogger().warning("Unable to read nether-portals.disabled-message.");
                    entity.sendRichMessage("<red>Nether Portals have been disabled.</red>");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerEnterNetherPortal(PlayerTeleportEvent e) {
        boolean netherPortalsEnabled = plugin.getConfig().getBoolean("nether-portals.enabled");
        String netherDisabledMessage = plugin.getConfig().getString("nether-portals.disabled-message");
        Player player = e.getPlayer();

        if (!netherPortalsEnabled && e.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL)) {
            e.setCancelled(true);
            if (netherDisabledMessage != null) {
                player.sendRichMessage(netherDisabledMessage);
            } else {
                plugin.getLogger().warning("Unable to read nether-portals.disabled-message.");
                player.sendRichMessage("<red>Nether Portals have been disabled.</red>");
            }
        }
    }

//    @EventHandler
//    public void onEntityEnterNetherPortal(EntityPortalEvent e) {
//        boolean netherPortalsEnabled = plugin.getConfig().getBoolean("nether-portals.enabled.entities");
//        if (!netherPortalsEnabled && e.getPortalType().equals(PortalType.NETHER)) {
//            e.setCancelled(true);
//        }
//    }
}