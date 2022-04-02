package net.desiremc.staff.implement;

import net.desiremc.SkyBlock;
import net.desiremc.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;

public class StaffModeListener implements Listener {

    private SkyBlock skyBlock;
    private StaffModeCommand command;
    private VanishCommand vanishCommand;
    private ArrayList<Player> freeze = new ArrayList<>();

    public StaffModeListener(SkyBlock skyBlock){
        this.skyBlock = skyBlock;
        this.command = new StaffModeCommand(skyBlock);
        this.vanishCommand = new VanishCommand();
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){

        Player player = event.getPlayer();

        if (command.staff.contains(player) || vanishCommand.vanish.contains(player) || player.getGameMode().equals(GameMode.CREATIVE)
                && !player.hasPermission("skyblock.build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();

        if (command.staff.contains(player)  || vanishCommand.vanish.contains(player) || player.getGameMode().equals(GameMode.CREATIVE)
        && !player.hasPermission("skyblock.build")) { event.setCancelled(true); }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (command.staff.contains(player) || player.getGameMode().equals(GameMode.CREATIVE)
                && !player.hasPermission("skyblock.build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemTake(PlayerPickupItemEvent event) {

        Player player = event.getPlayer();

        if (command.staff.contains(player) || vanishCommand.vanish.contains(player) || player.getGameMode().equals(GameMode.CREATIVE)
                && !player.hasPermission("skyblock.build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {

        Player player = event.getPlayer();

        if (command.staff.contains(player) || vanishCommand.vanish.contains(player) || player.getGameMode().equals(GameMode.CREATIVE)
                && !player.hasPermission("skyblock.build")) {
            event.setCancelled(true);
        }
    }

    /*
        @EventHandler
    public void onFreeze(PlayerInteractAtEntityEvent event){

        Player player = event.getPlayer();
        Player freezed = (Player) event.getRightClicked();

        if (!(freezed instanceof Player)) return;

        if (command.staff.contains(player) && player.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&cFreeze Player"))) {

            if (!(freeze.contains(freezed))) {
                freeze.add(freezed);
                freezed.sendMessage(CC.translate("&cYou have been frozen."));
                player.sendMessage(CC.translate("&aPlayer has been frozen."));
            } else {
                freeze.remove(freezed);
                freezed.sendMessage(CC.translate("&aYou have been unfrozen."));
                player.sendMessage(CC.translate("&aPlayer has been unfrozen."));
            }
        } else if (player.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&cInspect Inventory"))) {

            PlayerInventory inspectorInventory = freezed.getInventory();

            player.openInventory(inspectorInventory);
        }
    }
     */

    @EventHandler
    public void onFreezeMove(PlayerMoveEvent event){

        Player player = event.getPlayer();

        if (freeze.contains(player)) {
            double deltaXFrom = event.getFrom().getX();
            double deltaXTo = event.getTo().getX();

            double deltaZFrom = event.getFrom().getZ();
            double deltaZTo = event.getTo().getZ();

            if (deltaXFrom != deltaXTo) {
                player.teleport(new Location(Bukkit.getWorld(player.getWorld().getName()), deltaXFrom, player.getLocation().getY(), player.getLocation().getZ()));
            } else if (deltaZFrom != deltaZTo) {
                player.teleport(new Location(Bukkit.getWorld(player.getWorld().getName()), player.getLocation().getX(), player.getLocation().getY(), deltaZFrom));
            }
        }
    }

    @EventHandler
    public void onAttackFreezer(EntityDamageByEntityEvent event){

        Player player = (Player) event.getEntity();

        if (freeze.contains(player)){
            event.setDamage(0);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onAttackFreezer(EntityDamageEvent event) {

        if (event.getEntity() instanceof Player) return;

        Player player = (Player) event.getEntity();

        if (freeze.contains(player)) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onAttackFreezer(EntityDamageByBlockEvent event) {
        Player player = (Player) event.getEntity();

        if (freeze.contains(player)) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onVanish(PlayerInteractEvent event){

        Player player = event.getPlayer();
        Action action = event.getAction();

        if (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR)) {
            if (player.getItemInHand() == null || player.getItemInHand().getItemMeta() == null ||
            player.getItemInHand().getItemMeta().getDisplayName() == null) return;
            if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&cVanish"))
            && player.getGameMode().equals(GameMode.CREATIVE)) {

                if (!vanishCommand.vanish.contains(player)) {
                    vanishCommand.vanish.add(player);
                    for (final Player online : Bukkit.getOnlinePlayers()) {
                        if (!online.hasPermission("skyblock.command.staff")) {
                            online.hidePlayer(player);
                        }
                        player.sendMessage(CC.translate("&aVanish has been enabled."));
                    }
                } else {
                    vanishCommand.vanish.remove(player);
                    for (final Player online : Bukkit.getOnlinePlayers()) {
                        online.showPlayer(player);
                    }
                    player.sendMessage(CC.translate("&cVanish has been disabled."));
                }
            }
        }
    }
}
