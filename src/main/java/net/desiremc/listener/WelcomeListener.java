package net.desiremc.listener;

import lombok.Getter;
import net.desiremc.SkyBlock;
import net.desiremc.spawner.Spawner;
import net.desiremc.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

@Getter
public class WelcomeListener implements Listener {

    private final SkyBlock skyBlock;

    public WelcomeListener(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.setJoinMessage(null);
        Player player = event.getPlayer();

        if (!(player).hasPlayedBefore()) {
            getSkyBlock().getJoin().set("Join", getJoins() + 1);
            message(player);
        }
    }

    private Integer getJoins(){
        return this.getSkyBlock().getJoin().getInt("Join");
    }

    private void message(Player player) {

        Bukkit.broadcastMessage(CC.translate(getSkyBlock().getConfig().getString("join-message")
                .replace("%player%", player.getName())
                .replace("%players%", String.valueOf(getJoins()))));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }
}
