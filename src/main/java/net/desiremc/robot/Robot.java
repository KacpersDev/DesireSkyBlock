package net.desiremc.robot;

import lombok.Getter;
import net.desiremc.SkyBlock;
import net.desiremc.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Locale;

@Getter
public class Robot {

    private final String robotName;
    private final SkyBlock skyBlock;

    public Robot(SkyBlock skyblock, String robotName){
        this.robotName = robotName;
        this.skyBlock = skyblock;
    }

    public void give(Player player, int amount){

        ItemStack robot = new ItemStack(Material.valueOf(skyBlock.getConfig().getString("robot." + this.robotName + ".item")));
        ItemMeta meta = robot.getItemMeta();
        meta.setDisplayName(CC.translate(skyBlock.getConfig().getString("robot." + this.robotName + ".name")));
        ArrayList<String> lore = new ArrayList<>();
        for (final String l : skyBlock.getConfig().getStringList("robot." + this.robotName + ".lore")) {
            lore.add(CC.translate(l));
        }

        meta.setLore(lore);
        robot.setItemMeta(meta);
        robot.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        robot.setAmount(amount);
        player.getInventory().addItem(robot);
    }

    public void giveAll(int amount) {

        ItemStack robot = new ItemStack(Material.valueOf(skyBlock.getConfig().getString("robot." + this.robotName + ".item")));
        ItemMeta meta = robot.getItemMeta();
        meta.setDisplayName(CC.translate(skyBlock.getConfig().getString("robot." + this.robotName + ".name")));
        ArrayList<String> lore = new ArrayList<>();
        for (final String l : skyBlock.getConfig().getStringList("robot." + this.robotName + ".lore")) {
            lore.add(CC.translate(l));
        }

        meta.setLore(lore);
        robot.setItemMeta(meta);

        robot.addUnsafeEnchantment(Enchantment.DURABILITY, 10);

        robot.setAmount(amount);

        for (final Player online : Bukkit.getOnlinePlayers()) {
            online.getInventory().addItem(robot);
        }
    }
}
