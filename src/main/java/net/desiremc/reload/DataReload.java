package net.desiremc.reload;

import net.desiremc.SkyBlock;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

public class DataReload {

    public static void reload(){

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
}
