package net.desiremc.staff;

import net.desiremc.utils.CC;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StaffMode {

    public void activate(Player player){
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.setGameMode(GameMode.CREATIVE);
        ItemStack clock = new ItemStack(Material.WATCH);
        ItemMeta clockItemMeta = clock.getItemMeta();
        clockItemMeta.setDisplayName(CC.translate("&cRandom Teleport"));
        clock.setItemMeta(clockItemMeta);

        ItemStack inspector = new ItemStack(Material.BOOK);
        ItemMeta inspectorItemMeta = inspector.getItemMeta();
        inspectorItemMeta.setDisplayName(CC.translate("&cInspect Inventory"));
        inspector.setItemMeta(inspectorItemMeta);

        ItemStack freezer = new ItemStack(Material.ICE);
        ItemMeta freezeItemMeta = freezer.getItemMeta();
        freezeItemMeta.setDisplayName(CC.translate("&cFreeze Player"));
        freezer.setItemMeta(freezeItemMeta);

        ItemStack vanish = new ItemStack(Material.RED_ROSE);
        ItemMeta vanishItemMeta = vanish.getItemMeta();
        vanishItemMeta.setDisplayName(CC.translate("&cVanish"));
        vanish.setItemMeta(vanishItemMeta);

        player.getInventory().setItem(8, vanish);
        player.getInventory().setItem(6, freezer);
        player.getInventory().setItem(2, inspector);
        player.getInventory().setItem(0, clock);
    }

    public void deactivate(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.setGameMode(GameMode.SURVIVAL);

    }
}
