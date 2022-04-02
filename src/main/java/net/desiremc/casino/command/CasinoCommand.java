package net.desiremc.casino.command;

import lombok.Getter;
import net.desiremc.SkyBlock;
import net.desiremc.casino.Casino;
import net.desiremc.data.Data;
import net.desiremc.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

@Getter
public class CasinoCommand implements CommandExecutor {

    private SkyBlock skyBlock;
    private Casino casino;
    private int amount;
    private Data data;
    public static HashMap<Player, Integer> a = new HashMap<>();

    public CasinoCommand(SkyBlock skyBlock){
        this.skyBlock = skyBlock;
        this.data = new Data();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        if (!(sender.hasPermission("skyblock.command.casino"))) {
            sender.sendMessage(CC.translate(skyBlock.getConfig().getString("no-permissions")));
            return false;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            usage(player);
        } else if (args[0].equalsIgnoreCase("bet")) {
            if (args.length == 1) {
                usage(player);
            } else {
                int amount = Integer.parseInt(args[1]);

                if (amount < 100) {
                    player.sendMessage(CC.translate("&cYou cannot bet a amount lower then 100"));
                    return false;
                }
                this.amount = amount;
                if (data.getBalance(player) >= this.amount) {
                    data.removeBalance(player, player, this.amount);
                    a.put(player, amount);
                    this.casino = new Casino(CC.translate("&aCasino"), 36);
                    this.casino.open(player);
                } else {
                    player.sendMessage(CC.translate("&cYou don't have that much balance."));
                }
            }
        }

        return true;
    }

    void usage(Player player){

        player.sendMessage(CC.translate("&7&m------------------------------------------"));
        player.sendMessage(CC.translate("&cWrong usage - /casino bet <amount>"));
        player.sendMessage(CC.translate("&7&m------------------------------------------"));
    }
}
