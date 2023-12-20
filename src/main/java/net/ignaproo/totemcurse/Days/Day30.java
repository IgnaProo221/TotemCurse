package net.ignaproo.totemcurse.Days;

import net.ignaproo.totemcurse.Utils.ConfigData;
import net.ignaproo.totemcurse.Utils.DayUtils;
import net.ignaproo.totemcurse.main;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.UUID;

public class Day30 implements Listener {
    private static final UUID HEALTH_MODIFIER_UUID = UUID.randomUUID();
    private static final AttributeModifier HEALTH_MODIFIER = new AttributeModifier(
            HEALTH_MODIFIER_UUID, "HealthBonus", -2.0, AttributeModifier.Operation.ADD_NUMBER);

    @EventHandler
    private void onDamage(EntityDamageEvent e) {
        DayUtils.onDay(25, null, () -> {
            Random random = new Random();
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (random.nextDouble() < 0.001) {
                    if (e.getEntity() instanceof Player) {
                        Player player = (Player) e.getEntity();
                        applyHealthModifier(player);
                    }
                }
            }
            if (e.getCause() == EntityDamageEvent.DamageCause.DRAGON_BREATH) {
                if (e.getEntity() instanceof Player) {
                    e.setDamage(e.getDamage() * 4);
                }
            }
            if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                if (e.getEntity() instanceof Player) {
                    e.setDamage(e.getDamage() * 10);
                }
            }
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (e.getEntity() instanceof Player) {
                    Player player = (Player) e.getEntity();
                    if (hasUpgradedBoots(player)) {
                        // Generar una probabilidad del 10%
                        if (Math.random() <= 0.5) {
                            player.setCooldown(Material.NETHERITE_BOOTS, 20);
                            e.setCancelled(true);
                        }
                    }
                }
            }
        });
    }


    public void applyHealthModifier(Player player) {
        Attribute healthAttribute = Attribute.GENERIC_MAX_HEALTH;
        double currentHealth = player.getAttribute(healthAttribute).getBaseValue();

        if (!hasHealthModifier(player)) {
            player.getAttribute(healthAttribute).addModifier(HEALTH_MODIFIER);
            player.setHealth(currentHealth - 2); // Ajustar la salud actual al modificar el atributo
        }
    }

    public boolean hasHealthModifier(Player player) {
        for (AttributeModifier modifier : player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getModifiers()) {
            if (modifier.getUniqueId().equals(HEALTH_MODIFIER_UUID)) {
                return true;
            }
        }
        return false;
    }

    @EventHandler
    private void onEat(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        DayUtils.onDay(30, null, () -> {
            if (item != null && item.getType() == Material.APPLE) {
                ItemMeta itemMeta = item.getItemMeta();
                if (itemMeta != null && itemMeta.hasCustomModelData() && itemMeta.getCustomModelData() == 1) {
                    p.setCooldown(Material.APPLE, 100);
                }

            }
        });
    }
    public boolean hasUpgradedBoots(Player player) {
        ItemStack boots = player.getInventory().getBoots();
        return boots != null && boots.hasItemMeta() &&
                boots.getItemMeta().hasCustomModelData() &&
                boots.getItemMeta().getCustomModelData() == 2;
    }
    @EventHandler
    public void onCreeperSpawn(CreatureSpawnEvent event) {
        DayUtils.onDay(30, null, () -> {
            World world = event.getLocation().getWorld();
            int currentLimit = world.getMonsterSpawnLimit();
            world.setMonsterSpawnLimit(currentLimit * 2);
        });
        if (event.getEntity() instanceof Creeper && event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {

            // Verificar si es el día 30 o posterior
            DayUtils.onDay(30, null, () -> {
                Creeper silentCreeper = (Creeper) event.getEntity();
                silentCreeper.setSilent(true);
            });
        }
    }
    public static boolean estaBajoLluvia(Player player) {
        int blockLocation = player.getLocation().getWorld().getHighestBlockYAt(player.getLocation());
        if(blockLocation <= player.getLocation().getY()) {
            return player.getWorld().hasStorm();
        }else {
            return false;
        }
    }
    public static void underWaterFall(Player p) {
        DayUtils.onDay(30, null, () -> {

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (estaBajoLluvia(p)) {
                        if (ConfigData.getConfigValue("Storm.active", "false").equalsIgnoreCase("true")) {
                            if (wearingUpgradedArmor(p)) {
                                return;
                            }
                            p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 50, 2));
                        }
                    }
                }
            }.runTaskTimer(main.getInstance(), 0L, 10L);
        });
    }

    public static boolean wearingUpgradedArmor(Player player) {
        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack chestplate = player.getInventory().getChestplate();
        ItemStack leggings = player.getInventory().getLeggings();
        ItemStack boots = player.getInventory().getBoots();

        return isUpgradedArmorPiece(helmet) && isUpgradedArmorPiece(chestplate)
                && isUpgradedArmorPiece(leggings) && isUpgradedArmorPiece(boots);
    }

    public static boolean isUpgradedArmorPiece(ItemStack item) {
        return item != null && item.hasItemMeta() &&
                item.getItemMeta().hasCustomModelData() &&
                item.getItemMeta().getCustomModelData() == 2;
    }

}
