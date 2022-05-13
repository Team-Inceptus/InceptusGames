package us.teaminceptus.games.util;

import java.util.Properties;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

/**
 * Base GameManager Interface.
 * <p>
 * A GameManager is used to interact with <strong>current</strong> games, and able to stop them. Also contains useful game utilities.
 */
public interface GameManager extends Listener {

    // Utils

    static boolean isLanguage(@Nullable String input) {
        return getLanguage(input) != null;
    }

    static String getProperty(Properties p, String key, String defaultValue) {
        return ChatColor.translateAlternateColorCodes('&', p.getProperty(key, defaultValue));
    }

    default String getProp(Properties p, String key, String defaultValue) {
        return getProperty(p, key, defaultValue);
    }

    default String getProp(Properties p, String key) {
        return getProp(p, key, "null");
    }

    @Nullable
    static Language getLanguage(@Nullable String input) {
        if (input == null) return null;

        for (Language l : Language.values()) {
            if (l.getSuffix().equalsIgnoreCase(input)) return l;
        }

        return null;
    }


    // Other

    /**
     * Whether or not this Game is running.
     * @return true if running, else false
     */
    boolean running();

    /**
     * Returns InceptusGames Plugin Instance.
     * @return Plugin found
     */
    static Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin("InceptusGames");
    }

    /**
     * Gets all of the participants that have appeared.
     * @return List of Participants
     */
    Set<Player> getParticipants();

    /**
     * Gets all of the remaining participants.
     * @return Remaining Participants
     */
    Set<Player> getRemainingPlayers();

    /**
     * Stops the game. Silently fails if not running.
     */
    void stop();

    /**
     * Gets the current World that this Game is playing.
     * @return
     */
    World getCurrentWorld();

    /**
     * Gets the Center of this GameManager.
     * @return Center
     */
    Location getCenter();

    /**
     * Represents an Abstract GameType
     */
    interface GameType {

        int getId();

        int getTeamSize();

        int getTeamAmount();

    }

}
