package net.desiremc.staff.implement;

import net.desiremc.SkyBlock;
import net.desiremc.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishCommand implements CommandExecutor {

    public ArrayList<Player> vanish = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        if (!(sender.hasPermission("skyblock.command.vanish"))) {
            sender.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("no-permissions")));
            return false;
        }

        Player player = (Player) sender;
        if (vanish.contains(player)) {
            vanish.remove(player);
            for (final Player online : Bukkit.getOnlinePlayers()) {
                online.showPlayer(player);
                player.sendMessage(CC.translate("&cVanish has been disabled"));
            }
        } else {
            vanish.add(player);
            for (final Player online : Bukkit.getOnlinePlayers()) {
                if (!online.hasPermission("skyblock.command.vanish")) {
                    online.hidePlayer(player);
                    player.sendMessage(CC.translate("&aVanish has been enabled"));
                }
            }
        }

        return true;
    }
}
