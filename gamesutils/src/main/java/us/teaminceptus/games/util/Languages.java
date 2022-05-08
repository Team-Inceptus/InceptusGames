package us.teaminceptus.games.util;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Properties;

/**
 * Languages Util
 */
public final class Languages {

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
         * Gets a Message.
         * @param key Message to use
         * @return Found message
         * @throws IllegalStateException if an I/O Exception occurs
         */
        public String getMessage(@NotNull Message key) throws IllegalStateException {
            Properties p = new Properties();

            try {
                p.load(getClass().getClassLoader().getResourceAsStream("messages-" + this.suffix + ".properties"));
                return p.getProperty(key.key);
            }
            catch (IOException e) {
                throw new IllegalStateException(e.getMessage());
            }
        }

    }

    /**
     * Message for each property in language files
     *
     */
    public enum Message {

        ;

        private final String key;

        Message(String key) {
            this.key = key;
        }

        public String getKey() {
            return this.key;
        }
    }

}
