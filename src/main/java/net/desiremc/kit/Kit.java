package net.desiremc.kit;

import lombok.Getter;
import net.desiremc.SkyBlock;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public class Kit {

    private final String kitName;
    private final SkyBlock skyBlock;

    public Kit(SkyBlock skyBlock, String kitName){
        this.skyBlock = skyBlock;
        this.kitName = kitName;
    }

    public void redeemKit(Player player){

        if (this.kitName.equalsIgnoreCase("starter")) {
            ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
            ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
            ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
            ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);

            helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
            chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            chestplate.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
            leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
            boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            boots.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
            sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
            sword.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
            pickaxe.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
            pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 5);

            player.getInventory().addItem(helmet);
            player.getInventory().addItem(chestplate);
            player.getInventory().addItem(leggings);
            player.getInventory().addItem(boots);
            player.getInventory().addItem(sword);
            player.getInventory().addItem(pickaxe);
        }
    }
}
