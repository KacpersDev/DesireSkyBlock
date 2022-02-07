package net.desiremc.scoreboard.thread;

import lombok.RequiredArgsConstructor;
import net.desiremc.scoreboard.ScoreboardHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class ScoreboardRunnable extends BukkitRunnable {

    private final ScoreboardHandler handler;

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            this.handler.getAdapter().handleElement(player, this.handler.getHandler().getElement(player));
        }
    }
}