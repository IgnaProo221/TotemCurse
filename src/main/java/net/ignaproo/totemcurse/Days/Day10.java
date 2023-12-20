package net.ignaproo.totemcurse.Days;

import net.ignaproo.totemcurse.Utils.DayUtils;
import net.ignaproo.totemcurse.main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Day10 implements Listener {
    private final Map<PotionEffectType, PotionEffect> negativeEffects = new HashMap<>();
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            // Verificar si el evento es por daño de caída
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                DayUtils.onDay(10, null, () -> {

                    if (hasUpgradedBoots(player)) {
                        // Generar una probabilidad del 10%
                        if (Math.random() <= 0.1) {
                            player.setCooldown(Material.NETHERITE_BOOTS, 20);
                            event.setCancelled(true);
                        }
                    }
                });
            }
        }
    }

    public boolean hasUpgradedBoots(Player player) {
        ItemStack boots = player.getInventory().getBoots();
        return boots != null && boots.hasItemMeta() &&
                boots.getItemMeta().hasCustomModelData() &&
                boots.getItemMeta().getCustomModelData() == 1;
    }
    @EventHandler
    public void onCreeperSpawn(CreatureSpawnEvent event) {
        if (event.getEntity() instanceof Creeper && event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {

            // Verificar si es el día 10 o posterior
            DayUtils.onDay(10, 29, () -> {
                Random random = new Random();
                if (random.nextDouble() <= 0.3) {
                    Creeper silentCreeper = (Creeper) event.getEntity();
                    silentCreeper.setSilent(true);
                }
            });
        }
    }

    @EventHandler
    public void onMilkDrink(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        DayUtils.onDay(10, 24, () -> {

            if (event.getItem().getType().equals(Material.MILK_BUCKET)) {
                saveNegativeEffects(player);
                removeHarmfulEffects(player);
                restoreNegativeEffects(player);
            }
        });
    }

    public void saveNegativeEffects(Player player) {
        negativeEffects.clear(); // Limpiar la lista antes de guardar nuevos efectos
        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (isNegativeEffect(effect.getType())) {
                negativeEffects.put(effect.getType(), effect);
            }
        }
    }

    public void removeHarmfulEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (isNegativeEffect(effect.getType())) {
                player.removePotionEffect(effect.getType());
            }
        }
    }

    public void restoreNegativeEffects(Player player) {
        Bukkit.getScheduler().runTaskLater(main.getInstance(), () -> {
            for (PotionEffect effect : negativeEffects.values()) {
                player.addPotionEffect(effect);
            }
        }, 3L); // 3 ticks (20 ticks = 1 segundo)
    }

    public boolean isNegativeEffect(PotionEffectType type) {
        return type.equals(PotionEffectType.BAD_OMEN);
    }
    @EventHandler
    public void onEntityDamagedByEnderPearl(EntityDamageByEntityEvent event) {
        DayUtils.onDay(10, null, () -> {
            if (event.getDamager() instanceof EnderPearl) {
                event.setDamage(event.getDamage() * 2);
            }
        });
    }
}