package org.librenote.mc.cardinal.utils.events;

import org.bukkit.ChatColor;
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
            String countryCode = countryData.getCountryCode(player);
            player.teleport(countryData.getCoords(countryData.getCountryCode(player), player.getWorld()));
            player.sendMessage(ChatColor.RED + "Welcome to LibreCraft. You have spawned in " + countryData.getName(countryCode));
        }
    }
}
