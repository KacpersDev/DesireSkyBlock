package net.desiremc.data.command;

import net.desiremc.SkyBlock;
import net.desiremc.data.Data;
import net.desiremc.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {

    private final Data data = new Data();

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
        if (args.length == 0) {
            usage(player);
        } else {
            if (args[0].equalsIgnoreCase("check")) {
                if (player.hasPermission("skyblock.command.balance.check")) {
                    int amount = data.getBalance(player);
                    player.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("balance.check")
                            .replace("%player%", player.getName())
                            .replace("%balance%", String.valueOf(amount))));
                }
            } else if (args[0].equalsIgnoreCase("checkplayer")) {
                if (player.hasPermission("skyblock.command.balance.checkplayer")) {
                    if (args.length == 1) {
                        usage(player);
                    } else {
                        Player target = Bukkit.getPlayerExact(args[1]);
                        int amount = data.getBalance(target);
                        player.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("balance.checkplayer")
                                .replace("%player%", target.getName())
                                .replace("%balance%", String.valueOf(amount))));
                    }
                }
            } else if (args[0].equalsIgnoreCase("add")) {
                if (player.hasPermission("skyblock.command.balance.add")) {
                    if (args.length == 1) {
                        usage(player);
                    } else {
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if (args.length == 2) {
                            usage(player);
                        } else {
                            int amount = Integer.parseInt(args[2]);
                            data.addBalance(target, amount);
                            player.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("balance.add")
                                    .replace("%player%", target.getName())
                                    .replace("%balance%", String.valueOf(amount))));
                        }
                    }
                }
            } else if (args[0].equalsIgnoreCase("set")) {
                if (player.hasPermission("skyblock.command.set")) {
                    if (args.length == 1) {
                        usage(player);
                    } else {
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if (args.length == 2) {
                            usage(player);
                        } else {
                            int amount = Integer.parseInt(args[2]);
                            data.setBalance(target, amount);
                            player.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("balance.set")
                                    .replace("%player%", target.getName())
                                    .replace("%amount%", String.valueOf(amount))));
                        }
                    }
                }
            } else if (args[0].equalsIgnoreCase("remove")) {
                if (args.length == 1) {
                    usage(player);
                } else {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (args.length == 2) {
                        usage(player);
                    } else {
                        int amount = Integer.parseInt(args[2]);
                        data.removeBalance(target, player, amount);
                        player.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("balance.remove")
                                .replace("%player%", target.getName())
                                .replace("%amount%", String.valueOf(amount))));
                    }
                }
            }
        }

        return true;
    }

    private void usage(Player player){

        for (final String i : SkyBlock.getInstance().getConfig().getStringList("balance.usage")) {
            player.sendMessage(CC.translate(i));
        }
    }
}
