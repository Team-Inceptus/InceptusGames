package us.teaminceptus.games.floorislava;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import us.teaminceptus.games.floorislava.FloorIsLava.GameDifficulty;
import us.teaminceptus.games.util.GameBuilder;
import us.teaminceptus.games.util.GameManager;
import us.teaminceptus.games.util.Language;
import us.teaminceptus.games.util.WorldBox;

/**
 * Represents a Builder for a FloorIsLavaBuilder Game.
 */
public final class FloorIsLavaBuilder implements GameBuilder {
    
    private Set<UUID> participants = new HashSet<>();

    private FloorIsLavaBuilder() {}

    @Override
    public void addParticipant(@NotNull UUID uid) {
        participants.add(uid);
    }

    @Override
    public void removeParticipant(@NotNull UUID uid) {
        participants.remove(uid);
    }

    @Override
    public FloorIsLava start() throws IllegalArgumentException, UnsupportedOperationException {
        FileConfiguration config = FloorIsLava.loadConfiguration();
        ConfigurationSection setup = config.getConfigurationSection("setup");

        String diff = setup.getString("difficulty");

        if (!(setup.isString("difficulty"))) diff = "NORMAL";
        if (!(setup.isLocation("corner1"))) throw new IllegalArgumentException("Missing Corner #1");
        if (!(setup.isLocation("corner2"))) throw new IllegalArgumentException("Missing Corner #2");

        return new GameInstance(participants, GameDifficulty.valueOf(diff.toUpperCase()), setup.getLocation("corner1"), setup.getLocation("corner2"));
    };

    static class GameInstance implements FloorIsLava {

        private final Plugin plugin;
        private final FileConfiguration config;

        private final Set<UUID> participants;
        private final Location c1;
        private final Location c2;
        private final Location center;
        private final int startHeight;
        private final int finishHeight;
        private final GameDifficulty diff;
        private final Properties messages;
        private final WorldBox originalArea;

        private Set<UUID> remaining = new HashSet<>();
        private boolean running;

        {
            this.plugin = GameManager.getPlugin();
            Bukkit.getPluginManager().registerEvents(this, plugin);
            this.config = FloorIsLava.loadConfiguration();
        }

        private GameInstance(Set<UUID> players, GameDifficulty diff, Location c1, Location c2) {
            this.participants = players;
            this.c1 = c1;
            this.c2 = c2;
            this.diff = diff;
            this.center = new Location(c1.getWorld(), (c1.getX() + c2.getX()) / 2, (c1.getY() + c2.getY()) / 2, (c1.getZ() + c2.getZ()) / 2);
            this.remaining.addAll(participants);
            this.messages = Language.getCurrentLanguage("floorislava");
            this.running = true;
            this.startHeight = c1.getBlockY() > c2.getBlockY() ? c2.getBlockY() : c1.getBlockY();
            this.finishHeight = c1.getBlockY() > c2.getBlockY() ? c1.getBlockY() : c2.getBlockY();
            this.originalArea = new WorldBox(c1, c2);

            RUNNABLE.runTask(plugin);
        }

        private BukkitRunnable RUNNABLE = new BukkitRunnable() {

            public void run() {
                for (UUID p : remaining) Bukkit.getPlayer(p).sendTitle(getProp(messages, "start", ChatColor.GREEN + "Start!"), "", 10, 70, 20);

                new BukkitRunnable() {
                    int currentY = startHeight;

                    public void run() {
                        if (!(running)) cancel();
                        if (currentY == finishHeight) {
                            stop();
                            cancel();
                            return;
                        }

                        WorldBox area = new WorldBox(new Location(getCurrentWorld(), c1.getX(), currentY, c1.getZ()), new Location(getCurrentWorld(), c2.getX(), currentY, c2.getZ()));

                        for (Block b : area) {
                            if (b.getType() == Material.AIR) b.setType(Material.LAVA);
                        }

                        currentY++;
                    }
                }.runTaskTimer(plugin, 40, diff.getTickRate());
            }
        };

        @EventHandler
        public void onDeath(EntityDamageEvent e) {
            if (!(e.getEntity() instanceof Player p)) return;
            if (!(remaining.contains(p.getUniqueId()))) return;

            if (p.getHealth() - e.getFinalDamage() < 0) {
                e.setCancelled(true);
                remaining.remove(p.getUniqueId());

                p.teleport(config.getLocation("out", p.getBedSpawnLocation()));
            }
            

        }

        public void checkParticipants() {
            if (remaining.size() < 1) {
                for (UUID uid : participants) Bukkit.getPlayer(uid).sendTitle(getProp(messages, "all_out", ChatColor.RED + "Everybody is out! Cancelling..."), "", 10, 70, 20);
                stop();
            }
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
            for (Block b : originalArea) if (b.getType() == Material.LAVA) b.setType(Material.AIR);
            for (UUID uid : participants) Bukkit.getPlayer(uid).sendMessage(getProp(messages, "reset", ChatColor.GREEN + "Reset successful."));
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
        public GameDifficulty getDifficulty() {
            return this.diff;
        }

    }

}
