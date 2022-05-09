package us.teaminceptus.games.floorislava;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import us.teaminceptus.games.util.GameBuilder;
import us.teaminceptus.games.util.GameManager;

/**
 * Represents a Builder for a FloorIsLavaBuilder Game.
 */
public final class FloorIsLavaBuilder implements GameBuilder {
    
    private Set<UUID> participants = new HashSet<>();
    private Location corner1;
    private Location corner2;
    private Location center;
    private long tickRate = FloorIsLava.NORMAL_TICKRATE;

    private FloorIsLavaBuilder() {}

    @Override
    public void addParticipant(@NotNull UUID uid) {
        participants.add(uid);
    }

    @Override
    public void removeParticipant(@NotNull UUID uid) {
        participants.remove(uid);
    }

    public void setFirstCorner(@NotNull Location loc) {
        this.corner1 = loc;
    }

    public void setSecondCorner(@NotNull Location loc) {
        this.corner2 = loc;
    }

    public void setCenter(@NotNull Location center) {
        this.center = center;
    }

    public void setTickRate(long tick) {
        this.tickRate = tick;
    }

    @Override
    public FloorIsLava start() throws IllegalArgumentException, UnsupportedOperationException {
        if (center == null) throw new IllegalArgumentException("Missing center");
        if (corner1 == null) throw new IllegalArgumentException("Missing corner #1");
        if (corner2 == null) throw new IllegalArgumentException("Missing corner #2");

        if (!(center.getWorld().getUID().equals(corner1.getWorld().getUID()))) throw new IllegalArgumentException("Differing worlds: center and corner 1");
        if (!(center.getWorld().getUID().equals(corner2.getWorld().getUID()))) throw new IllegalArgumentException("Differing worlds: center and corner 2");
        if (!(corner1.getWorld().getUID().equals(corner2.getWorld().getUID()))) throw new IllegalArgumentException("Differing worlds: corner 1 and corner 2");

        return new GameInstance(participants, tickRate, corner1, corner2);
    };

    private static class GameInstance implements FloorIsLava, Listener {

        private final Plugin plugin;

        private final Set<UUID> participants;
        private final Location c1;
        private final Location c2;
        private final Location center;
        private final int startHeight;
        private final int finishHeight;
        private final long tickRate;

        private Set<UUID> remaining = new HashSet<>();
        private boolean running;

        {
            this.plugin = GameManager.getPlugin();
            Bukkit.getPluginManager().registerEvents(this, plugin);
        }

        private GameInstance(Set<UUID> players, long tickRate, Location c1, Location c2) {
            this.participants = players;
            this.c1 = c1;
            this.c2 = c2;
            this.tickRate = tickRate;
            this.center = new Location(c1.getWorld(), (c1.getX() + c2.getX()) / 2, (c1.getY() + c2.getY()) / 2, (c1.getZ() + c2.getZ()) / 2);

            this.running = true;
            this.startHeight = c1.getBlockY() > c2.getBlockY() ? c2.getBlockY() : c1.getBlockY();
            this.finishHeight = c1.getBlockY() > c2.getBlockY() ? c1.getBlockY() : c2.getBlockY();
        }

        @Override
        public boolean running() {
            return this.running;
        }

        @Override
        public Set<Player> getParticipants() {
            Set<Player> p = new HashSet<>();
            for (UUID uid : participants) p.add(Bukkit.getPlayer(uid));
            return p;
        }

        @Override
        public Set<Player> getRemainingPlayers() {
            Set<Player> p = new HashSet<>();
            for (UUID uid : remaining) p.add(Bukkit.getPlayer(uid));
            return p;
        }

        @Override
        public void stop() {
            running = false;
        }

        @Override
        public World getCurrentWorld() {
            return center.getWorld();
        }

        @Override
        public Location getCenter() {
            return this.center;
        }

        @Override
        public Location getFirstCorner() {
            return this.c1;
        }

        @Override
        public Location getSecondCorner() {
            return this.c2;
        }

        @Override
        public int getStartHeight() {
            return this.startHeight;
        }

        @Override
        public int getStopHeight() {
            return this.finishHeight;
        }

        @Override
        public long getTickRate() {
            return this.tickRate;
        }

    }

}
