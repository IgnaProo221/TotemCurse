package net.ignaproo.totemcurse.Utils;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static void console(String msg) {
        System.out.println(chatColor(msg));
    }
    public static String chatColor(String message) {
        return message.replace("&", "§");
    }
    public static String ib(java.awt.Color Colora, java.awt.Color Colorb, String StringToGradient) {
        ChatColor Color2 = ChatColor.of(Colorb);
        ChatColor Color1 = ChatColor.of(Colora);
        int r = Color1.getColor().getRed();
        int g = Color1.getColor().getGreen();
        int b = Color1.getColor().getBlue();
        int r2 = Color2.getColor().getRed();
        int g2 = Color2.getColor().getGreen();
        int b2 = Color2.getColor().getBlue();
        String Hex = String.format("%02X%02X%02X", r, g, b);
        String Hex2 = String.format("%02X%02X%02X", r2, g2, b2);
        String Final = IridiumColorAPI.process("<GRADIENT:" + Hex + ">" + StringToGradient + "</GRADIENT:" + Hex2 + ">");


        return Final;


    }
    public static String GetType(Entity p) {


        if (PDC.EntityPDC(p).get(PDC.Key(KeyList.mobtype()), PDC.STRING) == null) {


            return "vanilla";
        } else {


            return (String) PDC.EntityPDC(p).get(PDC.Key(KeyList.mobtype()), PDC.STRING);
        }


    }
    public static String dateFormat(Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date);
    }
    public static String getPrefix() {
        return PREFIX;
    }
    public static String c(String s) {
        return IridiumColorAPI.process(s);
    }

    public static int genRandom(int numMin, int numMax) {
        return (int) Math.floor(Math.random() * (numMax - numMin + 1) + numMin);
    }

    private static final String PREFIX = c("&8&l[" + ib(new Color(255, 90, 0), new Color(215, 0, 0), "&lContainer Hardcore") + "&8&l]&r ");

    public static void fakeExplosion(Location location) {
        location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);

        location.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, location, 1);

        double radius = 5.0;

        // Itera a través de todas las entidades cercanas
        for (Entity entity : location.getWorld().getNearbyEntities(location, radius, radius, radius)) {
            if (entity instanceof Player || entity instanceof IronGolem) {
            } else if (entity instanceof Wither || entity instanceof EnderDragon || entity instanceof Warden) {
            } else if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).setHealth(0.0);
            }
        }
    }
    public static String getCustomCause(EntityDamageEvent e) {
        String reasonDeath = "Desconocido";
        switch (e.getCause()) {
            case FALL, FALLING_BLOCK -> reasonDeath = "Caída";
            case FIRE, FIRE_TICK -> reasonDeath = "Fuego";
            case LAVA -> reasonDeath = "Lava";
            case DROWNING -> reasonDeath = "Ahogamiento";
            case BLOCK_EXPLOSION -> reasonDeath = "Explosion";
            case VOID -> reasonDeath = "Vacío";
            case LIGHTNING -> reasonDeath = "Rayo";
            case POISON -> reasonDeath = "Veneno";
            case MAGIC -> reasonDeath = "Magia";
            case WITHER -> reasonDeath = "Wither";
            case THORNS -> reasonDeath = "Espinas";
            case DRAGON_BREATH -> reasonDeath = "Aliento de dragón";
            case CONTACT -> reasonDeath = "Contacto";
            case CRAMMING -> reasonDeath = "Entity Cramming";
            case HOT_FLOOR -> reasonDeath = "Piso en llamas";
            case DRYOUT -> reasonDeath = "Secado";
            case STARVATION -> reasonDeath = "Hambre";
            case SUFFOCATION -> reasonDeath = "Asfixia";
            case FLY_INTO_WALL -> reasonDeath = "Colisión";
            case FREEZE -> reasonDeath = "Congelación";
            case ENTITY_ATTACK, ENTITY_SWEEP_ATTACK -> {
                if (e instanceof EntityDamageByEntityEvent) {
                    reasonDeath = "Entidad [" + ((EntityDamageByEntityEvent) e).getDamager().getName() + "&d&l]";
                }
            }
            case PROJECTILE -> {
                if (e instanceof EntityDamageByEntityEvent) {
                    Projectile proj = (Projectile) ((EntityDamageByEntityEvent) e).getDamager();
                    if (proj.getShooter() != null) {
                        Entity shooter = (Entity) proj.getShooter();
                        reasonDeath = "Proyectil [" + shooter.getName() + "&d&l]";
                    } else {
                        reasonDeath = "Proyectil [" + ((EntityDamageByEntityEvent) e).getDamager().getName() + "]";
                    }
                }
            }
            case ENTITY_EXPLOSION -> {
                if (e instanceof EntityDamageByEntityEvent) {
                    if (((EntityDamageByEntityEvent) e).getDamager() instanceof Fireball ball && ((Fireball) ((EntityDamageByEntityEvent) e).getDamager()).getShooter() != null) {
                        LivingEntity et = (LivingEntity) ball.getShooter();
                        reasonDeath = "Explosión [" + et.getName() + "&d&l]";
                    } else {
                        reasonDeath = Utils.c("Explosión [" + ((EntityDamageByEntityEvent) e).getDamager().getName() + "&d&l]");
                    }
                }
            }
            case SUICIDE -> reasonDeath = "Suicidio";
            default -> reasonDeath = "Desconocido";
        }
        return reasonDeath;
    }
}
