package net.desiremc.crates.command;

import lombok.Getter;
import net.desiremc.SkyBlock;
import net.desiremc.crates.Crate;
import net.desiremc.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Getter
public class CrateCommandHandler implements CommandExecutor {

    private final SkyBlock skyBlock;
    private Crate crate;

    public CrateCommandHandler(SkyBlock skyBlock){
        this.skyBlock = skyBlock;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender.hasPermission("skyblock.command.crate"))) {
            sender.sendMessage(CC.translate(skyBlock.getConfig().getString("no-permissions")));
            return false;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            usage(player);
        } else if (args[0].equalsIgnoreCase("give")) {
            if (args.length == 1) {
                usage(player);
            } else {
                Player target = Bukkit.getPlayerExact(args[1]);
                if (args.length == 2) {
                    usage(player);
                } else {
                    String name = args[2];
                    if (args.length == 3) {
                        usage(player);
                    } else {
                        int amount = Integer.parseInt(args[3]);
                        this.crate = new Crate(skyBlock, name);
                        this.crate.givePlayer(target, amount);
                    }
                }
            }
        } else if (args[0].equalsIgnoreCase("giveall")) {
            if (args.length == 1) {
                usage(player);
            } else {
                String name = args[1];
                if (args.length == 2) {
                    usage(player);
                } else {
                    int amount = Integer.parseInt(args[2]);
                    this.crate = new Crate(skyBlock, name);
                    this.crate.giveAll(player, amount);
                }
            }
        }


        return true;
    }

    void usage(Player player) {
        player.sendMessage(CC.translate("&7&m-------------------------------------------------------------"));
        player.sendMessage(CC.translate("&c/crate give <player> <name> <amount>"));
        player.sendMessage(CC.translate("&c/crate giveall <name> <amount>"));
        player.sendMessage(CC.translate("&cavailable crates - rank, legendary, basic, op, seasonal"));
        player.sendMessage(CC.translate("&7&m-------------------------------------------------------------"));
    }
}
