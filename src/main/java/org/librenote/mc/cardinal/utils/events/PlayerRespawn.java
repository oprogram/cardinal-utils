package org.librenote.mc.cardinal.utils.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.librenote.mc.cardinal.utils.Main;
import org.librenote.mc.cardinal.utils.mechanics.CountryData;

public class PlayerRespawn implements Listener {

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent playerRespawnEvent) {
        Player player = playerRespawnEvent.getPlayer();
        if (!playerRespawnEvent.isBedSpawn()) {
            CountryData countryData = new CountryData();
            Location location = countryData.getCoords(countryData.getCountryCode(player), player.getWorld());
            playerRespawnEvent.setRespawnLocation(location);
        }
    }
}
