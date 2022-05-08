package us.teaminceptus.games.bedwars;

import org.bukkit.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.teaminceptus.games.bedwars.teams.BedwarsTeam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Core Bedwras Game Manager
 */
public interface Bedwars {

    @NotNull
    Type getType();

    void setType(@NotNull Type t);

    @Nullable Map<Color, BedwarsTeam> getTeams();

    /**
     * Represents a Bedwars Game Type
     */
    enum Type {

        /**
         * 1v1
         */
        SOLO_2(2, 1, TWO()),
        /**
         * 1v1v1v1
         */
        SOLO_4(4, 1, FOUR()),
        /**
         * 1v1v1v1v1v1v1v1
         */
        SOLO_8(8, 1, EIGHT()),
        /**
         * 2v2
         */
        DUOS_2(2, 2, TWO()),
        /**
         * 2v2v2v2
         */
        DUOS_4(4, 2, FOUR()),
        /**
         * 2v2v2v2v2v2v2v2
         */
        DUOS_8(8, 2, EIGHT()),
        /**
         * 3v3
         */
        TRIO_2(2, 3, TWO()),
        /**
         * 3v3v3v3
         */
        TRIO_4(4, 3, FOUR()),
        /**
         * 4v4
         */
        QUAD_2(2, 4, TWO()),
        /**
         * 4v4v4v4
         */
        QUAD_4(4, 4, FOUR()),
        /**
         * 8v8
         */
        OCTA_2(2, 8, TWO()),
        ;

        private static List<Color> TWO() {
            return Arrays.asList(Color.RED, Color.BLUE);
        }

        private static List<Color> FOUR() {
            return Arrays.asList(Color.RED, Color.BLUE, Color.LIME, Color.YELLOW);
        }

        private static List<Color> EIGHT() {
            return Arrays.asList(Color.RED, Color.BLUE, Color.LIME, Color.YELLOW, Color.AQUA, Color.PURPLE, Color.SILVER, Color.BLACK);
        }

        private int teamAmount;
        private int teamSize;
        private List<Color> colors;

        Type(int teams, int teamSize, List<Color> colors) {
            this.teamAmount = teams;
            this.teamSize = teamAmount;
            this.colors = colors;
        }

        public int getTeamAmount() {
            return this.teamAmount;
        }

        public int getTeamSize() {
            return this.teamSize;
        }

        public List<Color> getColors() {
            return this.colors;
        }
    }

}
