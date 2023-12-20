package net.ignaproo.totemcurse.Controller;

import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Subcommand;
import net.ignaproo.totemcurse.Utils.ConfigData;
import net.ignaproo.totemcurse.Utils.DayUtils;
import net.ignaproo.totemcurse.Utils.Utils;
import net.ignaproo.totemcurse.main;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class RadioactiveStorm {
    private static BukkitTask stormTask;
    private static BukkitTask tier2StormEffectsTask;
    private static BukkitTask tier3StormEffectsTask;
    public static BossBar radioactiveStormTimer;
    public static void startStorm(String tier, Player p, int duration, int originDuration) {

        // Eliminar la tormenta actual y su BossBar asociada, si existe
        if (radioactiveStormTimer != null) {
            radioactiveStormTimer.removeAll();
        }

        // Crear una nueva BossBar para la tormenta
        radioactiveStormTimer = Bukkit.createBossBar(Utils.c("&3☣ &a&lRadioactive Storm &a3☣: &b&l" + segundosAFormatoTiempo(duration) + " &6&l(" + tier + ")"), BarColor.GREEN, BarStyle.SOLID);
        radioactiveStormTimer.setProgress(1.0); // Valor inicial, 1.0 es la barra completa
        radioactiveStormTimer.setVisible(true);
        activateStorm(tier, duration);
        ConfigData.setConfigValue("Storm.active", "true");
        ConfigData.setConfigValue("Storm.tier", String.valueOf(tier));
        ConfigData.setConfigValue("Storm.duration", String.valueOf(duration));
        ConfigData.setConfigValue("Storm.originDuration", String.valueOf(originDuration));
        ConfigData.setConfigValue("Storm.canBlockExplode", "true");
            radioactiveStormTimer.addPlayer(p);
        if (stormTask != null) {
            stormTask.cancel();
        }

        AtomicInteger stormDurationInSeconds = new AtomicInteger(duration);
        stormTask = Bukkit.getScheduler().runTaskTimer(main.getInstance(), () -> {

            if (stormDurationInSeconds.get() > 0) {
                ConfigData.setConfigValue("Storm.active", "true");
                ConfigData.setConfigValue("Storm.tier", String.valueOf(tier));
                ConfigData.setConfigValue("Storm.duration", String.valueOf(stormDurationInSeconds));
                ConfigData.setConfigValue("Storm.originDuration", String.valueOf(originDuration));
                ConfigData.setConfigValue("Storm.canBlockExplode", "true");
                double progress = (double) stormDurationInSeconds.get() / originDuration;
                radioactiveStormTimer.setProgress(progress);
                radioactiveStormTimer.setTitle(Utils.c("&3☣ &a&lRadioactive Storm &a3☣: &b&l" + segundosAFormatoTiempo(stormDurationInSeconds.get()) + " &6&l(" + tier + ")"));
                stormDurationInSeconds.decrementAndGet();
            } else {
                ConfigData.setConfigValue("Storm.active", "false");
                ConfigData.setConfigValue("Storm.tier", String.valueOf(tier));
                ConfigData.setConfigValue("Storm.duration", String.valueOf(stormDurationInSeconds));
                ConfigData.setConfigValue("Storm.originDuration", String.valueOf(originDuration));
                ConfigData.setConfigValue("Storm.canBlockExplode", "false");
                radioactiveStormTimer.removeAll();
                Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE,true);
                stormTask.cancel();
            }
        }, 0L, 20L);

    }
    public static void deleteStorm(BossBar bar) {
        bar.removeAll();

    }
    private static void activateStorm(String tier, int duration) {
        World world = Bukkit.getWorld("world");
        world.setStorm(false);
        world.setThundering(false);
        switch (tier) {
            case "Tier1":
                DayUtils.onDay(30, null, () -> {
                    Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE,false);
                    Bukkit.getWorld("world").setTime(18000);
                });
                world.setThunderDuration(0);
                world.setStorm(true);
                world.setWeatherDuration(duration * 20);
                break;
            case "Tier2":
                DayUtils.onDay(30, null, () -> {
                    Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE,false);
                    Bukkit.getWorld("world").setTime(18000);
                });
                world.setThunderDuration(0);
                world.setStorm(true);
                tier2StormEffectsTask = new BukkitRunnable() {

                    @Override
                    public void run() {
                        for (LivingEntity entity : Bukkit.getWorld("world").getLivingEntities()) {
                            if (ConfigData.getConfigValue("Storm.active", "false").equalsIgnoreCase("true")) {
                                if (entity.getType() != EntityType.PLAYER && entity.hasAI()) {

                                    entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,100, 0));
                                    entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 0));
                                }
                            } else if (ConfigData.getConfigValue("Storm.active", "false").equalsIgnoreCase("false")) {
                                tier2StormEffectsTask.cancel();
                            }
                        }
                    }
                }.runTaskTimer(main.getInstance(), 0L, 10L);
                DayUtils.onDay(30, null, () -> {
                    Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
                    int totalPlayers = onlinePlayers.size();

                    if (totalPlayers > 0) {
                        // Calcular la probabilidad del 1%
                        Random random = new Random();
                        int randomValue = random.nextInt(100) + 1; // Genera un número entre 1 y 100

                        if (randomValue == 1) { // Probabilidad del 1%
                            int playerIndex = random.nextInt(totalPlayers); // Índice del jugador aleatorio
                            Player randomPlayer = (Player) onlinePlayers.toArray()[playerIndex]; // Jugador aleatorio

                            // Spawnea una TNT encendida cerca del jugador aleatorio
                            randomPlayer.getWorld().spawn(randomPlayer.getLocation(), TNTPrimed.class, tnt -> {
                                tnt.setFuseTicks(100); // Tiempo de detonación
                            });
                        }
                    }
                });
                world.setWeatherDuration(duration * 20);
                break;
            case "Tier3":
                world.setThunderDuration(0);
                world.setStorm(true);
                Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE,false);
                Bukkit.getWorld("world").setTime(18000);
                tier3StormEffectsTask = new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (LivingEntity entity : Bukkit.getWorld("world").getLivingEntities()) {
                            if (ConfigData.getConfigValue("Storm.active", "false").equalsIgnoreCase("true")) {
                                if (entity.getType() != EntityType.PLAYER && entity.hasAI()) {
                                    DayUtils.onDay(1, 29, () -> {
                                        entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0));
                                        entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 0));
                                    });
                                    DayUtils.onDay(30, null, () -> {
                                        entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
                                        entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 3));
                                    });
                                }
                            }else if (ConfigData.getConfigValue("Storm.active", "false").equalsIgnoreCase("false")) {
                                tier3StormEffectsTask.cancel();
                            }
                        }
                    }
                }.runTaskTimer(main.getInstance(), 0L, 10L);

                world.setWeatherDuration(duration * 20);
                break;
            default:
                // Mensaje de error si el tipo de tormenta no es válido
                Bukkit.getLogger().warning("Tipo de tormenta no reconocido: " + tier);
                break;
        }
    }
    public static String segundosAFormatoTiempo(long segundos) {
        LocalTime tiempo = LocalTime.ofSecondOfDay(segundos);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return tiempo.format(formatter);
    }
}
