package net.desiremc.config;

import net.desiremc.SkyBlock;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class FlatFile {

    public FlatFile(File file, FileConfiguration configuration, String dir) {

        if (!(file.exists())) {
            file.getParentFile().mkdir();
            SkyBlock.getInstance().saveResource(dir, false);
        }

        try {
            configuration.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
