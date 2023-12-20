package net.ignaproo.totemcurse.Days;

import net.ignaproo.totemcurse.Utils.ConfigData;
import net.ignaproo.totemcurse.Utils.DayUtils;
import net.ignaproo.totemcurse.Utils.PlayerData;
import net.ignaproo.totemcurse.Utils.Utils;
import net.ignaproo.totemcurse.main;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.UUID;

public class Day40 implements Listener {
    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        World fromWorld = event.getFrom();
        World toWorld = player.getWorld();

        DayUtils.onDay(40, null, () -> {
            if (fromWorld != toWorld) {
                double chance = 0.7; // Probabilidad del 10% (puedes ajustar este valor)
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

    public static void updatePlayerData() {
        DayUtils.onDay(40, null, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                String enfermedadValue = PlayerData.getPlayerData(player, "LoseVidaLast", null);
                String vacunadoValue = PlayerData.getPlayerData(player, "CuredLast", null);

                if (enfermedadValue == null) {
                    PlayerData.setPlayerData(player, "LoseVidaLast", "true");
                    AttributeModifier modifier = new AttributeModifier("customHealth", -10.0, AttributeModifier.Operation.ADD_NUMBER);
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(modifier);
                }
                if (vacunadoValue == null) {
                    PlayerData.setPlayerData(player, "CuredLast", "false");
                }
            }
            PlayerData.reloadPlayerData();
        });
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        DayUtils.onDay(40, null, () -> {
            if (event.hasItem() && event.getItem().getType() == Material.STICK) {
                if (event.getItem().getItemMeta() != null &&
                        event.getItem().getItemMeta().hasCustomModelData() &&
                        event.getItem().getItemMeta().getCustomModelData() == 4) {
                    if (PlayerData.getPlayerData(player, "LoseVidaLast", "false").equalsIgnoreCase("true")) {
                        player.getInventory().setItemInMainHand(null);

                        // Actualizar datos del jugador
                        PlayerData.setPlayerData(player, "LoseVidaLast", "false");
                        PlayerData.setPlayerData(player, "CuredLast", "true");
                        player.sendMessage(Utils.c(Utils.getPrefix() + "&7Has recuperado tus 5 slots de vida."));
                        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 2, 0.2F);
                        AttributeModifier modifier = new AttributeModifier("customHealth", 10.0, AttributeModifier.Operation.ADD_NUMBER);
                        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(modifier);
                        PlayerData.reloadPlayerData();
                    }
                }
            }
        });
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        DayUtils.onDay(40, null, () -> {
            if (event.getEntityType() == EntityType.RABBIT) {
                Rabbit rabbit = (Rabbit) event.getEntity();
                if (rabbit.getRabbitType() == Rabbit.Type.BROWN || rabbit.getRabbitType() == Rabbit.Type.WHITE) {
                    if (Math.random() <= 0.01) { // Probabilidad del 5% de que sea un conejo asesino
                        World world = rabbit.getWorld();
                        Rabbit killerRabbit = (Rabbit) world.spawnEntity(rabbit.getLocation(), EntityType.RABBIT);
                        killerRabbit.setRabbitType(Rabbit.Type.THE_KILLER_BUNNY); // Conejo asesino
                        killerRabbit.setMaxHealth(300); // Configuración de la salud
                        killerRabbit.setHealth(300); // Configuración de la salud actual
                        killerRabbit.setCustomName("Killer Rabbit");
                        killerRabbit.setCustomNameVisible(true);
                    }
                }
            }
        });
    }

    public static void startTNTCounter(Player p) {
        DayUtils.onDay(40, null, () -> {
            new BukkitRunnable() {

                @Override
                public void run() {
                    Location blockLocation = p.getLocation().getBlock().getLocation();
                    spawnPrimedTNT(blockLocation);
                }
            }.runTaskTimer(main.getInstance(), 3600 * 20, 3600 * 20);
        });
    }
    private static void spawnPrimedTNT(Location location) {
        TNTPrimed tnt = (TNTPrimed) location.getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
        tnt.setFuseTicks(60);
    }
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            DayUtils.onDay(40, null, () -> {
               if (player.getFoodLevel() < 1) {
                   player.sendMessage(String.valueOf(player.getFoodLevel()));
                   player.setHealth(0.0);
                   player.setFoodLevel(20);
               }
            });
        }
    }
    public static void checkDayForty(Player player) {
        DayUtils.onDay(40, null, () -> {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (player.hasPotionEffect(PotionEffectType.LUCK)) {
                        player.sendTitle(Utils.c(ConfigData.getConfigValue("unicode", "汉")), null, 0, 50, 20);
                    }
                    boolean tieneEnfermedad = Boolean.parseBoolean(PlayerData.getPlayerData(player, "LoseVidaLast", "false"));
                    boolean tieneVacuna = Boolean.parseBoolean(PlayerData.getPlayerData(player, "CuredLast", "false"));

                    if (tieneEnfermedad && !tieneVacuna) {
                        // Aplicar efectos
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 50, 0));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 50, 0));
                    }
                }
            }.runTaskTimer(main.getInstance(), 0L, 5L);
        });
    }
}
