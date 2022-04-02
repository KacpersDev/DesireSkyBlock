package net.desiremc.kit.listener;

import lombok.Getter;
import net.desiremc.SkyBlock;
import net.desiremc.kit.Kit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@Getter
public class KitListener implements Listener {

    private final SkyBlock skyBlock;
    private final Kit kit;

    public KitListener(SkyBlock skyBlock){
        this.skyBlock  = skyBlock;
        this.kit = new Kit(skyBlock, "starter");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (!(player.hasPlayedBefore())) {
            this.kit.redeemKit(player);
        }
    }
}
