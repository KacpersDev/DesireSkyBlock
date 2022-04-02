package net.desiremc;

import net.desiremc.casino.command.CasinoCommand;
import net.desiremc.casino.listener.CasinoListener;
import net.desiremc.chat.ChatFormatListener;
import net.desiremc.command.Fly;
import net.desiremc.config.FlatFile;
import net.desiremc.crates.command.CrateCommandHandler;
import net.desiremc.crates.listener.CrateListener;
import net.desiremc.data.DataListener;
import net.desiremc.data.command.BalanceCommand;
import net.desiremc.data.command.GemsCommand;
import net.desiremc.kit.command.KitCommand;
import net.desiremc.kit.listener.KitListener;
import net.desiremc.listener.FoodListener;
import net.desiremc.listener.WeatherListener;
import net.desiremc.listener.WelcomeListener;
import net.desiremc.messager.AutoMessage;
import net.desiremc.reclaim.ReclaimCommandHandler;
import net.desiremc.reclaim.ReclaimHandler;
import net.desiremc.robot.command.RobotCommandHandler;
import net.desiremc.robot.listener.RobotListener;
import net.desiremc.scoreboard.ScoreboardHandler;
import net.desiremc.scoreboard.reflection.Board;
import net.desiremc.spawn.SetSpawnCommand;
import net.desiremc.spawn.SpawnCommand;
import net.desiremc.spawn.SpawnListener;
import net.desiremc.staff.implement.StaffModeCommand;
import net.desiremc.staff.implement.StaffModeListener;
import net.desiremc.staff.implement.VanishCommand;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class SkyBlock extends JavaPlugin {

    private static SkyBlock instance;
    private final ReclaimHandler reclaimHandler = new ReclaimHandler();
    private final File config = new File(getDataFolder(), "config.yml");
    private final FileConfiguration configuration = new YamlConfiguration();
    private final File reclaim = new File(getDataFolder(), "reclaim.yml");
    private final FileConfiguration reclaimConfiguration = new YamlConfiguration();
    private final File data = new File(getDataFolder(), "playerdata.yml");
    private final YamlConfiguration dataConfiguration = new YamlConfiguration();
    private final File event = new File(getDataFolder(), "event.yml");
    private final YamlConfiguration eventConfiguration = new YamlConfiguration();
    private final File jData = new File(getDataFolder(), "joindata.yml");
    private final YamlConfiguration joindataConfiguration = new YamlConfiguration();
    private final File rData = new File(getDataFolder(), "reclaimdata.yml");
    private final FileConfiguration reclaimDataConfiguration = new YamlConfiguration();
    private Chat chat = null;


    @Override
    public void onEnable() {
        instance = this;

        this.config();
        this.listener();
        this.command();
        this.setupChat();
    }

    public static SkyBlock getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
    }

    private boolean setupChat() {
        RegisteredServiceProvider chatProvider = this.getServer().getServicesManager().getRegistration(Chat.class);
        if (chatProvider != null) {
            chat = (Chat) chatProvider.getProvider();
        }
        return chat != null;
    }

    private void config(){
        new FlatFile(config, configuration, "config.yml");
        new FlatFile(reclaim, reclaimConfiguration, "config.yml");
        new FlatFile(data, dataConfiguration, "playerdata.yml");
        new FlatFile(event, eventConfiguration, "event.yml");
        new FlatFile(jData, joindataConfiguration, "joindata.yml");
        new FlatFile(rData, reclaimDataConfiguration, "reclaimdata.yml");
    }

    private void command(){

        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("balance").setExecutor(new BalanceCommand());
        getCommand("gems").setExecutor(new GemsCommand());
        getCommand("reclaim").setExecutor(new ReclaimCommandHandler());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("staff").setExecutor(new StaffModeCommand(this));
        getCommand("robot").setExecutor(new RobotCommandHandler(this));
        getCommand("kit").setExecutor(new KitCommand(this));
        getCommand("casino").setExecutor(new CasinoCommand(this));
        getCommand("fly").setExecutor(new Fly());
        getCommand("crate").setExecutor(new CrateCommandHandler(this));
        getCommand("automessager").setExecutor(new AutoMessage(this));
    }

    private void listener(){

        PluginManager manager = Bukkit.getPluginManager();
        new ScoreboardHandler(this, new Board(), 20L);

        manager.registerEvents(new SpawnListener(), this);
        manager.registerEvents(new ChatFormatListener(), this);
        manager.registerEvents(new DataListener(), this);
        manager.registerEvents(new StaffModeListener(this), this);
        manager.registerEvents(new RobotListener(), this);
        manager.registerEvents(new FoodListener(), this);
        manager.registerEvents(new KitListener(this), this);
        manager.registerEvents(new CasinoListener(this), this);
        manager.registerEvents(new CrateListener(this), this);
        manager.registerEvents(new WeatherListener(), this);
        manager.registerEvents(new WelcomeListener(this), this);
    }

    public ReclaimHandler getReclaimHandler(){
        return this.reclaimHandler;
    }

    public FileConfiguration getConfig(){
        return this.configuration;
    }

    public FileConfiguration getReclaim(){ return this.reclaimConfiguration; }

    public FileConfiguration getData(){ return this.dataConfiguration; }

    public FileConfiguration getJoin(){ return this.joindataConfiguration; }

    public FileConfiguration getReclaimDataConfiguration(){ return this.reclaimDataConfiguration;}

    public Chat getChat(){
        return this.chat;
    }

    public File getConfigFile(){
        return this.config;
    }

    public File getDataFile(){ return this.data; }
}
