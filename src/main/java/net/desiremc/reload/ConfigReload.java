package net.desiremc.reload;

import net.desiremc.SkyBlock;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

public class ConfigReload {

    public static void reload(){

        try {
            SkyBlock.getInstance().getConfig().save(SkyBlock.getInstance().getConfigFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            SkyBlock.getInstance().getConfig().load(SkyBlock.getInstance().getConfigFile());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
