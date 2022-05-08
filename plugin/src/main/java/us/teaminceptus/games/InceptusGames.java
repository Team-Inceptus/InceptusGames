package us.teaminceptus.games;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.BukkitCommandHandler;
import us.teaminceptus.games.floorislava.FloorIsLava;

/**
 * Core InceptusGames Plugin
 */
public final class InceptusGames extends JavaPlugin implements FloorIsLava, Listener {

    // Plugin Fields
    // Implementation Fields

    Location firstCorner;
    Location secondCorner;

    // Events

    public void setBlocks(PlayerInteractEvent e) {
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.LEFT_CLICK_BLOCK)) {
            Player p = e.getPlayer();

            ItemStack tool = e.getItem();

            if (tool == null) return;
            if (!tool.hasItemMeta() || !tool.getItemMeta().hasLore()) return;

            if (tool.getItemMeta().getLore().contains("Example lore here")) {

                if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
                    setFirstCorner(e.getClickedBlock().getLocation());
                }

                if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    setSecondCorner(e.getClickedBlock().getLocation());
                }

                // store the block coordinates, check to make sure both blocks have been set, then do arena logic

                if (secondCorner != null && firstCorner != null) {
                    //set arena



                }
            }

        }


    }

    // Plugin Logic

    public void onEnable() {
        BukkitCommandHandler handler = BukkitCommandHandler.create(this);

        handler.register(this);
        handler.registerBrigadier();
    }


    public void onDisable() {

    }

    // Commands & Implementation

    /**
     * Gets the first corner.
     *
     * @return Location of first corner
     */
    @Override
    public Location getFirstCorner() {
        return null;
    }

    /**
     * Gets the second corner.
     *
     * @return Location of second corner
     */
    @Override
    public Location getSecondCorner() {
        return null;
    }

    @Override
    public void startFloorIsLava() {

    }

    @Override
    public void endFloorIsLava() {

    }

    @Override
    public void setFirstCorner(Location loc) throws IllegalStateException {

    }

    @Override
    public void setSecondCorner(Location loc) throws IllegalStateException {

    }

    @Override
    public void setSpawn(@NotNull Location loc) throws IllegalStateException {
        
    }

    @Override
    public void setFinish(@NotNull Location loc) throws IllegalStateException {

    }

    @Override
    public void addCheckpoint(@NotNull Location loc) throws IllegalStateException {

    }

    @Override
    public void removeCheckpoint(@NotNull Location loc) throws IllegalStateException {

    }

    @Override
    public void setRate(double rate) {

    }

    @Override
    public @Nullable Location getSpawn() {
        return null;
    }

    @Override
    public @Nullable Location getFinish() {
        return null;
    }

    @Override
    public @Nullable Location getCheckpoint(int index) {
        return null;
    }

    @Override
    public double getRate() {
        return 0;
    }


}
