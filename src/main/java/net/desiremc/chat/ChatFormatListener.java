package net.desiremc.chat;

import net.desiremc.SkyBlock;
import net.desiremc.utils.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){

        Player player = event.getPlayer();


        event.setFormat(CC.translate(SkyBlock.getInstance().getConfig().getString("chat-format")
                .replace("%player%", player.getName())
                .replace("%rank%", CC.translate(SkyBlock.getInstance().getChat().getPrimaryGroup(player)))
                .replace("%message%", event.getMessage())));
    }
}
