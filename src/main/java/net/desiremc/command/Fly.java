package net.desiremc.command;

import net.desiremc.SkyBlock;
import net.desiremc.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Fly implements CommandExecutor {

    private ArrayList<Player> fly = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        if (!(sender.hasPermission("skyblock.command.fly"))) {
            sender.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("no-permissions")));
            return false;
        }
        Player player = (Player) sender;
        if (!fly.contains(player)) {
            player.setAllowFlight(true);
            player.setFlying(true);
            fly.add(player);
            player.sendMessage(CC.translate("&aFly has been enabled"));
        } else {
            fly.remove(player);
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(CC.translate("&cFly has been disabled"));
        }
        return true;
    }
}
