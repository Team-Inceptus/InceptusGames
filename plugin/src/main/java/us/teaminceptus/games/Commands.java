package us.teaminceptus.games;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import us.teaminceptus.games.util.WorldBox;

import java.util.Iterator;

public class Commands implements CommandExecutor {

    InceptusGames plugin;
    public Commands(InceptusGames plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {


        if(!(sender instanceof Player player)) return false;

        //op commands

        if(player.isOp()) {

            if(cmd.getName().equalsIgnoreCase("setlarena")) {
                if(args.length < 6) {
                    player.sendMessage("§cPlease provide the valid number of coordinates in this format: x1 y1 z1 x2 y2 z2");
                    return false;
                }
                if(args.length > 6) {
                    player.sendMessage("§cThat's too many arguments!");
                }
                //convert arguements to the corner locations
                Location c1 = new Location(player.getWorld(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                Location c2 = new Location(player.getWorld(), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]));

                WorldBox wb = new WorldBox(c1, c2);
                Iterator<Block> arena = wb.iterator();

            }

        }


        return false;
    }
}
