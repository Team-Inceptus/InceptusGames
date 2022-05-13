package us.teaminceptus.games.floorislava;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import us.teaminceptus.games.util.GameManager;

/**
 * Core FloorIsLava Game Manager 
*/
public interface FloorIsLava extends GameManager {

    // File Manager & Other

    static File getFile() {
        Plugin p = Bukkit.getPluginManager().getPlugin("InceptusGames");

        return new File(p.getDataFolder(), "floorislava.yml");
    }

    static FileConfiguration loadConfiguration() {
        if (!(getFile().exists())) try { getFile().createNewFile(); } catch (IOException e) { e.printStackTrace(); }

        FileConfiguration config = YamlConfiguration.loadConfiguration(getFile());

        if (!(config.isConfigurationSection("setup"))) config.createSection("setup");
        ConfigurationSection setup = config.getConfigurationSection("setup");

        if (!(GameDifficulty.isGameDifficulty(config.getString("difficulty")))) setup.set("difficulty", "NORMAL");

        try {
            config.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return config; 
    }

    enum GameDifficulty {

        EASY(140),
        NORMAL(80),
        HARD(40),
        DEMON(10)
        ;

        private final long tickRate;

        private GameDifficulty(long tickRate) {
            this.tickRate = tickRate;
        }

        public long getTickRate() {
            return this.tickRate;
        }

        public static boolean isGameDifficulty(@Nullable String input) {
            if (input == null) return false;

            for (GameDifficulty d : values()) if (d.name().equalsIgnoreCase(input)) return true;
            return false;
        }
    }

    // Implementation

    /**
     * Fetches the First Corner.
     * @return First Corner
     */
    Location getFirstCorner();

    /**
     * Fetches the Second Corner.
     * @return Second Corner
     */
    Location getSecondCorner();
    
    /**
     * Fetches the start height's Y value.
     * @return Start Height
     */
    int getStartHeight();

    /**
     * Fetches the stop height's Y value.
     * @return Stop Height
     */
    int getStopHeight();

    /**
     * Gets the Difficulty for this FloorIsLava Game.
     * @return Game Difficulty
     */
    GameDifficulty getDifficulty();

}