package net.desiremc.spawn;

import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;
import net.desiremc.SkyBlock;
import net.desiremc.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        if (!(sender.hasPermission("skyblock.command.spawn"))) {
            sender.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("no-permissions")));
            return false;
        }

        Player player = (Player) sender;
        double x = SkyBlock.getInstance().getConfig().getDouble("spawn.x");
        double y = SkyBlock.getInstance().getConfig().getDouble("spawn.y");
        double z = SkyBlock.getInstance().getConfig().getDouble("spawn.z");

        player.teleport(new Location(Bukkit.getWorld("World"),x,y,z));
        player.sendMessage("Rank " + isPlayerInGroup(player, "owner"));
        return true;
    }

    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }
}
