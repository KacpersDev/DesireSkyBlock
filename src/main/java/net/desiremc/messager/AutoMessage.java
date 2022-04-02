package net.desiremc.messager;

import net.desiremc.SkyBlock;
import net.desiremc.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoMessage implements CommandExecutor {

    private SkyBlock skyBlock;
    private boolean messager;

    public AutoMessage(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender.hasPermission("skyblock.command.automessage"))) {
            sender.sendMessage(CC.translate(skyBlock.getConfig().getString("no-permissions")));
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0){
            usage(player);
        } else if (args[0].equalsIgnoreCase("on")) {
            this.messager = true;
            player.sendMessage(CC.translate("&aAuto-Messager has been enabled."));
            task(player);
        } else if (args[0].equalsIgnoreCase("off")) {
            this.messager = false;
            player.sendMessage(CC.translate("&cAuto-Messager has been disabled."));
        }

        return true;
    }

    void usage(Player player){

    }

    void task(Player player) {

        if (messager) {


            new BukkitRunnable(){

                int i = 1200;

                @Override
                public void run() {

                    i--;

                    if (i <= 0) {
                        i = 1200;
                    }

                    if (!messager) {
                        this.cancel();
                    }

                    if (i == 900) {
                        for (final String n : skyBlock.getConfig().getStringList("messager.sale")) {
                            Bukkit.broadcastMessage(CC.translate(n));
                        }
                    } else if (i == 600) {
                        for (final String n : skyBlock.getConfig().getStringList("messager.key-all")) {
                            Bukkit.broadcastMessage(CC.translate(n));
                        }
                    } else if (i == 300) {
                        for (final String n : skyBlock.getConfig().getStringList("messager.discord")) {
                            Bukkit.broadcastMessage(CC.translate(n));
                        }
                    } else if (i == 1) {
                        for (final String n : skyBlock.getConfig().getStringList("messager.twitter")) {
                            Bukkit.broadcastMessage(CC.translate(n));
                        }
                    }

                }
            }.runTaskTimer(skyBlock, 0L,20L);
        }
    }
}
