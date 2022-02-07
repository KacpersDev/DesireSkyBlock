package net.desiremc.data;

import net.desiremc.SkyBlock;
import net.desiremc.reload.DataReload;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DataListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();

        if (!player.hasPlayedBefore() && !SkyBlock.getInstance().getData().contains("Player." + player.getUniqueId())) {

            SkyBlock.getInstance().getData().set("Player." + player.getUniqueId() + ".Reclaimed", false);
            SkyBlock.getInstance().getData().set("Player." + player.getUniqueId() + ".Balance", 0);
            SkyBlock.getInstance().getData().set("Player." + player.getUniqueId() + ".Gems", 0);
            DataReload.reload();
        }
    }
}
