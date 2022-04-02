package net.desiremc.staff.implement;

import net.desiremc.SkyBlock;
import net.desiremc.staff.StaffMode;
import net.desiremc.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class StaffModeCommand implements CommandExecutor {

    private SkyBlock skyBlock;
    private StaffMode staffMode;

    public StaffModeCommand(SkyBlock skyBlock){
        this.skyBlock = skyBlock;
        this.staffMode = new StaffMode();
    }

    public final ArrayList<Player> staff = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            return false;
        }

        if (!(sender.hasPermission("skyblock.command.staffmode"))) {
            sender.sendMessage(CC.translate(skyBlock.getConfig().getString("no-permissions")));
            return false;
        }

        Player player = (Player) sender;
        if (!(staff.contains(player))) {
            staff.add(player);
            staffMode.activate(player);
            player.sendMessage(CC.translate("&aStaff mode has been enabled."));
        } else {
            staff.remove(player);
            staffMode.deactivate(player);
            player.sendMessage(CC.translate("&cStaff mode has been disabled."));
        }

        return true;
    }
}
