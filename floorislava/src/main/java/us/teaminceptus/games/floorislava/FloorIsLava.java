package us.teaminceptus.games.floorislava;

import org.bukkit.Location;

import us.teaminceptus.games.util.GameManager;

/**
 * Core FloorIsLava Game Manager 
*/
public interface FloorIsLava extends GameManager {

    /**
     * Tick Rate on Easy Mode (140 ticks / 7 seconds)
     */
    long EASY_TICKRATE = 140;

    /**
     * Tick Rate on Normal Mode (80 ticks / 4 seconds)
     */
    long NORMAL_TICKRATE = 80;

    /**
     * Tick Rate on Hard Mode (40 ticks / 2 seconds)
     */
    long HARD_TICKRATE = 40;

    /**
     * Tick Rate on Demon Mode (10 ticks / 0.5 seconds)
     */
    long DEMON_TICKRATE = 10;

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
     * Gets the current tick rate that Lava is rising.
     * @return Tick Rate
     */
    long getTickRate();

}