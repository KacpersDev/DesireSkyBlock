package net.desiremc.casino;

import lombok.Getter;
import net.desiremc.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Getter
public class Casino {

    private final String title;
    private final int size;
    private Inventory inventory;

    public Casino(String title, int size){
        this.title = title;
        this.size = size;
    }

    public void open(Player player){

        this.inventory = Bukkit.createInventory(player, size, title);

        ItemStack emerald = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta emeraldMeta = emerald.getItemMeta();
        emeraldMeta.setDisplayName(CC.translate("&aConfirm Bet"));
        emerald.setItemMeta(emeraldMeta);

        ItemStack redstone = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta redstoneMeta = redstone.getItemMeta();
        redstoneMeta.setDisplayName(CC.translate("&cCancel Bet"));
        redstone.setItemMeta(redstoneMeta);

        inventory.setItem(12, emerald);
        inventory.setItem(15, redstone);

        player.openInventory(inventory);
    }
}
