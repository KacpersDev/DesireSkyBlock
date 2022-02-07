package net.desiremc.spawn;

import net.desiremc.SkyBlock;
import net.desiremc.reload.ConfigReload;
import net.desiremc.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        if (!(sender.hasPermission("skyblock.command.setspawn"))) {
            sender.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("no-permissions")));
            return false;
        }

        Player player = (Player) sender;
        insert(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()
        , player.getLocation().getYaw(), player.getLocation().getPitch());
        player.sendMessage(CC.translate("&aYou have set a new spawn location."));
        return true;
    }

    private void insert(double x, double y, double z, double yaw, double pitch){

        SkyBlock.getInstance().getConfig().set("spawn.x", x);
        SkyBlock.getInstance().getConfig().set("spawn.y", y);
        SkyBlock.getInstance().getConfig().set("spawn.z", z);
        SkyBlock.getInstance().getConfig().set("spawn.yaw", yaw);
        SkyBlock.getInstance().getConfig().set("spawn.pitch", pitch);
        ConfigReload.reload();
    }
}
