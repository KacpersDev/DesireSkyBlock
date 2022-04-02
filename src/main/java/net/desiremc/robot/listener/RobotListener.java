package net.desiremc.robot.listener;

import net.desiremc.SkyBlock;
import net.desiremc.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;
import java.util.Random;

public class RobotListener implements Listener {

    private final Random random = new Random();
    private ItemStack mat;

    @EventHandler
    public void onInteract(PlayerInteractEvent event){

        Player player = event.getPlayer();
        Action action = event.getAction();

        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (player.getItemInHand() == null || player.getItemInHand().getItemMeta() == null || player.getInventory().getItemInHand().getItemMeta().getDisplayName() == null) return;
            if (player.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&eSlow &fRobot Miner"))
            && player.getInventory().getItemInHand().getItemMeta().hasEnchant(Enchantment.DURABILITY)) {
                openInventory(player);

            }

            if (player.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&cMedium &fRobot Miner"))
            && player.getInventory().getItemInHand().getItemMeta().hasEnchant(Enchantment.DURABILITY)) {

                openInventory(player);
            }

            if (player.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&4Fast &fRobot Miner"))
                    && player.getInventory().getItemInHand().getItemMeta().hasEnchant(Enchantment.DURABILITY)) {

                openInventory(player);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){

        Player player = (Player) event.getWhoClicked();

        if (event.getInventory().getTitle().equalsIgnoreCase(CC.translate("&aRobot Inventory"))) {
            event.setCancelled(true);
        }

        if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&aActivate"))) {
            if (player.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&eSlow &fRobot Miner"))) {
                player.sendMessage(CC.translate("&aRobot has been activated, for another 15 minutes."));
                slowMine(player);
                removeItem(player);
            } else if (player.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&cMedium &fRobot Miner"))
            && player.getInventory().getItemInHand().getItemMeta().hasEnchant(Enchantment.DURABILITY)) {
                player.sendMessage(CC.translate("&aRobot has been activated, for another 15 minutes."));
                mediumMine(player);
                removeItem(player);
            } else if (player.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&4Fast &fRobot Miner"))
            && player.getInventory().getItemInHand().getItemMeta().hasEnchant(Enchantment.DURABILITY)) {
                player.sendMessage(CC.translate("&aRobot has been activated, for another 15 minutes."));
                fastMine(player);
                removeItem(player);
            }

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&cCancel"))) {
            player.closeInventory();
        }
    }

    private void openInventory(Player player){

        Inventory inventory = Bukkit.createInventory(player, 36, CC.translate("&aRobot Inventory"));

        ItemStack emerald = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta = emerald.getItemMeta();
        meta.setDisplayName(CC.translate("&aActivate"));
        emerald.setItemMeta(meta);

        ItemStack redstone = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta redstoneMeta = redstone.getItemMeta();
        redstoneMeta.setDisplayName(CC.translate("&cCancel"));
        redstone.setItemMeta(redstoneMeta);

        inventory.setItem(13, emerald);
        inventory.setItem(15, redstone);

        player.openInventory(inventory);
    }

    void removeItem(Player player){

        if (player.getItemInHand().getAmount() > 1) {
            player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
        } else {
            player.setItemInHand(new ItemStack(Material.AIR));
            player.setItemInHand(null);
        }
    }

    void slowMine(Player player){

        new BukkitRunnable(){

            int i = 900;

            @Override
            public void run() {

                i--;

                if (i <= 0) {
                    this.cancel();
                    player.sendMessage(CC.translate("&aRobot has expired"));
                }

                if (i == 600) {
                    int blockType = random.nextInt(2);
                    if (blockType == 0) {
                        mat = new ItemStack(Material.EMERALD_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 5 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 10 Minutes left."));
                    } else if (blockType == 1) {
                        mat = new ItemStack(Material.DIAMOND_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 5 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 5 Minutes left."));
                    } else if (blockType == 2) {
                        mat = new ItemStack(Material.IRON_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 5 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. Robot has expired."));
                    }
                } else if (i == 300){
                    int blockType = random.nextInt(2);
                    if (blockType == 0) {
                        mat = new ItemStack(Material.EMERALD_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 5 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 10 Minutes left."));
                    } else if (blockType == 1) {
                        mat = new ItemStack(Material.DIAMOND_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 5 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 5 Minutes left."));
                    } else if (blockType == 2) {
                        mat = new ItemStack(Material.IRON_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 5 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. Robot has expired."));
                    }
                } else if (i == 1) {
                    int blockType = random.nextInt(2);
                    if (blockType == 0) {
                        mat = new ItemStack(Material.EMERALD_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 5 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 10 Minutes left."));
                    } else if (blockType == 1) {
                        mat = new ItemStack(Material.DIAMOND_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 5 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 5 Minutes left."));
                    } else if (blockType == 2) {
                        mat = new ItemStack(Material.IRON_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 5 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. Robot has expired."));
                    }
                }

            }
        }.runTaskTimer(SkyBlock.getInstance(), 0L,20L);
    }

    void mediumMine(Player player){

        new BukkitRunnable(){

            int i = 900;

            @Override
            public void run() {

                i--;

                if (i <= 0) {
                    this.cancel();
                    player.sendMessage(CC.translate("&aRobot has expired"));
                }

                // 600, 300, 1
                //

                if (i == 675) {
                    int blockType = random.nextInt(2);
                    if (blockType == 0) {
                        mat = new ItemStack(Material.EMERALD_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 4 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 10 Minutes left."));
                    } else if (blockType == 1) {
                        mat = new ItemStack(Material.DIAMOND_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 4 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 5 Minutes left."));
                    } else if (blockType == 2) {
                        mat = new ItemStack(Material.IRON_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 4 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. Robot has expired."));
                    }
                } else if (i == 450){
                    int blockType = random.nextInt(2);
                    if (blockType == 0) {
                        mat = new ItemStack(Material.EMERALD_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 4 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 10 Minutes left."));
                    } else if (blockType == 1) {
                        mat = new ItemStack(Material.DIAMOND_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 4 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 5 Minutes left."));
                    } else if (blockType == 2) {
                        mat = new ItemStack(Material.IRON_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 4 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. Robot has expired."));
                    }
                } else if (i == 225) {
                    int blockType = random.nextInt(2);
                    if (blockType == 0) {
                        mat = new ItemStack(Material.EMERALD_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 4 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 10 Minutes left."));
                    } else if (blockType == 1) {
                        mat = new ItemStack(Material.DIAMOND_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 4 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 5 Minutes left."));
                    } else if (blockType == 2) {
                        mat = new ItemStack(Material.IRON_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 4 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. Robot has expired."));
                    }
                } else if (i == 1) {
                    int blockType = random.nextInt(2);
                    if (blockType == 0) {
                        mat = new ItemStack(Material.EMERALD_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 4 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 10 Minutes left."));
                    } else if (blockType == 1) {
                        mat = new ItemStack(Material.DIAMOND_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 4 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 5 Minutes left."));
                    } else if (blockType == 2) {
                        mat = new ItemStack(Material.IRON_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 4 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. Robot has expired."));
                    }
                }

            }
        }.runTaskTimer(SkyBlock.getInstance(), 0L,20L);
    }

    void fastMine(Player player){

        new BukkitRunnable(){

            int i = 900;

            @Override
            public void run() {

                i--;

                if (i <= 0) {
                    this.cancel();
                    player.sendMessage(CC.translate("&aRobot has expired"));
                }

                // 180

                if (i == 720) {
                    int blockType = random.nextInt(2);
                    if (blockType == 0) {
                        mat = new ItemStack(Material.EMERALD_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 3 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 10 Minutes left."));
                    } else if (blockType == 1) {
                        mat = new ItemStack(Material.DIAMOND_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 3 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 5 Minutes left."));
                    } else if (blockType == 2) {
                        mat = new ItemStack(Material.IRON_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 3 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. Robot has expired."));
                    }
                } else if (i == 540){
                    int blockType = random.nextInt(2);
                    if (blockType == 0) {
                        mat = new ItemStack(Material.EMERALD_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 3 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 10 Minutes left."));
                    } else if (blockType == 1) {
                        mat = new ItemStack(Material.DIAMOND_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 3 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 5 Minutes left."));
                    } else if (blockType == 2) {
                        mat = new ItemStack(Material.IRON_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 3 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. Robot has expired."));
                    }
                } else if (i == 360) {
                    int blockType = random.nextInt(2);
                    if (blockType == 0) {
                        mat = new ItemStack(Material.EMERALD_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 3 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 10 Minutes left."));
                    } else if (blockType == 1) {
                        mat = new ItemStack(Material.DIAMOND_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 3 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 5 Minutes left."));
                    } else if (blockType == 2) {
                        mat = new ItemStack(Material.IRON_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 3 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. Robot has expired."));
                    }
                } else if (i == 180) {
                    int blockType = random.nextInt(2);
                    if (blockType == 0) {
                        mat = new ItemStack(Material.EMERALD_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 3 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 10 Minutes left."));
                    } else if (blockType == 1) {
                        mat = new ItemStack(Material.DIAMOND_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 3 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 5 Minutes left."));
                    } else if (blockType == 2) {
                        mat = new ItemStack(Material.IRON_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 3 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. Robot has expired."));
                    }
                } else if (i == 1) {
                    int blockType = random.nextInt(2);
                    if (blockType == 0) {
                        mat = new ItemStack(Material.EMERALD_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 3 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 10 Minutes left."));
                    } else if (blockType == 1) {
                        mat = new ItemStack(Material.DIAMOND_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 3 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. 5 Minutes left."));
                    } else if (blockType == 2) {
                        mat = new ItemStack(Material.IRON_BLOCK);
                        int amount = random.nextInt(16);
                        mat.setAmount(amount);
                        player.getInventory().addItem(mat);
                        player.sendMessage(CC.translate("&aYour robot has mined " + amount + " of " + mat.getType() + " within 3 minutes"));
                        player.sendMessage(CC.translate("&aAll Items has been added to your account. Robot has expired."));
                    }
                }

            }
        }.runTaskTimer(SkyBlock.getInstance(), 0L,20L);
    }
}
