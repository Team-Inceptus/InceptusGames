package us.teaminceptus.games.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a Language
 */
public enum Language {

    /**
     * The English Language.
     */
    ENGLISH("en"),
    /**
     * The Spanish Language.
     */
    SPANISH("es"),
    ;

    private final String suffix;

    Language(String suffix) {
        this.suffix = suffix;
    }

    /**
     * Return the Language suffix.
     * @return Language Suffix
     */
    public String getSuffix() {
        return this.suffix;
    }

    /**
     * Fetches the current language used by the Plugin.
     * @param prefix Prefix of messages file.
     * @return Found Messages File, or empty Properties if not found.
     */
    public static Properties getCurrentLanguage(@NotNull String prefix) {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("InceptusGames");

        String language = plugin.getConfig().getString("language", "en");

        Properties p = new Properties();
        try {
            p.load(new FileInputStream(new File(plugin.getDataFolder(), prefix + "-" + language + ".properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return p;
    }

}
