package net.desiremc.spawn;

import net.desiremc.SkyBlock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpawnListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();

        double x = SkyBlock.getInstance().getConfig().getDouble("spawn.x");
        double y = SkyBlock.getInstance().getConfig().getDouble("spawn.y");
        double z = SkyBlock.getInstance().getConfig().getDouble("spawn.z");

        player.teleport(new Location(Bukkit.getWorld("World"),x,y,z));
    }
}
