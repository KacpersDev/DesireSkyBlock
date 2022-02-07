package net.desiremc.reclaim;

import net.desiremc.SkyBlock;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ReclaimHandler {

    public void setReclaimed(Player player, boolean reclaimed){

        SkyBlock.getInstance().getData().set("Player." + player.getUniqueId() + ".Reclaimed", reclaimed);
    }

    public boolean reclaimed(Player player){

        return SkyBlock.getInstance().getData().getBoolean("Player." + player.getUniqueId() + ".Reclaimed");
    }

    public void reclaim(Player player){

        String playerRank = "";

        for (final String commands : SkyBlock.getInstance().getReclaim().getStringList("rank." + playerRank + ".reclaim")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands
                    .replace("%player%", player.getName()));
        }
    }
}
