package net.desiremc.robot.command;

import net.desiremc.SkyBlock;
import net.desiremc.robot.Robot;
import net.desiremc.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RobotCommandHandler implements CommandExecutor {

    private SkyBlock skyBlock;
    private Robot robot;

    public RobotCommandHandler(SkyBlock skyBlock){
        this.skyBlock = skyBlock;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender.hasPermission("skyblock.command.robot"))) {
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
                    String robotName = args[2];
                    if (args.length == 3) {
                        usage(player    );
                    } else {
                        int amount = Integer.parseInt(args[3]);
                        this.robot = new Robot(skyBlock, robotName);
                        this.robot.give(target, amount);
                    }
                }
            }
        } else if (args[0].equalsIgnoreCase("giveall")) {
            if (args.length == 1) {
                usage(player);
            } else {
                String robotName = args[1];
                if (args.length == 2) {
                    usage(player);
                } else {
                    int amount = Integer.parseInt(args[2]);
                    this.robot = new Robot(skyBlock, robotName);
                    this.robot.giveAll(amount);

                }
            }
        }
        return false;
    }

    void usage(Player player){

        player.sendMessage(CC.translate("&7&m-------------------------------------------------"));
        player.sendMessage(CC.translate("&c/robot give <player> <name> <amonut>"));
        player.sendMessage(CC.translate("&c/robot giveall <name> <amount>"));
        player.sendMessage(CC.translate("&7&m-------------------------------------------------"));
    }
}
