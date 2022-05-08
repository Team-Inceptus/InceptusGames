package us.teaminceptus.games;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.teaminceptus.games.floorislava.FloorIsLava;

class Commands implements FloorIsLava, Listener {

    protected InceptusGames plugin;
    
    public Commands(InceptusGames plugin) {
        this.plugin = plugin;
        plugin.handler.register(this);
    }



    @EventHandler

    public void setBlocks(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            Player p = e.getPlayer();


            ItemStack tool = p.getItemInUse();

            if(tool == null) return;

            if(!tool.hasItemMeta() || !tool.getItemMeta().hasLore()) return;

        }


    }

    public void setFirstCorner(@NotNull Location loc) throws IllegalStateException {

    }


    public void setSecondCorner(@NotNull Location loc) throws IllegalStateException {

    }


    public void setSpawn(@NotNull Location loc) throws IllegalStateException {

    }


    public void setFinish(@NotNull Location loc) throws IllegalStateException {

    }


    public void addCheckpoint(@NotNull Location loc) throws IllegalStateException {

    }

    public void removeCheckpoint(@NotNull Location loc) throws IllegalStateException {

    }


    public void setRate(double rate) {

    }


    public @Nullable Location getSpawn() {
        return null;
    }


    public @Nullable Location getFinish() {
        return null;
    }


    public @Nullable Location getCheckpoint(int index) {
        return null;
    }


    public double getRate() {
        return 0;
    }


    public @NotNull Location getFirstCorner() {
        return null;
    }


    public @NotNull Location getSecondCorner() {
        return null;
    }
}
