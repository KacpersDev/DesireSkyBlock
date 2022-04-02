package net.desiremc.scoreboard.reflection;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import net.desiremc.SkyBlock;
import net.desiremc.scoreboard.element.ScoreboardElement;
import net.desiremc.scoreboard.element.ScoreboardElementHandler;
import net.desiremc.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class Board implements ScoreboardElementHandler {

    private String islandName;
    private String leader;
    private BigDecimal worth;
    private double raiting;
    private String scoreboardTitle = CC.translate(SkyBlock.getInstance().getConfig().getString("scoreboard-title"));
    private String lineElement = CC.translate("&c&ldesiremc.net");

    @Override
    public ScoreboardElement getElement(Player player) {
        final ScoreboardElement element = new ScoreboardElement();

        int balance = SkyBlock.getInstance().getData().getInt("Player." + player.getUniqueId() + ".Balance");
        int gems = SkyBlock.getInstance().getData().getInt("Player." + player.getUniqueId() + ".Gems");

        if (SuperiorSkyblockAPI.getPlayer(player.getUniqueId()).hasIsland()) {
            Island playerIsland = SuperiorSkyblockAPI.getPlayer(player.getUniqueId()).getIsland();

            islandName = playerIsland.getName();
            leader = playerIsland.getOwner().getName();
            worth = playerIsland.getWorth();

            raiting = playerIsland.getTotalRating();
        }
        element.setTitle(scoreboardTitle);
        Bukkit.getScheduler().runTaskTimer(SkyBlock.getInstance(), new Runnable() {

            int i = 8;

            @Override
            public void run() {

                i--;

                if (i <= 0) {
                    i = 8;
                }

                if (i == 7) {
                    scoreboardTitle = (CC.translate(SkyBlock.getInstance().getConfig().getString("title.1")));
                } else if (i == 6) {
                    scoreboardTitle = (CC.translate(SkyBlock.getInstance().getConfig().getString("title.2")));
                } else if (i == 5) {
                    scoreboardTitle = (CC.translate(SkyBlock.getInstance().getConfig().getString("title.3")));
                } else if (i == 4) {
                    scoreboardTitle = (CC.translate(SkyBlock.getInstance().getConfig().getString("title.4")));
                } else if (i == 3) {
                    scoreboardTitle = (CC.translate(SkyBlock.getInstance().getConfig().getString("title.5")));
                } else if (i == 2) {
                    scoreboardTitle = (CC.translate(SkyBlock.getInstance().getConfig().getString("title.6")));
                }
            }
        }, 0L,60L);

        element.add("");
        element.add(CC.translate("&4&l| &c" + player.getName()));
        element.add(CC.translate("&4&l| &7Rank: " + CC.translate(SkyBlock.getInstance().getChat().getPrimaryGroup(player))));
        String balanceString = convertInt(balance);
        String gemsString = convertInt(gems);
        element.add(CC.translate("&4&l| &7Balance: " + balanceString));
        element.add(CC.translate("&4&l| &7Gems: " + gemsString));
        element.add("");
        element.add(CC.translate("&4&l| &cIsland Info"));
        if (SuperiorSkyblockAPI.getPlayer(player.getUniqueId()).hasIsland()) {
            element.add(CC.translate("&4&l| &7Name: &f" + islandName));
            element.add(CC.translate("&4&l| &7Leader: &f" + leader));
            element.add(CC.translate("&4&l| &7Worth &f" + worth));
            element.add(CC.translate("&4&l| &7Top &f" + raiting));
        } else {
            element.add(CC.translate("&4&l| &7Name: &f" + "None"));
            element.add(CC.translate("&4&l| &7Leader: &f" + "None"));
            element.add(CC.translate("&4&l| &7Worth &f" + "None"));
            element.add(CC.translate("&4&l| &7Top &f" + "None"));
        }
        element.add("");
        element.add(lineElement);
        Bukkit.getScheduler().runTaskTimer(SkyBlock.getInstance(), new Runnable() {

            int i = 4;

            @Override
            public void run() {

                i--;

                if (i <= 0) {
                    i = 4;
                }

                if (i == 3) {
                    lineElement = CC.translate("&cstore.desiremc.net");
                } else if (i == 2) {
                    lineElement = CC.translate("&ctwitter.com/desiremc");
                } else if (i == 1) {
                    lineElement = CC.translate("&cdiscord.desiremc.net");
                }

            }
        },0L,100L);

        return element;
    }

    public String convertInt(int i) {
        String n = Integer.toString(i);
        int l = n.length();
        if (l > 3) {
            if (l < 7) {
                return decimal(n, 3) + "K";
            }
            if (l < 10) {
                return decimal(n, 6) + "M";
            }
            if (l < 13) {
                return decimal(n, 9) + "B";
            }
            if (l < 16) {
                return decimal(n, 12) + "T";
            }
            if (l < 19) {
                return decimal(n, 15) + "Q";
            }
        }
        return n;
    }

    public String decimal(String s, int i) {
        int b = s.length() - i;
        return s.substring(0, b) + "." + s.substring(b, b+2);
    }

}
