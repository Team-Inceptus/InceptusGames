package us.teaminceptus.games.bedwars.teams;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import us.teaminceptus.games.bedwars.Bedwars;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Bedwars Team
 */
public final class BedwarsTeam implements Iterable<Player> {

    private final Color color;
    private List<Player> teammates;
    private final int size;

    private BedwarsTeam(Color c, Bedwars.Type type) {
        this.size = type.getTeamSize();
        this.color = c;
    }

    public static BedwarsTeam red(@NotNull Bedwars.Type type) throws NullPointerException {
        Objects.requireNonNull(type);
        return new BedwarsTeam(Color.RED, type);
    }

    @NotNull
    @Override
    public Iterator<Player> iterator() {
        return teammates.iterator();
    }

    public Color getColor() {
        return this.color;
    }

    public int getSize() {
        return this.size;
    }
}
