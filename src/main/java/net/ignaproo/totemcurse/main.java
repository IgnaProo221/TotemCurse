package net.ignaproo.totemcurse;

import co.aikar.commands.BukkitCommandManager;
import net.ignaproo.totemcurse.Commands.Commands;
import net.ignaproo.totemcurse.Controller.ArmorController;
import net.ignaproo.totemcurse.Controller.MysticalController;
import net.ignaproo.totemcurse.Controller.RadioactiveStorm;
import net.ignaproo.totemcurse.Days.*;
import net.ignaproo.totemcurse.Events.DamageEvent;
import net.ignaproo.totemcurse.Events.DeathEvent;
import net.ignaproo.totemcurse.Events.JoinEvent;
import net.ignaproo.totemcurse.Events.TotemEvent;
import net.ignaproo.totemcurse.Utils.ConfigData;
import net.ignaproo.totemcurse.Utils.DayUtils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import static net.ignaproo.totemcurse.Controller.RadioactiveStorm.radioactiveStormTimer;

public final class main extends JavaPlugin {
    private static main instance;

    @Override
    public void onEnable() {
        instance = this;

        for (Player pl : Bukkit.getOnlinePlayers()) {
            Day3.checkDayThree(pl);
            Day15.checkDayFifteen(pl);
            Day15.updatePlayerData();
            Day30.underWaterFall(pl);
            Day35.updatePlayerData();
            Day40.startTNTCounter(pl);
            Day40.updatePlayerData();
            Day40.checkDayForty(pl);
            pl.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "ignoreCurse"), PersistentDataType.STRING, "false");
        }
        BukkitCommandManager commandManager = new BukkitCommandManager(this);
        commandManager.registerCommand(new Commands());
        getServer().getPluginManager().registerEvents(new TotemEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new DamageEvent(), this);
        getServer().getPluginManager().registerEvents(new Day3(), this);
        getServer().getPluginManager().registerEvents(new Day7(), this);
        getServer().getPluginManager().registerEvents(new Day10(), this);
        getServer().getPluginManager().registerEvents(new Day15(), this);
        getServer().getPluginManager().registerEvents(new Day17(), this);
        getServer().getPluginManager().registerEvents(new Day25(), this);
        getServer().getPluginManager().registerEvents(new Day30(), this);
        getServer().getPluginManager().registerEvents(new Day35(), this);
        getServer().getPluginManager().registerEvents(new Day40(), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        ArmorController armor = new ArmorController();
        armor.startTask();
        MysticalController mysc = new MysticalController();
        mysc.startTask();
        World world = Bukkit.getWorld("world");
        PersistentDataContainer dataContainer = world.getPersistentDataContainer();
        DayUtils.taskDay();
        if (ConfigData.getConfigValue("Storm.active", "false").equalsIgnoreCase("true")) {
            String tier = ConfigData.getConfigValue("Storm.tier", "Tier1");
            for (Player pl : Bukkit.getOnlinePlayers()) {
                RadioactiveStorm.startStorm(tier, pl, Integer.parseInt(ConfigData.getConfigValue("Storm.duration", "0")), Integer.parseInt(ConfigData.getConfigValue("Storm.originDuration", "0")));
            }
        }
    }

    @Override
    public void onDisable() {
        if (radioactiveStormTimer != null) {
            RadioactiveStorm.deleteStorm(radioactiveStormTimer);
        }
    }
    public static main getInstance() {
        return instance;
    }
}
