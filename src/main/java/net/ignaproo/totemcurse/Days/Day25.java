package net.ignaproo.totemcurse.Days;

import net.ignaproo.totemcurse.Utils.DayUtils;
import net.ignaproo.totemcurse.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class Day25 implements Listener {
    private final Map<PotionEffectType, PotionEffect> negativeEffects = new HashMap<>();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Random random = new Random();

        DayUtils.onDay(25, null, () -> {
            if (random.nextDouble() <= 0.03) { // 3% de probabilidad
                Location blockLocation = event.getBlock().getLocation().add(0, 1, 0); // Suma 1 a la Y
                spawnPrimedTNT(blockLocation);
            }
        });
    }

    private void spawnPrimedTNT(Location location) {
        TNTPrimed tnt = (TNTPrimed) location.getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
        tnt.setFuseTicks(60);
    }

    @EventHandler
    public void onMilkDrink(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        DayUtils.onDay(25, null, () -> {

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
            negativeEffects.put(effect.getType(), effect);
        }
    }

    public void removeHarmfulEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }

    public void restoreNegativeEffects(Player player) {
        Bukkit.getScheduler().runTaskLater(main.getInstance(), () -> {
            for (PotionEffect effect : negativeEffects.values()) {
                player.addPotionEffect(effect);
            }
        }, 3L); // 3 ticks (20 ticks = 1 segundo)
    }

    @EventHandler
    private void onEat(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        DayUtils.onDay(25, null, () -> {

            if (item != null && item.getType() == Material.GOLDEN_CARROT) {
                ItemMeta itemMeta = item.getItemMeta();
                if (itemMeta != null && itemMeta.hasCustomModelData() && itemMeta.getCustomModelData() == 1) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 10, 1));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 5, 4));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 25, 1));
                }

            }
            if (item != null && item.getType() == Material.GOLDEN_APPLE) {
                p.setCooldown(Material.GOLDEN_APPLE, 100);

            } else if (item != null && item.getType() == Material.ENCHANTED_GOLDEN_APPLE) {
                p.setCooldown(Material.ENCHANTED_GOLDEN_APPLE, 20);
            }
        });
    }

}
