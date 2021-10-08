package org.librenote.mc.cardinal.utils;

import org.bukkit.plugin.java.JavaPlugin;
import org.librenote.mc.cardinal.utils.events.PlayerJoin;
import org.librenote.mc.cardinal.utils.events.PlayerRespawn;

public class Main extends JavaPlugin {
    public static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();

        this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }
}
