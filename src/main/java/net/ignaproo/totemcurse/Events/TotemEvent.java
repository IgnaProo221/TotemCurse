package net.ignaproo.totemcurse.Events;

import net.ignaproo.totemcurse.Utils.DayUtils;
import net.ignaproo.totemcurse.Utils.Utils;
import net.ignaproo.totemcurse.main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class TotemEvent implements Listener {
    @EventHandler
    public void onTotem(EntityResurrectEvent e) {
        if (!(e.getEntity() instanceof Player p)) return;
        if (p.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING ||
                p.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING) {

            if (p.getCooldown(Material.TOTEM_OF_UNDYING) > 1) {
                e.setCancelled(true);
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.sendMessage(Utils.getPrefix() + Utils.c("&7El totem del jugador: &e&l" + p.getName() + " &7Estaba en cooldown y ha muerto por: &d&l" + Utils.getCustomCause(p.getLastDamageCause())));
                }
                return;
            }

            detectModelData(p, 1, true, () -> {
                DayUtils.onDay(40, null, () -> {
                    Random r = new Random();
                    if (r.nextDouble() <= 0.03) {
                        e.setCancelled(true);
                        p.setHealth(0.0);
                        for (Player pl : Bukkit.getOnlinePlayers()) {
                            pl.sendMessage(Utils.getPrefix() + Utils.c("&7El totem del jugador: &e&l" + p.getName() + " &7Ha fallado y ha muerto por: &d&l" + Utils.getCustomCause(p.getLastDamageCause())));
                        }
                        return;
                    }
                });
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, (20 * 30), 3));
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, (20 * 15), 2));
                p.setHealth(p.getHealth() + 14);
            }, () -> {
            });
            detectModelData(p, 2, false, () -> {
                p.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "ignoreCurse"), PersistentDataType.STRING, "true");
                DayUtils.onDay(40, null, () -> {
                    Random r = new Random();
                    if (r.nextDouble() <= 0.05) {
                        p.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "ignoreCurse"), PersistentDataType.STRING, "false");
                        return;
                    }
                });
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (20 * 5), 3));
            }, () -> {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        p.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "ignoreCurse"), PersistentDataType.STRING, "false");
                    }
                }.runTaskLater(main.getInstance(), 2);
            });
            detectModelData(p, 3, false, () -> {
                DayUtils.onDay(40, null, () -> {
                    Random r = new Random();
                    if (r.nextDouble() <= 0.03) {
                        for (Player pl : Bukkit.getOnlinePlayers()) {
                            pl.sendMessage(Utils.getPrefix() + Utils.c("&7El totem del jugador: &e&l" + p.getName() + " &7Ha fallado y ha muerto por: &d&l" + Utils.getCustomCause(p.getLastDamageCause())));
                        }
                        e.setCancelled(true);
                        p.setHealth(0.0);
                        return;
                    }
                });
                p.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "ignoreCurse"), PersistentDataType.STRING, "true");
                DayUtils.onDay(40, null, () -> {
                    Random r = new Random();
                    if (r.nextDouble() <= 0.03) {
                        for (Player pl : Bukkit.getOnlinePlayers()) {
                            pl.sendMessage(Utils.getPrefix() + Utils.c("&7El totem del jugador: &e&l" + p.getName() + " &7Ha fallado y ha muerto por: &d&l" + Utils.getCustomCause(p.getLastDamageCause())));
                        }
                        e.setCancelled(true);
                        p.setHealth(0.0);
                        return;
                    }
                    if (r.nextDouble() <= 0.05) {
                        p.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "ignoreCurse"), PersistentDataType.STRING, "false");
                        return;
                    }
                });
            }, () -> {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        p.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "ignoreCurse"), PersistentDataType.STRING, "false");
                    }
                }.runTaskLater(main.getInstance(), 2);
                Utils.fakeExplosion(p.getLocation());
            });
            detectModelData(p, 4, true, () -> {
                DayUtils.onDay(40, null, () -> {
                    Random r = new Random();
                    if (r.nextDouble() <= 0.03) {
                        e.setCancelled(true);
                        p.setHealth(0.0);
                        for (Player pl : Bukkit.getOnlinePlayers()) {
                            pl.sendMessage(Utils.getPrefix() + Utils.c("&7El totem del jugador: &e&l" + p.getName() + " &7Ha fallado y ha muerto por: &d&l" + Utils.getCustomCause(p.getLastDamageCause())));
                        }
                        return;
                    }
                });
                p.getInventory().setItemInOffHand(new ItemStack(Material.TOTEM_OF_UNDYING, 1));
            }, () -> {
            });
            detectModelData(p, 5, true, () -> {
                DayUtils.onDay(40, null, () -> {
                    Random r = new Random();
                    if (r.nextDouble() <= 0.03) {
                        e.setCancelled(true);
                        for (Player pl : Bukkit.getOnlinePlayers()) {
                            pl.sendMessage(Utils.getPrefix() + Utils.c("&7El totem del jugador: &e&l" + p.getName() + " &7Ha fallado y ha muerto por: &d&l" + Utils.getCustomCause(p.getLastDamageCause())));
                        }
                        p.setHealth(0.0);
                        return;
                    }
                });
                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, (20 * 5), 2));
                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, (20 * 3), 9));
            }, () -> {
            });
            int probability = Utils.genRandom(0, 100);
            ItemStack totemItemMainHand = p.getInventory().getItemInMainHand();
            ItemStack totemItemOffHand = p.getInventory().getItemInOffHand();

            for (Player pl : Bukkit.getOnlinePlayers()) {
                if (totemItemMainHand.getType() == Material.TOTEM_OF_UNDYING || totemItemOffHand.getType() == Material.TOTEM_OF_UNDYING) {
                    String itemName = "&6Totem of undying";
                    ;
                    if (totemItemMainHand.getType() == Material.TOTEM_OF_UNDYING && !totemItemMainHand.hasItemMeta() || totemItemOffHand.getType() == Material.TOTEM_OF_UNDYING && !totemItemOffHand.hasItemMeta()) {
                        itemName = "&6Totem of undying";
                    } else if (totemItemMainHand.getType() == Material.TOTEM_OF_UNDYING && totemItemMainHand.hasItemMeta() || totemItemOffHand.getType() == Material.TOTEM_OF_UNDYING && totemItemOffHand.hasItemMeta()) {
                        itemName = totemItemMainHand.getType() == Material.TOTEM_OF_UNDYING ? totemItemMainHand.getItemMeta().getDisplayName() : totemItemOffHand.getItemMeta().getDisplayName();
                    }
                    String cause = Utils.getCustomCause(p.getLastDamageCause());

                    pl.sendMessage(Utils.getPrefix() + Utils.c("&7El jugador: &e&l" + p.getName() + " &7Ha activado un: " + itemName + " &7Causa: &d&l" + cause));
                }
            }
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 2);
            if (Bukkit.getWorld("world").getPersistentDataContainer().get(new NamespacedKey(main.getInstance(), "mechTotem"), PersistentDataType.STRING).equalsIgnoreCase("true")) {
                int prob = Utils.genRandom(0, 100);
                Random failTotem = new Random();

                DayUtils.onDay(5, 24, () -> {
                    if (prob <= 5) { // Si la probabilidad es <= 5%
                        if (!hasCustomModelData(p)) {
                            new BukkitRunnable() {

                                @Override
                                public void run() {
                                    boolean foundTotem = false;

                                    for (ItemStack itemStack : p.getInventory().getContents()) {
                                        if (itemStack != null && itemStack.getType() == Material.TOTEM_OF_UNDYING) {
                                            foundTotem = true;
                                            p.getInventory().removeItem(itemStack);
                                            new BukkitRunnable() {
                                                @Override
                                                public void run() {
                                                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                                                }
                                            }.runTaskLater(main.getInstance(), 15);
                                            break;
                                        }
                                    }

                                    if (!foundTotem) {
                                        for (Player pl : Bukkit.getOnlinePlayers()) {
                                            pl.sendMessage(Utils.getPrefix() + Utils.c("&7El jugador: &e&l" + p.getName() + " &7No tenia suficientes totems y ha muerto por: &d&l" + Utils.getCustomCause(p.getLastDamageCause())));
                                        }
                                        p.setHealth(0.0);
                                    }
                                }
                            }.runTaskLater(main.getInstance(), 5L);
                            return;
                        }
                    }
                });
                DayUtils.onDay(25, 39, () -> {
                    if (!hasCustomModelData(p)) {
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                p.setCooldown(Material.TOTEM_OF_UNDYING, 60);
                            }
                        }.runTaskLater(main.getInstance(), 2L);
                        if (failTotem.nextDouble() <= 0.03) {
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                pl.sendMessage(Utils.getPrefix() + Utils.c("&7El totem del jugador: &e&l" + p.getName() + " &7Ha fallado y ha muerto por: &d&l" + Utils.getCustomCause(p.getLastDamageCause())));
                            }
                            e.setCancelled(true);
                            p.setHealth(0.0);
                            return;
                        }
                        if (prob <= 50) { // Si la probabilidad es <= 5%
                            if (!hasCustomModelData(p)) { // Si no tiene CustomModelData
                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        boolean foundTotem = false;

                                        for (ItemStack itemStack : p.getInventory().getContents()) {
                                            if (itemStack != null && itemStack.getType() == Material.TOTEM_OF_UNDYING) {
                                                foundTotem = true;
                                                p.getInventory().removeItem(itemStack);
                                                new BukkitRunnable() {
                                                    @Override
                                                    public void run() {
                                                        p.playEffect(EntityEffect.TOTEM_RESURRECT);
                                                    }
                                                }.runTaskLater(main.getInstance(), 15);
                                                break;
                                            }
                                        }

                                        if (!foundTotem) {
                                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                                pl.sendMessage(Utils.getPrefix() + Utils.c("&7El jugador: &e&l" + p.getName() + " &7No tenia suficientes totems y ha muerto por: &d&l" + Utils.getCustomCause(p.getLastDamageCause())));
                                            }
                                            p.setHealth(0.0);
                                        }
                                    }
                                }.runTaskLater(main.getInstance(), 3);
                                ;
                            }
                        }
                    }
                });
                DayUtils.onDay(40, null, () -> {
                    if (!hasCustomModelData(p)) {
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                p.setCooldown(Material.TOTEM_OF_UNDYING, 60);
                            }
                        }.runTaskLater(main.getInstance(), 2L);
                        if (failTotem.nextDouble() <= 0.2) {
                            e.setCancelled(true);
                            p.setHealth(0.0);
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                pl.sendMessage(Utils.getPrefix() + Utils.c("&7El totem del jugador: &e&l" + p.getName() + " &7Ha fallado y ha muerto por: &d&l" + Utils.getCustomCause(p.getLastDamageCause())));
                            }
                            return;
                        }
                        if (prob <= 50) { // Si la probabilidad es <= 5%
                            if (!hasCustomModelData(p)) { // Si no tiene CustomModelData
                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        boolean foundTotem = false;

                                        for (ItemStack itemStack : p.getInventory().getContents()) {
                                            if (itemStack != null && itemStack.getType() == Material.TOTEM_OF_UNDYING) {
                                                foundTotem = true;
                                                p.getInventory().removeItem(itemStack);
                                                new BukkitRunnable() {
                                                    @Override
                                                    public void run() {
                                                        p.playEffect(EntityEffect.TOTEM_RESURRECT);
                                                    }
                                                }.runTaskLater(main.getInstance(), 15);
                                                break;
                                            }
                                        }

                                        if (!foundTotem) {
                                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                                pl.sendMessage(Utils.getPrefix() + Utils.c("&7El jugador: &e&l" + p.getName() + " &7No tenia suficientes totems y ha muerto por: &d&l" + Utils.getCustomCause(p.getLastDamageCause())));
                                            }
                                            p.setHealth(0.0);
                                        }
                                    }
                                }.runTaskLater(main.getInstance(), 3);
                            }
                        }
                    }
                });
                if (!p.getPersistentDataContainer().get(new NamespacedKey(main.getInstance(), "ignoreCurse"), PersistentDataType.STRING).equalsIgnoreCase("true")) {
                    AtomicInteger probabilidadEpic = new AtomicInteger(45);
                    DayUtils.onDay(25, 39, () -> {
                        probabilidadEpic.set(70);
                    });
                    DayUtils.onDay(40, null, () -> {
                        probabilidadEpic.set(100);
                    });
                    if (probability <= probabilidadEpic.get()) {
                        int curse = Utils.genRandom(1, 5);
                        switch (curse) {
                            case 1 -> {
                                for (Player pl : Bukkit.getOnlinePlayers()) {
                                    Color color1 = new Color(204, 241, 204);
                                    Color color2 = new Color(192, 181, 128, 255);
                                    pl.sendMessage(Utils.getPrefix() + Utils.c("&eUna maldicion ha sido provocada en el jugador: &c&l" + p.getName() + "\n" +
                                            Utils.ib(color1, color2, "&l[Maldicion del vacio]")));
                                    pl.sendMessage("");
                                    pl.sendMessage(Utils.c("&4- &cCooldown ender pearl 3s"));
                                    pl.sendMessage(Utils.c("&4- &cDarkness 1 minuto"));
                                    pl.sendMessage(Utils.c("&4- &cNo resistencia al fuego"));
                                }
                                p.setCooldown(Material.ENDER_PEARL, 60);
                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        p.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, (20 * 60), 0));
                                        p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                                    }
                                }.runTaskLater(main.getInstance(), 5);
                            }
                            case 2 -> {
                                for (Player pl : Bukkit.getOnlinePlayers()) {
                                    Color color1 = new Color(204, 241, 204);
                                    Color color2 = new Color(192, 181, 128, 255);
                                    pl.sendMessage(Utils.getPrefix() + Utils.c("&eUna maldicion ha sido provocada en el jugador: &c&l" + p.getName() + "\n" +
                                            Utils.ib(color1, color2, "&l[Maldicion De La Contencion]")));
                                    pl.sendMessage("");
                                    pl.sendMessage(Utils.c("&4- &cHambre 5 por 30s"));
                                    pl.sendMessage(Utils.c("&4- &cFatiga minera 3 por 30s"));
                                    pl.sendMessage(Utils.c("&4- &cNo resistencia al fuego"));
                                    pl.sendMessage(Utils.c("&4- &cNo abasorcion"));
                                    pl.sendMessage(Utils.c("&4- &cNo regeneracion"));
                                }
                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, (20 * 30), 4));
                                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (20 * 30), 2));
                                        p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                                        p.removePotionEffect(PotionEffectType.ABSORPTION);
                                        p.removePotionEffect(PotionEffectType.REGENERATION);
                                    }
                                }.runTaskLater(main.getInstance(), 5);

                            }
                            case 3 -> {
                                for (Player pl : Bukkit.getOnlinePlayers()) {
                                    Color color1 = new Color(204, 241, 204);
                                    Color color2 = new Color(192, 181, 128, 255);
                                    pl.sendMessage(Utils.getPrefix() + Utils.c("&eUna maldicion ha sido provocada en el jugador: &c&l" + p.getName() + "\n" +
                                            Utils.ib(color1, color2, "&l[Maldicion De la Vida]")));
                                    pl.sendMessage("");
                                    pl.sendMessage(Utils.c("&4- &cUsar doble totem"));
                                    pl.sendMessage(Utils.c("&4- &cNo absorcion"));
                                    pl.sendMessage(Utils.c("&4- &cNo regeneracion"));
                                }
                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        boolean foundTotem = false;

                                        for (ItemStack itemStack : p.getInventory().getContents()) {
                                            if (itemStack != null && itemStack.getType() == Material.TOTEM_OF_UNDYING) {
                                                foundTotem = true;
                                                p.getInventory().removeItem(itemStack);
                                                new BukkitRunnable() {
                                                    @Override
                                                    public void run() {
                                                        p.playEffect(EntityEffect.TOTEM_RESURRECT);
                                                    }
                                                }.runTaskLater(main.getInstance(), 15);
                                                break;
                                            }
                                        }

                                        if (!foundTotem) {
                                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                                pl.sendMessage(Utils.getPrefix() + Utils.c("&7El jugador: &e&l" + p.getName() + " &7No tenia suficientes totems y ha muerto por: &d&l" + Utils.getCustomCause(p.getLastDamageCause())));
                                            }
                                            p.setHealth(0.0);
                                        }
                                        p.removePotionEffect(PotionEffectType.ABSORPTION);
                                        p.removePotionEffect(PotionEffectType.REGENERATION);
                                    }
                                }.runTaskLater(main.getInstance(), 5);

                            }
                            case 4 -> {
                                for (Player pl : Bukkit.getOnlinePlayers()) {
                                    Color color1 = new Color(204, 241, 204);
                                    Color color2 = new Color(192, 181, 128, 255);
                                    pl.sendMessage(Utils.getPrefix() + Utils.c("&eUna maldicion ha sido provocada en el jugador: &c&l" + p.getName() + "\n" +
                                            Utils.ib(color1, color2, "&l[Maldicion Del Caos]")));
                                    pl.sendMessage("");
                                    pl.sendMessage(Utils.c("&4- &cCooldown escudo 5s"));
                                    pl.sendMessage(Utils.c("&4- &cWither 5 por 20s"));
                                    pl.sendMessage(Utils.c("&4- &cDebilidad 10 por 20s"));
                                    pl.sendMessage(Utils.c("&4- &cVeneno 3 por 20s"));
                                }
                                p.setCooldown(Material.SHIELD, 100);
                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, (20 * 20), 4));
                                        p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (20 * 20), 9));
                                        p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (20 * 20), 2));
                                    }
                                }.runTaskLater(main.getInstance(), 5);

                            }
                            case 5 -> {
                                for (Player pl : Bukkit.getOnlinePlayers()) {
                                    Color color1 = new Color(204, 241, 204);
                                    Color color2 = new Color(192, 181, 128, 255);
                                    pl.sendMessage(Utils.getPrefix() + Utils.c("&eUna maldicion ha sido provocada en el jugador: &c&l" + p.getName() + "\n" +
                                            Utils.ib(color1, color2, "&l[Maldicion Del Angel]")));
                                    pl.sendMessage("");
                                    pl.sendMessage(Utils.c("&4- &cCooldown totem por 3s"));
                                    pl.sendMessage(Utils.c("&4- &cNo poder picar por 3s"));
                                    pl.sendMessage(Utils.c("&4- &cFatiga minera 5 por 20s"));
                                }
                                p.setCooldown(Material.TOTEM_OF_UNDYING, 60);
                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (20 * 3), 19));
                                    }
                                }.runTaskLater(main.getInstance(), 5);
                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (20 * 20), 4));
                                    }
                                }.runTaskLater(main.getInstance(), 65);

                            }
                        }
                    }
                }
            }
        }
    }
    public static void detectModelData(Player p, int cmd, boolean ignoreCurse, Runnable run, Runnable before) {
        ItemStack totemItem = null;

        if (p.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING) {
            totemItem = p.getInventory().getItemInMainHand();
        }
        else if (p.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING) {
            totemItem = p.getInventory().getItemInOffHand();
        }

        if (totemItem.hasItemMeta()) {
            if (totemItem.getItemMeta().hasCustomModelData()) {
                if (totemItem.getItemMeta().getCustomModelData() == cmd) {
                    if (ignoreCurse == true) {
                        p.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "ignoreCurse"), PersistentDataType.STRING, "true");
                    }
                    before.run();
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (ignoreCurse == true) {
                                p.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "ignoreCurse"), PersistentDataType.STRING, "false");
                            }
                            run.run();
                        }
                    }.runTaskLater(main.getInstance(), 2);
                }
            }
        }
    }
    public static boolean hasCustomModelData(Player p) {
        ItemStack totemItem = null;

        if (p.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING) {
            totemItem = p.getInventory().getItemInMainHand();
        } else if (p.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING) {
            totemItem = p.getInventory().getItemInOffHand();
        }

        if (totemItem != null && totemItem.hasItemMeta()) {
            return totemItem.getItemMeta().hasCustomModelData();
        }
        return false;
    }
}
