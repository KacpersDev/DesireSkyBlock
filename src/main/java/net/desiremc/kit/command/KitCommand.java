package net.desiremc.kit.command;

import net.desiremc.SkyBlock;
import net.desiremc.utils.CC;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitCommand implements CommandExecutor {

    private SkyBlock skyBlock;

    public KitCommand(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        if (!(sender.hasPermission("skyblock.command.kit"))) {
            sender.sendMessage(CC.translate(skyBlock.getConfig().getString("no-permissions")));
            return false;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            usage(player);
        } else if (args[0].equalsIgnoreCase("pickaxe")) {
            ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
            pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 5);
            pickaxe.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().addItem(pickaxe);
        }

        return true;
    }

    void usage(Player player){

        player.sendMessage(CC.translate("&7&m-------------------------"));
        player.sendMessage(CC.translate("&cAvailable kits: pickaxe"));
        player.sendMessage(CC.translate("&7&m-------------------------"));
    }
}
