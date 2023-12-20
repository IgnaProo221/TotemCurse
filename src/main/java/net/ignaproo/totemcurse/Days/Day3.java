package net.ignaproo.totemcurse.Days;

import net.ignaproo.totemcurse.Utils.ConfigData;
import net.ignaproo.totemcurse.Utils.DayUtils;
import net.ignaproo.totemcurse.main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class Day3 implements Listener {
    public static void checkDayThree(Player player) {
        DayUtils.onDay(3, null, () -> {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (isHoldingEmergencyAxe(player)) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 0));
                    }
                }
            }.runTaskTimer(main.getInstance(), 0L, 5L);
        });
    }

    public static boolean isHoldingEmergencyAxe(Player player) {
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        if (mainHandItem != null && mainHandItem.getType() == Material.IRON_AXE && mainHandItem.hasItemMeta()) {
            ItemMeta itemMeta = mainHandItem.getItemMeta();
            return itemMeta.hasCustomModelData() && itemMeta.getCustomModelData() == 1;
        }
        return false;
    }

    private final double COOLDOWN_CHANCE = 0.05; // Probabilidad del 10% para el cooldown

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();

        DayUtils.onDay(3, null, () -> {
            if (mainHandItem != null && mainHandItem.getType() == Material.IRON_AXE && mainHandItem.hasItemMeta()) {
                ItemMeta itemMeta = mainHandItem.getItemMeta();
                if (itemMeta != null && itemMeta.hasCustomModelData() && itemMeta.getCustomModelData() == 1) {
                    if (player.getCooldown(Material.IRON_AXE) > 0) {
                        event.setCancelled(true);
                        return;
                    }
                    Random random = new Random();
                    if (random.nextDouble() <= COOLDOWN_CHANCE) {
                        player.setCooldown(Material.IRON_AXE, 100);
                    }
                }
            }
        });
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();

        DayUtils.onDay(3, null, () -> {
            if (mainHandItem != null && mainHandItem.getType() == Material.IRON_AXE && mainHandItem.hasItemMeta()) {
                ItemMeta itemMeta = mainHandItem.getItemMeta();
                if (itemMeta != null && itemMeta.hasCustomModelData() && itemMeta.getCustomModelData() == 1) {
                    Random random = new Random();
                    if (random.nextDouble() <= COOLDOWN_CHANCE) {
                        player.setCooldown(Material.IRON_AXE, 100);
                    }
                }
            }
        });
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        DayUtils.onDay(3, null, () -> {
            if (itemInHand != null && itemInHand.getType() == Material.GREEN_DYE) {
                ItemMeta itemMeta = itemInHand.getItemMeta();
                if (itemMeta != null && itemMeta.hasCustomModelData() && itemMeta.getCustomModelData() == 1) {
                    if (player.getCooldown(Material.GREEN_DYE) > 0) {
                        return;
                    }
                    player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20 * 60, 4));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 60, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 60 * 6, 0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 60 * 2, 1));
                    player.setCooldown(Material.GREEN_DYE, 60 * 20);
                }
            }
        });
    }
}
