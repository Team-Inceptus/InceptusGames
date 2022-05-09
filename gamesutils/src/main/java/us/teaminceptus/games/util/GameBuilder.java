package us.teaminceptus.games.util;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a Game Builder for Implementation
 */
public interface GameBuilder {
    
    default void addParticipant(@NotNull Player p) {
        addParticipant(p.getUniqueId());
    }

    void addParticipant(@NotNull UUID uid);

    default void removeParticipant(@NotNull Player p) {
        removeParticipant(p.getUniqueId());
    }

    void removeParticipant(@NotNull UUID uid);
    
    /**
     * Stars the Game.
     * @return GameManager found with this Game
     * @throws IllegalArgumentException if an argument is missing
     * @throws UnsupportedOperationException if cannot be started
     */
    GameManager start() throws IllegalArgumentException, UnsupportedOperationException;

}
