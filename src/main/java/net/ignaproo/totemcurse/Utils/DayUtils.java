package net.ignaproo.totemcurse.Utils;

import net.ignaproo.totemcurse.Days.Day15;
import net.ignaproo.totemcurse.Days.Day3;
import net.ignaproo.totemcurse.Days.Day30;
import net.ignaproo.totemcurse.Days.Day40;
import net.ignaproo.totemcurse.main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.Objects;

public class DayUtils {

    public static void updateDay() {
        int day = ConfigData.getDay();
        ConfigData.setConfigValue("day", String.valueOf(day));
        ConfigData.setConfigValue("date", String.valueOf(LocalDate.now()));
    }

    public static void notifyPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 180, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 100, 0));
            player.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 2, 0.6F);

            player.sendTitle(Utils.c("&7!!! <GRADIENT:F22E2E>&lDía " + ConfigData.getDay() + "</GRADIENT:B32502> &r&7!!!"), Utils.c("&7Felicidades por superar el &ddía: " + (ConfigData.getDay() - 1)), 30, 140, 40);
            player.sendMessage(Utils.c(Utils.getPrefix() + "&eHa llegado el día: " + ConfigData.getDay()));
            new BukkitRunnable() {

                @Override
                public void run() {
                    player.sendMessage(Utils.c(Utils.getPrefix() + "<GRADIENT:FF0000>&lLastStorm</GRADIENT:8F3700> &r&eAproximándose"));
                    player.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 1.4F, 0.8F);
                }
            }.runTaskLater(main.getInstance(), 60);
            if (Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 0));
            }
        }
    }

    public static void taskDay() {
        new BukkitRunnable() {
            @Override
            public void run() {
                String dateUtils = ConfigData.getServerDate();
                LocalDate fechaActual = LocalDate.now();
                if (!dateUtils.equals(fechaActual.toString())) {
                    updateDay();
                    notifyPlayers();
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        Day3.checkDayThree(pl);
                        Day15.checkDayFifteen(pl);
                        Day40.startTNTCounter(pl);
                        Day40.checkDayForty(pl);
                    }
                }
            }
        }.runTaskTimer(main.getPlugin(main.class), 0, 20L); //20 (ticks) = 1 segundo
    }

    public static void onDay(int day, @Nullable Integer until, Runnable run) {
        int nowDay = ConfigData.getDay();
        if (nowDay >= day && (until == null || (nowDay <= until))) {
            run.run();
        }
    }
}
