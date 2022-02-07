package net.desiremc.data;

import net.desiremc.SkyBlock;
import net.desiremc.reload.DataReload;
import net.desiremc.utils.CC;
import org.bukkit.entity.Player;

public class Data {

    public Integer getBalance(Player player){

        return SkyBlock.getInstance().getData().getInt("Player." + player.getUniqueId() + ".Balance");
    }

    public void addBalance(Player player, int amount){

        int balance = (getBalance(player) + amount);
        SkyBlock.getInstance().getData().set("Player." + player.getUniqueId() + ".Balance", balance);
        DataReload.reload();
    }

    public void setBalance(Player player, int amount){

        SkyBlock.getInstance().getData().set("Player." + player.getUniqueId() + ".Balance", amount);
        DataReload.reload();
    }

    public void removeBalance(Player target, Player player, int amount) {

        if ((getBalance(player) - amount >= 0)) {
            int newBalance = (getBalance(player) - amount);
            SkyBlock.getInstance().getData().set("Player." + player.getUniqueId() + ".Balance", newBalance);
            DataReload.reload();
        }  else {
            player.sendMessage(CC.translate(SkyBlock.getInstance().getConfig().getString("balance.lower0")
                    .replace("%player%", target.getName())));
        }
    }
}
