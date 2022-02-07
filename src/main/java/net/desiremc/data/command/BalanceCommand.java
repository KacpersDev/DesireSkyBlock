package net.desiremc.data.command;

import net.desiremc.SkyBlock;
import net.desiremc.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        if (!(sender.hasPermission("skyblock.command.balance"))) {
            sender.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("no-permissions")));
            return false;
        }

        Player player = (Player) sender;


        return true;
    }
}
