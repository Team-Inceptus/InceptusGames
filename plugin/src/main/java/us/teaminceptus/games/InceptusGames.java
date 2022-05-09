package us.teaminceptus.games;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import revxrsal.commands.bukkit.BukkitCommandHandler;

/**
 * Core InceptusGames Plugin
 */
public final class InceptusGames extends JavaPlugin implements Listener {

    // Plugin Fields
    // Implementation Fields

    // Plugin Logic

    public void onEnable() {
        BukkitCommandHandler handler = BukkitCommandHandler.create(this);

        handler.register(this);
        handler.registerBrigadier();
    }


    public void onDisable() {

    }

    // Implementation


}
