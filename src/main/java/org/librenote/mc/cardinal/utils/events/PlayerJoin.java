package org.librenote.mc.cardinal.utils.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.librenote.mc.cardinal.utils.mechanics.CountryData;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent playerJoinEvent) {
        Player player = playerJoinEvent.getPlayer();
        if (!player.hasPlayedBefore()) {
            CountryData countryData = new CountryData();
            player.teleport(countryData.getCoords(countryData.getCountryCode(player), player.getWorld()));
        }
    }
}
