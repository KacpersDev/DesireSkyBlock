package net.desiremc.data.command;

import net.desiremc.SkyBlock;
import net.desiremc.data.Data;
import net.desiremc.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GemsCommand implements CommandExecutor {

    private final Data data = new Data();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        if (!(sender.hasPermission("skyblock.command.gems"))) {
            sender.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("no-permissions")));
            return false;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            usage(player);
        } else {
            if (args[0].equalsIgnoreCase("check")) {
                if (player.hasPermission("skyblock.command.gems.check")) {
                    int amount = data.getGems(player);
                    player.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("gems.check")
                            .replace("%player%", player.getName())
                            .replace("%balance%", String.valueOf(amount))));
                }
            } else if (args[0].equalsIgnoreCase("checkplayer")) {
                if (player.hasPermission("skyblock.command.gems.checkplayer")) {
                    if (args.length == 1) {
                        usage(player);
                    } else {
                        Player target = Bukkit.getPlayerExact(args[1]);
                        int amount = data.getGems(target);
                        player.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("gems.checkplayer")
                                .replace("%player%", target.getName())
                                .replace("%balance%", String.valueOf(amount))));
                    }
                }
            } else if (args[0].equalsIgnoreCase("add")) {
                if (player.hasPermission("skyblock.command.gems.add")) {
                    if (args.length == 1) {
                        usage(player);
                    } else {
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if (args.length == 2) {
                            usage(player);
                        } else {
                            int amount = Integer.parseInt(args[2]);
                            data.addGems(target, amount);
                            player.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("gems.add")
                                    .replace("%player%", target.getName())
                                    .replace("%balance%", String.valueOf(amount))));
                        }
                    }
                }
            } else if (args[0].equalsIgnoreCase("set")) {
                if (player.hasPermission("skyblock.gems.set")) {
                    if (args.length == 1) {
                        usage(player);
                    } else {
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if (args.length == 2) {
                            usage(player);
                        } else {
                            int amount = Integer.parseInt(args[2]);
                            data.setGems(target, amount);
                            player.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("gems.set")
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
                        data.removeGems(target, player, amount);
                        player.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("gems.remove")
                                .replace("%player%", target.getName())
                                .replace("%amount%", String.valueOf(amount))));
                    }
                }
            }
        }

        return true;
    }

    private void usage(Player player){

        for (final String i : SkyBlock.getInstance().getConfig().getStringList("gems.usage")) {
            player.sendMessage(CC.translate(i));
        }
    }
}
