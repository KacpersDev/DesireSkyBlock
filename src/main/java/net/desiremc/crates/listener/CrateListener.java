package net.desiremc.crates.listener;

import lombok.Getter;
import net.desiremc.SkyBlock;
import net.desiremc.data.Data;
import net.desiremc.robot.Robot;
import net.desiremc.spawner.Spawner;
import net.desiremc.utils.CC;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

@Getter
public class CrateListener implements Listener {

    private SkyBlock skyBlock;
    private Random random;
    private Inventory inventory;
    private Data data;

    public CrateListener(SkyBlock skyBlock){
        this.skyBlock = skyBlock;
        this.random = new Random();
        this.data = new Data();
    }

    @EventHandler
    public void onMove(InventoryClickEvent event){

        if (event.getInventory().getTitle().equalsIgnoreCase(CC.translate("&aInventory"))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onRankCrateOpen(BlockPlaceEvent event){

        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack handItem = player.getItemInHand();

        if (handItem == null || handItem.getItemMeta() == null || handItem.getItemMeta().getDisplayName() == null) return;
        if (handItem.getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&4Rank &fBox"))
        && handItem.getType() == Material.ENDER_PORTAL_FRAME && handItem.getItemMeta().hasEnchant(Enchantment.DURABILITY)) {
            Bukkit.getWorld(block.getWorld().getName()).playEffect(block.getLocation(), Effect.SMOKE, 1);
            Location location = block.getLocation();
            location.getBlock().setType(Material.AIR);
            inventory = Bukkit.createInventory(player, 27, CC.translate("&aInventory"));
            player.openInventory(inventory);
            ItemStack emerald = new ItemStack(Material.EMERALD_BLOCK);
            ItemMeta meta = emerald.getItemMeta();
            meta.setDisplayName(CC.translate("&aWin"));
            emerald.setItemMeta(meta);
            inventory.setItem(4, emerald);
            inventory.setItem(22, emerald); // 14
            player.updateInventory();



            new BukkitRunnable(){

                int i = 8;

                @Override
                public void run() {

                    i--;

                    if (i <= 0) {
                        player.closeInventory();
                        this.cancel();
                    }

                    if (i == 7) {
                        int randomNumber = random.nextInt(100);
                        random(player, randomNumber);
                    } else if (i == 6) {
                        int randomNumber = random.nextInt(100);
                        random(player, randomNumber);
                    } else if (i == 5) {
                        int randomNumber = random.nextInt(100);
                        random(player, randomNumber);
                    } else if (i == 4) {
                        int randomNumber = random.nextInt(100);
                        random(player, randomNumber);
                    } else if (i == 3) {
                        int randomNumber = random.nextInt(100);
                        random(player, randomNumber);
                    } else if (i == 2) {
                        int randomNumber = random.nextInt(100);
                        random(player, randomNumber);
                    } else if (i == 1) {
                        int randomNumber = random.nextInt(100);
                        random(player, randomNumber);
                        ItemStack item = inventory.getItem(13);
                        item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(CC.translate(inventory.getItem(13).getItemMeta().getDisplayName()));
                        item.setItemMeta(meta);
                        player.getInventory().addItem(item);
                    }
                }
            }.runTaskTimer(skyBlock, 0L,20L);
        }
    }

    void random(Player player, int randomNumber){
        if (randomNumber < 10) {
            ItemStack item = new ItemStack(Material.PAPER);
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(CC.translate("&4&lDesire &fRank"));
            item.setItemMeta(itemMeta);
            inventory.setItem(13, item);
            player.updateInventory();
        } else if (randomNumber > 10 && randomNumber < 25) {
            ItemStack item = new ItemStack(Material.PAPER);
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(CC.translate("&d&lEmperor &fRank"));
            item.setItemMeta(itemMeta);
            inventory.setItem(13, item);
            player.updateInventory();
        } else if (randomNumber > 25 && randomNumber < 50) {
            ItemStack item = new ItemStack(Material.PAPER);
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(CC.translate("&f&lAngel &fRank"));
            item.setItemMeta(itemMeta);
            inventory.setItem(13, item);
            player.updateInventory();
        } else if (randomNumber > 50 && randomNumber < 80) {
            ItemStack item = new ItemStack(Material.PAPER);
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(CC.translate("&e&lWizard &fRank"));
            item.setItemMeta(itemMeta);
            inventory.setItem(13, item);
            player.updateInventory();
        } else if (randomNumber > 80 && randomNumber < 100) {
            ItemStack item = new ItemStack(Material.PAPER);
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(CC.translate("&6&lDragon &fRank"));
            item.setItemMeta(itemMeta);
            inventory.setItem(13, item);
            player.updateInventory();
        }
    }

    @EventHandler
    public void LegendaryCrateOpen(BlockPlaceEvent event){

        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack handItem = player.getItemInHand();

        if (handItem == null || handItem.getItemMeta() == null || handItem.getItemMeta().getDisplayName() == null) return;
        if (handItem.getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&6Legendary &fBox"))
                && handItem.getType() == Material.ENDER_CHEST && handItem.getItemMeta().hasEnchant(Enchantment.DURABILITY)) {

            Bukkit.getWorld(block.getWorld().getName()).playEffect(block.getLocation(), Effect.SMOKE, 1);
            Location location = block.getLocation();
            location.getBlock().setType(Material.AIR);
            inventory = Bukkit.createInventory(player, 27, CC.translate("&aInventory"));
            player.openInventory(inventory);
            ItemStack emerald = new ItemStack(Material.EMERALD_BLOCK);
            ItemMeta meta = emerald.getItemMeta();
            meta.setDisplayName(CC.translate("&aWin"));
            emerald.setItemMeta(meta);
            inventory.setItem(4, emerald);
            inventory.setItem(22, emerald);
            inventory.setItem(3, emerald);
            inventory.setItem(21, emerald);
            inventory.setItem(5, emerald);
            inventory.setItem(23, emerald);
            player.updateInventory();

            new BukkitRunnable(){

                int i = 8;

                @Override
                public void run() {

                    i--;

                    if (i <= 0) {
                        player.closeInventory();
                        this.cancel();
                    }

                    if (i == 7) {
                        player.openInventory(inventory);

                    } else if (i == 6) {
                        legendaryCrateItems(player, random.nextInt(100));
                    } else if (i == 5) {
                        legendaryCrateItems(player, random.nextInt(100));
                    } else if (i == 4) {
                        legendaryCrateItems(player, random.nextInt(100));
                    } else if (i == 3) {
                        legendaryCrateItems(player, random.nextInt(100));
                    } else if (i == 2) {
                        legendaryCrateItems(player, random.nextInt(100));
                    } else if (i == 1) {
                        legendaryCrateItems(player, random.nextInt(100));
                    }
                }
            }.runTaskTimer(skyBlock, 0L,20L);
        }
    }

    void legendaryCrateItems(Player player, int randomNumber){

        ItemStack cash1000 = new ItemStack(Material.PAPER);
        ItemMeta meta = cash1000.getItemMeta();
        meta.setDisplayName(CC.translate("&a1000$ Cash Note"));
        cash1000.setItemMeta(meta);

        ItemStack cash5000 = new ItemStack(Material.PAPER);
        ItemMeta meta1 = cash5000.getItemMeta();
        meta1.setDisplayName(CC.translate("&a5000$ Cash Note"));
        cash5000.setItemMeta(meta1);

        ItemStack cash7500 = new ItemStack(Material.PAPER);
        ItemMeta meta2 = cash7500.getItemMeta();
        meta2.setDisplayName(CC.translate("&a7500$ Cash Note"));
        cash7500.setItemMeta(meta2);

        ItemStack cash10000 = new ItemStack(Material.PAPER);
        ItemMeta meta3 = cash10000.getItemMeta();
        meta3.setDisplayName(CC.translate("&a10000$ Cash Note"));
        cash7500.setItemMeta(meta3);

        // ---
        ItemStack gems1000 = new ItemStack(Material.PAPER);
        ItemMeta gemsMeta1000 = gems1000.getItemMeta();
        gemsMeta1000.setDisplayName(CC.translate("&a1000$ Gems Note"));
        gems1000.setItemMeta(gemsMeta1000);

        ItemStack gems5000 = new ItemStack(Material.PAPER);
        ItemMeta gemsMeta500 = gems5000.getItemMeta();
        gemsMeta500.setDisplayName(CC.translate("&a5000$ Gems Note"));
        gems5000.setItemMeta(gemsMeta500);

        ItemStack gems7500 = new ItemStack(Material.PAPER);
        ItemMeta gemsMeta7500 = gems7500.getItemMeta();
        gemsMeta7500.setDisplayName(CC.translate("&a7500$ Gems Note"));
        gems7500.setItemMeta(gemsMeta7500);

        ItemStack gems10000 = new ItemStack(Material.PAPER);
        ItemMeta gemsMeta10000 = gems10000.getItemMeta();
        gemsMeta10000.setDisplayName(CC.translate("&a10000$ Gems Note"));
        gems10000.setItemMeta(gemsMeta10000);

        ItemStack rank = new ItemStack(Material.BOOK);
        ItemMeta rankMeta = rank.getItemMeta();
        rankMeta.setDisplayName(CC.translate("&f&lAngel Rank"));
        rank.setItemMeta(rankMeta);

        Robot robot = new Robot(skyBlock, "slow");
        Robot robot1 = new Robot(skyBlock, "fast");
        Robot robot2 = new Robot(skyBlock, "medium");

        if (randomNumber < 5) {

            player.getInventory().addItem(rank);
            player.getInventory().addItem(cash1000);
            player.getInventory().addItem(cash5000);
            player.getInventory().addItem(gems1000);
            new Spawner(player, EntityType.SKELETON, 5);// 11 12 13 14 15
            inventory.setItem(11, rank);
            inventory.setItem(12, cash1000);
            inventory.setItem(13, cash5000);
            inventory.setItem(14, gems1000);
            inventory.setItem(15, new ItemStack(Material.MOB_SPAWNER, 5));
            player.updateInventory();
            data.addBalance(player ,1000);
            data.addBalance(player, 5000);
            data.addGems(player, 1000);
        } else if (randomNumber < 15 && randomNumber > 5) {

            ItemStack robotI = new ItemStack(Material.GOLD_BLOCK);
            ItemMeta rMeta = robotI.getItemMeta();
            rMeta.setDisplayName(CC.translate("&cMedium &fRobot"));
            robotI.setItemMeta(rMeta);

            new Spawner(player, EntityType.SKELETON, 5);
            player.getInventory().addItem(cash7500);
            robot2.give(player, 1);
            player.getInventory().addItem(cash10000);
            player.getInventory().addItem(cash1000);
            player.getInventory().addItem(gems5000);
            inventory.setItem(11, cash7500);
            inventory.setItem(12, cash10000);
            inventory.setItem(13, cash1000);
            inventory.setItem(14, gems5000);
            inventory.setItem(15, robotI);
            player.updateInventory();
            data.addBalance(player, 7500);
            data.addBalance(player, 10000);
            data.addBalance(player, 1000);
            data.addGems(player, 5000);
        } else if (randomNumber < 30 && randomNumber > 15) {
            ItemStack robotI = new ItemStack(Material.IRON_BLOCK);
            ItemMeta rMeta = robotI.getItemMeta();
            rMeta.setDisplayName(CC.translate("&eSlow &fRobot"));
            robotI.setItemMeta(rMeta);

            player.getInventory().addItem(cash5000);
            robot.give(player, 3);
            player.getInventory().addItem(cash10000);
            player.getInventory().addItem(cash1000);
            player.getInventory().addItem(gems5000);

            inventory.setItem(11, cash5000);
            inventory.setItem(12, cash10000);
            inventory.setItem(13, cash1000);
            inventory.setItem(14, gems5000);
            inventory.setItem(15, robotI);
            player.updateInventory();
            data.addBalance(player, 5000);
            data.addBalance(player, 10000);
            data.addGems(player, 5000);
            data.addBalance(player, 5000);
        } else if (randomNumber < 50 && randomNumber > 30) {
            ItemStack robotI = new ItemStack(Material.DIAMOND_BLOCK);
            ItemMeta rMeta = robotI.getItemMeta();
            rMeta.setDisplayName(CC.translate("&4Fast &fRobot"));
            robotI.setItemMeta(rMeta);
            new Spawner(player, EntityType.COW, 5);
            robot1.give(player, 3);
            player.getInventory().addItem(cash10000);
            player.getInventory().addItem(cash1000);
            player.getInventory().addItem(gems5000);

            inventory.setItem(11, cash10000);
            inventory.setItem(12, cash1000);
            inventory.setItem(13, gems5000);
            inventory.setItem(14, new ItemStack(Material.MOB_SPAWNER, 5));
            inventory.setItem(15, robotI);
            player.updateInventory();
            data.addBalance(player, 10000);
            data.addBalance(player, 1000);
            data.addGems(player, 5000);
        } else if (randomNumber < 80 && randomNumber > 50) {
            ItemStack robotI = new ItemStack(Material.DIAMOND_BLOCK);
            ItemMeta rMeta = robotI.getItemMeta();
            rMeta.setDisplayName(CC.translate("&4Fast &fRobot"));
            robotI.setItemMeta(rMeta);
            new Spawner(player, EntityType.COW, 2);
            player.getInventory().addItem(cash10000);
            player.getInventory().addItem(cash1000);
            player.getInventory().addItem(gems5000);
            robot1.give(player, 2);

            inventory.setItem(11, cash10000);
            inventory.setItem(12, cash1000);
            inventory.setItem(13, gems5000);
            inventory.setItem(14, new ItemStack(Material.MOB_SPAWNER,2));
            inventory.setItem(15, robotI);
            player.updateInventory();
            data.addBalance(player, 10000);
            data.addBalance(player, 1000);
            data.addGems(player, 5000);
        } else if (randomNumber < 100 && randomNumber > 80) {
            new Spawner(player, EntityType.SKELETON, 3);
            ItemStack robotI = new ItemStack(Material.DIAMOND_BLOCK);
            ItemMeta rMeta = robotI.getItemMeta();
            rMeta.setDisplayName(CC.translate("&4Fast &fRobot"));
            robotI.setItemMeta(rMeta);
            player.getInventory().addItem(cash5000);
            player.getInventory().addItem(cash7500);
            player.getInventory().addItem(gems5000);
            robot1.give(player, 2);
            inventory.setItem(11, cash10000);
            inventory.setItem(12, cash1000);
            inventory.setItem(13, gems5000);
            inventory.setItem(15, robotI);
            player.updateInventory();
            data.addBalance(player, 10000);
            data.addBalance(player, 1000);
            data.addGems(player, 5000);
        }
    }
}
