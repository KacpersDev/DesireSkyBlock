package net.desiremc.scoreboard.reflection;

import net.desiremc.SkyBlock;
import net.desiremc.scoreboard.element.ScoreboardElement;
import net.desiremc.scoreboard.element.ScoreboardElementHandler;
import net.desiremc.utils.CC;
import org.bukkit.entity.Player;

public class Board implements ScoreboardElementHandler {
    @Override
    public ScoreboardElement getElement(Player player) {
        final ScoreboardElement element = new ScoreboardElement();

        int balance = SkyBlock.getInstance().getData().getInt("Player." + player.getUniqueId() + ".Balance");
        int gems = SkyBlock.getInstance().getData().getInt("Player." + player.getUniqueId() + ".Gems");

        element.setTitle(CC.translate(SkyBlock.getInstance().getConfig().getString("scoreboard-title")));
        for (final String i : SkyBlock.getInstance().getConfig().getStringList("scoreboard-lines")) {
            element.add(CC.translate(i)
                    .replace("%player%", player.getName())
                    .replace("%rank%", CC.translate(SkyBlock.getInstance().getChat().getPrimaryGroup(player)))
                    .replace("%balance%", String.valueOf(balance))
                    .replace("%gems%", String.valueOf(gems)));
        }

        return element;
    }
}
