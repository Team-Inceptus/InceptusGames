package us.teaminceptus.games.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Base GameManager Interface.
 * <p>
 * A GameManager is used to interact with <strong>current</strong> games, and able to stop them.
 */
public interface GameManager {
    
    /**
     * All Registered Game Managers
     */
    Map<String, GameManager> GAME_MANAGERS = new HashMap<>();

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
