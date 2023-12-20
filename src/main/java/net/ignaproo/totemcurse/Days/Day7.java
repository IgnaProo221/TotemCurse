package net.ignaproo.totemcurse.Days;

import net.ignaproo.totemcurse.Entities.MobTypeList;
import net.ignaproo.totemcurse.Entities.MobUtils;
import net.ignaproo.totemcurse.Utils.ConfigData;
import net.ignaproo.totemcurse.Utils.DayUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Day7 implements Listener {
    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        World fromWorld = event.getFrom();
        World toWorld = player.getWorld();

        DayUtils.onDay(7, 39, () -> {
            if (fromWorld != toWorld) {
                double chance = 0.1; // Probabilidad del 10% (puedes ajustar este valor)
                Random random = new Random();

                // Generar un número aleatorio entre 0 y 1
                double randomValue = random.nextDouble();

                // Si el número aleatorio es menor o igual a la probabilidad, spawnea un Enderman
                if (randomValue <= chance) {
                    spawnEnderman(player.getLocation(), player);
                }
            }
        });
    }

    // Método para spawnear un Enderman en la posición del jugador
    private void spawnEnderman(Location loc, Player player) {
        Enderman enderman = loc.getWorld().spawn(loc, Enderman.class);
        net.ignaproo.totemcurse.Entities.Mobs.Enderman.ChangerEnderman(enderman, player);
        enderman.setTarget(player);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            DayUtils.onDay(7, 34, () -> {
                if (isEnvironmentalDamage(event)) {
                    event.setDamage(event.getDamage() * 2); // Duplica el daño ambiental para el día 15.
                }
            });
        }
    }
    private boolean isEnvironmentalDamage(EntityDamageEvent event) {
        return event.getCause() == EntityDamageEvent.DamageCause.FIRE // Agrega más causas si es necesario.
                || event.getCause() == EntityDamageEvent.DamageCause.LAVA
                || event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION
                || event.getCause() == EntityDamageEvent.DamageCause.CRAMMING
                || event.getCause() == EntityDamageEvent.DamageCause.DRYOUT
                || event.getCause() == EntityDamageEvent.DamageCause.THORNS
                || event.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION
                || event.getCause() == EntityDamageEvent.DamageCause.STARVATION
                || event.getCause() == EntityDamageEvent.DamageCause.DROWNING
                || event.getCause() == EntityDamageEvent.DamageCause.MELTING
                || event.getCause() == EntityDamageEvent.DamageCause.FALL
                || event.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK
                || event.getCause() == EntityDamageEvent.DamageCause.POISON
                || event.getCause() == EntityDamageEvent.DamageCause.WITHER
                || event.getCause() == EntityDamageEvent.DamageCause.MAGIC
                || event.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL
                || event.getCause() == EntityDamageEvent.DamageCause.FREEZE
                || event.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR
                || event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK
                || event.getCause() == EntityDamageEvent.DamageCause.LIGHTNING;
    }

    @EventHandler
    private void onEat(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        DayUtils.onDay(7, null, () -> {

            if (item != null && item.getType() == Material.PUMPKIN_PIE && item.hasItemMeta()) {
                ItemMeta itemMeta = item.getItemMeta();
                if (itemMeta != null && itemMeta.hasCustomModelData() && itemMeta.getCustomModelData() == 1) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 2));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 20, 1));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 60 * 2, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 20, 1));
                }

            }
        });
    }
    @EventHandler
    public void EntityDamagedByEntity(EntityDamageByEntityEvent event) {
        MobUtils.CustomMobEvent(event.getDamager(), MobTypeList.ChangerEnderman(), 7, () -> {
            if (event.getEntityType() == EntityType.PLAYER) {
                Player p = (Player) event.getEntity();
                p.getWorld().spawnEntity(p.getLocation(), EntityType.ENDERMITE);
            }
        });
    }
}
