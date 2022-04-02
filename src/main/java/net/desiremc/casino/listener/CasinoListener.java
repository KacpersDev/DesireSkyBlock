package net.desiremc.casino.listener;

import net.desiremc.SkyBlock;
import net.desiremc.casino.command.CasinoCommand;
import net.desiremc.data.Data;
import net.desiremc.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.Random;

public class CasinoListener implements Listener {

    private SkyBlock skyBlock;
    private Random random;
    private CasinoCommand command;
    private Data data;

    public CasinoListener(SkyBlock skyBlock){
        this.skyBlock = skyBlock;
        this.random = new Random();
        this.command = new CasinoCommand(skyBlock);
        this.data = new Data();
    }

    @EventHandler
    public void onInteractWithInventory(InventoryClickEvent event){

        Player player = (Player) event.getWhoClicked();

        if (event.getInventory().getTitle().equalsIgnoreCase(CC.translate("&aCasino"))) {
            event.setCancelled(true);
        }

        if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&aConfirm Bet"))) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "balance remove " + player.getName() + " " + command.getAmount());
            load();
            new BukkitRunnable(){

                int i = 5;

                @Override
                public void run() {

                    int result = random.nextInt(100);

                    i--;

                    if (i == 1) {
                        this.cancel();
                        if (result > 50) { // player winning
                            player.sendMessage(CC.translate("&aYou have won the bet."));
                            Bukkit.broadcastMessage(CC.translate("&a" + player.getName() + " &fwon bet in casino."));
                            int total = (CasinoCommand.a.get(player) * 2);
                            data.addBalance(player, total);
                        } else {
                            player.sendMessage(CC.translate("&cYou have lost the bet."));
                            Bukkit.broadcastMessage(CC.translate("&c" + player.getName() + " &flost bet in casino."));
                        }
                    }

                    player.sendMessage(CC.translate("&aBet results in &7: &f" + i));

                }
            }.runTaskTimer(skyBlock, 0L,20L);
        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&cCancel Bet"))) {
            data.addBalance(player, CasinoCommand.a.get(player));
            player.closeInventory();
        }
    }

    void load(){

        try {
            SkyBlock.getInstance().getData().save(SkyBlock.getInstance().getDataFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            SkyBlock.getInstance().getData().load(SkyBlock.getInstance().getDataFile());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
