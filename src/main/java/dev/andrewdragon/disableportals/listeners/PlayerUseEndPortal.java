package dev.andrewdragon.disableportals.listeners;

import dev.andrewdragon.disableportals.DisablePortals;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.PortalType;

public class PlayerUseEndPortal implements Listener {

    private final DisablePortals plugin;

    public PlayerUseEndPortal(DisablePortals plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEndPortalCreate(PortalCreateEvent e) {
        boolean endPortalsEnabled = plugin.getConfig().getBoolean("end-portals.enabled");
        String endDisabledMessage = plugin.getConfig().getString("end-portals.disabled-message");
        Entity entity = e.getEntity();

        if (!endPortalsEnabled && e.getReason().equals(PortalCreateEvent.CreateReason.END_PLATFORM)) {
            e.setCancelled(true);
            if (entity instanceof Player) {
                if (endDisabledMessage != null) {
                    entity.sendMessage(MiniMessage.miniMessage().deserialize(endDisabledMessage));
                } else {
                    plugin.getLogger().warning("Unable to read end-portals.disabled-message. Using Default.");
                    entity.sendMessage(MiniMessage.miniMessage().deserialize("<red>End Portals have been disabled.</red>"));
                }
            }
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
                // THIS SPAMS CHAT RIGHT NOW
                player.sendMessage(MiniMessage.miniMessage().deserialize(endDisabledMessage));
            } else {
                plugin.getLogger().warning("Unable to read end-portals.disabled-message. Using Default.");
                player.sendMessage(MiniMessage.miniMessage().deserialize("<red>End Portals have been disabled.</red>"));
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