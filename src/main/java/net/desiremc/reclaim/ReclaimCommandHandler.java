package net.desiremc.reclaim;

import net.desiremc.SkyBlock;
import net.desiremc.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReclaimCommandHandler implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        if (!(sender.hasPermission("skyblock.command.reclaim"))) {
            sender.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("no-permissions")));
            return false;
        }

        Player player = (Player) sender;


        return true;

    }
}
