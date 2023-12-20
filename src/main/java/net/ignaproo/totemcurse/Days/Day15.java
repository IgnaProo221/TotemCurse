package net.ignaproo.totemcurse.Days;

import net.ignaproo.totemcurse.Items.Items;
import net.ignaproo.totemcurse.Utils.DayUtils;
import net.ignaproo.totemcurse.Utils.PlayerData;
import net.ignaproo.totemcurse.Utils.Utils;
import net.ignaproo.totemcurse.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class Day15 implements Listener {
    @EventHandler
    public void onFish(PlayerFishEvent event) {
        DayUtils.onDay(15, null, () -> {
            Player p = event.getPlayer();
            ItemStack item = p.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();
            int model = meta.getCustomModelData();
            if (meta.hasCustomModelData()) {
                if (model == 1 && (event.getState().equals(PlayerFishEvent.State.FISHING))) {
                    Vector hookLocation = event.getHook().getVelocity();

                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0F, 7.0F);
                    Entity getHook = event.getHook();
                    getHook.setVelocity(hookLocation.multiply(2.0D));
                }
            }
            if (meta.hasCustomModelData()) {
                if (model == 1 && event.getState().equals(PlayerFishEvent.State.REEL_IN)) {
                    Location playerLocation = p.getLocation();
                    Location hookLocation = event.getHook().getLocation();
                    Location change = hookLocation.subtract(playerLocation);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 140, 0));
                    p.setVelocity(change.toVector().multiply(0.5D));
                    p.setCooldown(Material.FISHING_ROD, 20 * 10);

                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0F, 7.0F);
                }
            }
        });
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        DayUtils.onDay(15, null, () -> {
            if (itemInHand != null && itemInHand.getType() == Material.FEATHER) {
                ItemMeta itemMeta = itemInHand.getItemMeta();
                if (itemMeta != null && itemMeta.hasCustomModelData() && itemMeta.getCustomModelData() == 1) {
                    if (player.getCooldown(Material.FEATHER) > 0) {
                        return;
                    }
                    Vector direction = player.getLocation().getDirection().normalize().multiply(1.5);
                    player.setVelocity(direction);
                    player.setCooldown(Material.FEATHER, 20 * 30);
                }
            }
            if (event.hasItem() && event.getItem().getType() == Material.STICK) {
                if (event.getItem().getItemMeta() != null &&
                        event.getItem().getItemMeta().hasCustomModelData() &&
                        event.getItem().getItemMeta().getCustomModelData() == 1) {
                    event.getItem().setItemMeta(Items.EmptySyringe().getItemMeta());
                    if (PlayerData.getPlayerData(player, "Enfermedad", "false").equalsIgnoreCase("true")) {

                        // Actualizar datos del jugador
                        PlayerData.setPlayerData(player, "Enfermedad", "false");
                        PlayerData.setPlayerData(player, "Vacunado", "true");
                        player.sendMessage(Utils.c(Utils.getPrefix() + "&7Te has recuperado de la enfermedad"));
                        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 2, 1.2F);
                        PlayerData.reloadPlayerData();
                    }
                }
            }
        });
    }

    public static void checkDayFifteen(Player player) {
        DayUtils.onDay(15, null, () -> {
            new BukkitRunnable() {
                @Override
                public void run() {
                    boolean tieneEnfermedad = Boolean.parseBoolean(PlayerData.getPlayerData(player, "Enfermedad", "false"));
                    boolean tieneVacuna = Boolean.parseBoolean(PlayerData.getPlayerData(player, "Vacunado", "false"));

                    if (tieneEnfermedad && !tieneVacuna) {
                        // Aplicar efectos
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 50, 0));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 50, 0));
                    }
                    if (isHoldingSkullShield(player)) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 1));
                    }
                }
            }.runTaskTimer(main.getInstance(), 0L, 5L);
        });
    }

    public static boolean isHoldingSkullShield(Player player) {
        ItemStack offHandItem = player.getInventory().getItemInOffHand();
        if (offHandItem != null && offHandItem.getType() == Material.SHIELD && offHandItem.hasItemMeta()) {
            ItemMeta itemMeta = offHandItem.getItemMeta();
            return itemMeta.hasCustomModelData() && itemMeta.getCustomModelData() == 1;
        }
        return false;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        DayUtils.onDay(15, null, () -> {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                if (player.isBlocking()) {
                    ItemStack shield = player.getInventory().getItemInOffHand();
                    if (shield != null && shield.getType() == Material.SHIELD && shield.hasItemMeta()) {
                        Random random = new Random();
                        if (random.nextDouble() <= 0.15) {
                            player.setCooldown(shield.getType(), 10 * 20); // 10 segundos en ticks
                        }
                    }
                }
            }
        });
    }

    public static void updatePlayerData() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String enfermedadValue = PlayerData.getPlayerData(player, "Enfermedad", null);
            String vacunadoValue = PlayerData.getPlayerData(player, "Vacunado", null);

            if (enfermedadValue == null) {
                PlayerData.setPlayerData(player, "Enfermedad", "true");
            }
            if (vacunadoValue == null) {
                PlayerData.setPlayerData(player, "Vacunado", "false");
            }
        }
        PlayerData.reloadPlayerData();
    }
    @EventHandler
    private void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        DayUtils.onDay(15, null, () -> {
            boolean tieneEnfermedad = Boolean.parseBoolean(PlayerData.getPlayerData(p, "Enfermedad", "false"));
            boolean tieneVacuna = Boolean.parseBoolean(PlayerData.getPlayerData(p, "Vacunado", "false"));

            if (tieneEnfermedad && !tieneVacuna) {
                Random r = new Random();
                if (r.nextDouble() <= 0.10) {
                    e.setCancelled(true);
                }
            }
        });
    }
}
