package net.desiremc.reclaim;

import net.desiremc.SkyBlock;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.IOException;

public class ReclaimHandler {

    public void setReclaimed(Player player, boolean reclaimed){

        SkyBlock.getInstance().getData().set("Player." + player.getUniqueId() + ".Reclaimed", reclaimed);
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

    public boolean reclaimed(Player player){

        return SkyBlock.getInstance().getData().getBoolean("Player." + player.getUniqueId() + ".Reclaimed");
    }

    public void applyReclaim(Player player) {

        String rank = SkyBlock.getInstance().getChat().getPrimaryGroup(player);

        for (final String i : SkyBlock.getInstance().getReclaimDataConfiguration().getConfigurationSection("reclaim").getKeys(false)) {
            String reclaimRank = SkyBlock.getInstance().getReclaimDataConfiguration().getString("reclaim." + i + ".rank");
            if (rank.contains(reclaimRank)) {
                for (final String commands : SkyBlock.getInstance().getReclaimDataConfiguration().getStringList("reclaim." + i + ".commands")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("%player%", player.getName()));
                }
            }
        }
    }
}
