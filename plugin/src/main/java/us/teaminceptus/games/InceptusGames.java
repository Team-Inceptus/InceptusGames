package us.teaminceptus.games;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import revxrsal.commands.bukkit.BukkitCommandHandler;
import us.teaminceptus.games.floorislava.FloorIsLava;

/**
 * Core InceptusGames Plugin
 */
public final class InceptusGames extends JavaPlugin implements FloorIsLava {

    BukkitCommandHandler handler;

    public void onEnable() {
        this.handler = BukkitCommandHandler.create(this);

        new Commands(this);

        handler.registerBrigadier();
    }

    public void onDisable() {

    }

    /**
     * Gets the first corner.
     *
     * @return Location of first corner
     */
    @Override
    public Location getFirstCorner() {
        return null;
    }

    /**
     * Gets the second corner.
     *
     * @return Location of second corner
     */
    @Override
    public Location getSecondCorner() {
        return null;
    }

    @Override
    public void setFirstCorner(Location loc) throws IllegalStateException {

    }

    @Override
    public void setSecondCorner(Location loc) throws IllegalStateException {

    }

    @Override
    public void setSpawn(@NotNull Location loc) throws IllegalStateException {
        
    }

    @Override
    public void setFinish(@NotNull Location loc) throws IllegalStateException {

    }

    @Override
    public void addCheckpoint(@NotNull Location loc) throws IllegalStateException {

    }

    @Override
    public void removeCheckpoint(@NotNull Location loc) throws IllegalStateException {

    }

    @Override
    public void setRate(double rate) {

    }

    @Override
    public @Nullable Location getSpawn() {
        return null;
    }

    @Override
    public @Nullable Location getFinish() {
        return null;
    }

    @Override
    public @Nullable Location getCheckpoint(int index) {
        return null;
    }

    @Override
    public double getRate() {
        return 0;
    }
}
