package net.ignaproo.totemcurse.Days;

import net.ignaproo.totemcurse.Items.Items;
import net.ignaproo.totemcurse.Utils.ConfigData;
import net.ignaproo.totemcurse.Utils.DayUtils;
import net.ignaproo.totemcurse.Utils.PlayerData;
import net.ignaproo.totemcurse.Utils.Utils;
import net.ignaproo.totemcurse.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;
import java.util.UUID;

public class Day35 implements Listener {
    @EventHandler
    private void onEat(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        DayUtils.onDay(35, null, () -> {
            if (item != null && item.getType() == Material.GOLDEN_APPLE) {
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        p.removePotionEffect(PotionEffectType.ABSORPTION);
                        p.removePotionEffect(PotionEffectType.REGENERATION);
                    }
                }.runTaskLater(main.getInstance(), 3L);

            } else if (item != null && item.getType() == Material.ENCHANTED_GOLDEN_APPLE) {
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        p.removePotionEffect(PotionEffectType.ABSORPTION);
                        p.removePotionEffect(PotionEffectType.REGENERATION);
                        p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                        p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                    }
                }.runTaskLater(main.getInstance(), 3L);
            }
        });
    }

    public static void updatePlayerData() {
        DayUtils.onDay(35, null, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                String enfermedadValue = PlayerData.getPlayerData(player, "LoseVida", null);
                String vacunadoValue = PlayerData.getPlayerData(player, "Cured", null);

                if (enfermedadValue == null) {
                    PlayerData.setPlayerData(player, "LoseVida", "true");
                    AttributeModifier modifier = new AttributeModifier("customHealth", -10.0, AttributeModifier.Operation.ADD_NUMBER);
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(modifier);
                }
                if (vacunadoValue == null) {
                    PlayerData.setPlayerData(player, "Cured", "false");
                }
            }
            PlayerData.reloadPlayerData();
        });
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        DayUtils.onDay(35, null, () -> {
            if (event.hasItem() && event.getItem().getType() == Material.STICK) {
                if (event.getItem().getItemMeta() != null &&
                        event.getItem().getItemMeta().hasCustomModelData() &&
                        event.getItem().getItemMeta().getCustomModelData() == 3) {
                    player.getInventory().setItemInMainHand(null);
                    if (PlayerData.getPlayerData(player, "LoseVida", "false").equalsIgnoreCase("true")) {

                        // Actualizar datos del jugador
                        PlayerData.setPlayerData(player, "LoseVida", "false");
                        PlayerData.setPlayerData(player, "Cured", "true");
                        player.sendMessage(Utils.c(Utils.getPrefix() + "&7Has recuperado tus 5 slots de vida."));
                        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 2, 0.7F);
                        AttributeModifier modifier = new AttributeModifier("customHealth", 10.0, AttributeModifier.Operation.ADD_NUMBER);
                        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(modifier);
                        PlayerData.reloadPlayerData();
                    }
                }
            }
        });
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            DayUtils.onDay(35, null, () -> {
                if (isEnvironmentalDamage(event)) {
                    event.setDamage(event.getDamage() * 5); // Duplica el daño ambiental para el día 15.
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
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();


        if (from != null && to != null && !from.getBlock().equals(to.getBlock())) {
            DayUtils.onDay(35, null, () -> {
                if (to.getBlock().getType() == Material.WATER) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 50, 2));
                }
            });
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Random random = new Random();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        DayUtils.onDay(35, null, () -> {
            if (!isNetheriteTool(itemInHand)) {
                player.damage(2.0);
            }
            if (random.nextDouble() <= 0.1) { // 3% de probabilidad
                if (ConfigData.getConfigValue("Storm.canBlockExplode", "false").equalsIgnoreCase("true")) {
                    Location blockLocation = event.getBlock().getLocation().add(0, 1, 0); // Suma 1 a la Y
                    spawnPrimedTNT(blockLocation);
                }
            }
        });
    }

    private boolean isNetheriteTool(ItemStack item) {
        if (item.getType() == Material.NETHERITE_SWORD ||
                item.getType() == Material.NETHERITE_PICKAXE ||
                item.getType() == Material.NETHERITE_AXE ||
                item.getType() == Material.NETHERITE_SHOVEL ||
                item.getType() == Material.NETHERITE_HOE) {

            ItemMeta itemMeta = item.getItemMeta();
            return itemMeta != null && itemMeta.hasCustomModelData() && itemMeta.getCustomModelData() == 2;
        }
        return false;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Random random = new Random();

        DayUtils.onDay(25, null, () -> {
            if (random.nextDouble() <= 0.1) { // 3% de probabilidad
                if (ConfigData.getConfigValue("Storm.canBlockExplode", "false").equalsIgnoreCase("true")) {
                    Location blockLocation = event.getBlock().getLocation().add(0, 1, 0); // Suma 1 a la Y
                    spawnPrimedTNT(blockLocation);
                }
            }
        });
    }
    private void spawnPrimedTNT(Location location) {
        TNTPrimed tnt = (TNTPrimed) location.getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
        tnt.setFuseTicks(40);
    }


}
