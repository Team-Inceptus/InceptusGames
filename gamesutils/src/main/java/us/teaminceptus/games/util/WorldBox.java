package us.teaminceptus.games.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Util Class for looping through Bounding Boxes
 */
public final class WorldBox implements Iterable<Block>  {

    private final double minX;
    private final double minY;
    private final double minZ;
    private final double maxX;
    private final double maxY;
    private final double maxZ;
    private final World world;

    public WorldBox(@NotNull BoundingBox box, @NotNull World w) {
        this.world = w;
        this.minX = box.getMinX();
        this.minY = box.getMinY();
        this.minZ = box.getMinZ();
        this.maxX = box.getMaxX();
        this.maxY = box.getMaxY();
        this.maxZ = box.getMaxZ();
    }

    public WorldBox(@NotNull World w, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.world = w;
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    public WorldBox(@NotNull Location c1, @NotNull Location c2) throws IllegalArgumentException {
        if (!(c1.getWorld().getUID().equals(c2.getWorld().getUID()))) throw new IllegalArgumentException("Differing worlds");

        this.world = c1.getWorld();
        BoundingBox box = new BoundingBox(c1.getX(), c1.getY(), c1.getZ(), c2.getX(), c2.getY(), c2.getZ());
        this.minX = box.getMinX();
        this.minY = box.getMinY();
        this.minZ = box.getMinZ();
        this.maxX = box.getMaxX();
        this.maxY = box.getMaxY();
        this.maxZ = box.getMaxZ();
    }

    @NotNull
    public BoundingBox getBoundingBox() {
        return new BoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
    }

    @NotNull
    public World getWorld() {
        return this.world;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<Block> iterator() {
        List<Block> blocks = new ArrayList<>();

        BoundingBox box = new BoundingBox(minX, minY, minZ, maxX, maxY, maxZ);

        for (double x = box.getMinX(); x < box.getMaxX(); x++) {
            for (double y = box.getMinY(); y < box.getMaxY(); y++) {
                for (double z = box.getMinZ(); z < box.getMinZ(); z++) {
                    blocks.add(new Location(world, x, y, z).getBlock());
                }
            }
        }

        return blocks.iterator();
    }

}
