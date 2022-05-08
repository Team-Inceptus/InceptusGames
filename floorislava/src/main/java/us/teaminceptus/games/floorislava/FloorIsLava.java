package us.teaminceptus.games.floorislava;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Core FloorIsLava Game Manager */
public interface FloorIsLava {

    /*
    @NotNull

    default BoundingBox getBox() {
        return new BoundingBox(getFirstCorner().getX(), getFirstCorner().getY(), getFirstCorner().getZ(), getSecondCorner().getX(), getSecondCorner().getY(), getSecondCorner().getZ());
    }
    */
    /**
     * @param loc
     * @throws IllegalStateException
     * @return
     */
    void setFirstCorner(@NotNull Location loc) throws IllegalStateException;

    /**
     * Sets the second corner.
     * @param loc Location to set
     * @throws IllegalStateException if game has already started
     */
    void setSecondCorner(@NotNull Location loc) throws IllegalStateException;

    void setSpawn(@NotNull Location loc) throws IllegalStateException;

    void setFinish(@NotNull Location loc) throws IllegalStateException;

    void addCheckpoint(@NotNull Location loc) throws IllegalStateException;

    void removeCheckpoint(@NotNull Location loc) throws IllegalStateException;

    void setRate(double rate);

    @Nullable
    Location getSpawn();

    @Nullable
    Location getFinish();

    @Nullable
    Location getCheckpoint(int index);

    double getRate();

    /**
     * Gets the first corner of this FloorIsLava.
     * @return Location of first corner
     */
    @NotNull
    Location getFirstCorner();

    /**
     * Gets the second corner of this FloorIsLava.
     * @return Location of second corner
     */
    @NotNull
    Location getSecondCorner();

    void startFloorIsLava();

    void endFloorIsLava();

}