package net.desiremc.crates;

import lombok.Getter;
import net.desiremc.SkyBlock;
import net.desiremc.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

@Getter
public class Crate {

    private final String crateName;
    private final SkyBlock skyBlock;

    public Crate(SkyBlock skyBlock, String crateName) {
        this.skyBlock = skyBlock;
        this.crateName = crateName;
    }

    public void givePlayer(Player player, int amount) {

        if (crateName.equalsIgnoreCase("rank")) {
            rankCrate(player, amount);
        } else if (crateName.equalsIgnoreCase("legendary")) {
            legendaryCrate(player, amount);
        } else if (crateName.equalsIgnoreCase("basic")) {
            basicCrate(player, amount);
        } else if (crateName.equalsIgnoreCase("op")) {
            opCrate(player, amount);
        } else if (crateName.equalsIgnoreCase("seasonal")) {
            seasonalCrate(player, amount);
        } else {
            player.sendMessage(CC.translate("&cWrong Usage - available crates - rank, legendary, basic, op, seasonal"));
        }
    }

    public void giveAll(Player player, int amount) {

        if (crateName.equalsIgnoreCase("rank")) {
            rankCrateAll(amount);
        } else if (crateName.equalsIgnoreCase("legendary")) {
            legendaryCrateAll(amount);
        } else if (crateName.equalsIgnoreCase("basic")) {
            basicCrateAll(amount);
        } else if (crateName.equalsIgnoreCase("op")) {
            opCrateAll(amount);
        } else if (crateName.equalsIgnoreCase("seasonal")) {
            seasonalCrateAll(amount);
        } else {
            player.sendMessage(CC.translate("&cWrong Usage - available crates - rank, legendary, basic, op, seasonal"));
        }
    }

    private void rankCrate(Player player, int amount){

        ItemStack rank = new ItemStack(Material.ENDER_PORTAL_FRAME);
        rank.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        ItemMeta meta = rank.getItemMeta();
        meta.setDisplayName(CC.translate("&4Rank &fBox"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(CC.translate("&7&m--------------------------"));
        lore.add(CC.translate("&7&oPlace on ground to open"));
        lore.add(CC.translate("&7&m--------------------------"));
        meta.setLore(lore);
        rank.setItemMeta(meta);

        rank.setAmount(amount);
        player.getInventory().addItem(rank);
    }

    private void rankCrateAll(int amount){

        ItemStack rank = new ItemStack(Material.ENDER_PORTAL_FRAME);
        rank.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        ItemMeta meta = rank.getItemMeta();
        meta.setDisplayName(CC.translate("&4Rank &fBox"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(CC.translate("&7&m--------------------------"));
        lore.add(CC.translate("&7&oPlace on ground to open"));
        lore.add(CC.translate("&7&m--------------------------"));
        meta.setLore(lore);
        rank.setItemMeta(meta);

        rank.setAmount(amount);
        for (final Player online : Bukkit.getOnlinePlayers()) {
            online.getInventory().addItem(rank);
        }
    }

    private void legendaryCrate(Player player, int amount){

        ItemStack item = new ItemStack(Material.ENDER_CHEST);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate("&6Legendary &fBox"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(CC.translate("&7&m--------------------------"));
        lore.add(CC.translate("&7&oPlace on ground to open"));
        lore.add(CC.translate("&7&m--------------------------"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        item.setAmount(amount);
        player.getInventory().addItem(item);
    }

    private void legendaryCrateAll(int amount){

        ItemStack item = new ItemStack(Material.ENDER_CHEST);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate("&6Legendary &fBox"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(CC.translate("&7&m--------------------------"));
        lore.add(CC.translate("&7&oPlace on ground to open"));
        lore.add(CC.translate("&7&m--------------------------"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        item.setAmount(amount);
        for (final Player online : Bukkit.getOnlinePlayers()){
            online.getInventory().addItem(item);
        }
    }

    private void basicCrate(Player player, int amount){

        ItemStack item = new ItemStack(Material.CHEST);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate("&7Basic &fBox"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(CC.translate("&7&m--------------------------"));
        lore.add(CC.translate("&7&oPlace on ground to open"));
        lore.add(CC.translate("&7&m--------------------------"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        item.setAmount(amount);
        player.getInventory().addItem(item);
    }

    private void basicCrateAll(int amount){

        ItemStack item = new ItemStack(Material.CHEST);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate("&7Basic &fBox"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(CC.translate("&7&m--------------------------"));
        lore.add(CC.translate("&7&oPlace on ground to open"));
        lore.add(CC.translate("&7&m--------------------------"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        item.setAmount(amount);
        for (final Player online : Bukkit.getOnlinePlayers()){
            online.getInventory().addItem(item);
        }
    }

    private void opCrate(Player player, int amount){

        ItemStack item = new ItemStack(Material.ENCHANTMENT_TABLE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate("&cOP &fBox"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(CC.translate("&7&m--------------------------"));
        lore.add(CC.translate("&7&oPlace on ground to open"));
        lore.add(CC.translate("&7&m--------------------------"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        item.setAmount(amount);
        player.getInventory().addItem(item);
    }

    private void opCrateAll(int amount) {

        ItemStack item = new ItemStack(Material.ENCHANTMENT_TABLE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate("&cOP &fBox"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(CC.translate("&7&m--------------------------"));
        lore.add(CC.translate("&7&oPlace on ground to open"));
        lore.add(CC.translate("&7&m--------------------------"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        item.setAmount(amount);
        for (final Player online : Bukkit.getOnlinePlayers()){
            online.getInventory().addItem(item);
        }
    }

    private void seasonalCrate(Player player, int amount){

        ItemStack item = new ItemStack(Material.BEACON);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate("&dSEASONAL &fBox"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(CC.translate("&7&m--------------------------"));
        lore.add(CC.translate("&7&oPlace on ground to open"));
        lore.add(CC.translate("&7&m--------------------------"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        item.setAmount(amount);
        player.getInventory().addItem(item);
    }

    private void seasonalCrateAll(int amount) {
        ItemStack item = new ItemStack(Material.BEACON);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate("&dSEASONAL &fBox"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(CC.translate("&7&m--------------------------"));
        lore.add(CC.translate("&7&oPlace on ground to open"));
        lore.add(CC.translate("&7&m--------------------------"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        item.setAmount(amount);
        for (final Player online : Bukkit.getOnlinePlayers()){
            online.getInventory().addItem(item);
        }
    }

}
