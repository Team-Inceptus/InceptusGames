package us.teaminceptus.games.duels;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Core Duels Module Game Manager
 */
public interface Duels {

    /**
     * Fetches the Duels instance.
     * @return Duels Instance
     */
    static Duels getDuels() {
        return (Duels) Bukkit.getPluginManager().getPlugin("InceptusGames");
    }

    boolean runningDuelsGame();

    List<Player> getDuelsCombatants();

    @Nullable
    Type getDuelsType();

    void setDuelsType(@NotNull Type type);

    /**
     * Duels Game Type
     */
    enum Type {

        SOLO_2(0, 1),
        DUOS_2(1, 2),
        TRIOS_2(2, 3),
        QUAD_2(3, 4),

        ;

        private final int id;
        private final int teamSize;

        private Type(int id, int teamSize) {
            this.id = id;
            this.teamSize = teamSize;
        }

        public int getId() {
            return this.id;
        }

        public int getTeamSize() {
            return this.teamSize;
        }
    }

}
