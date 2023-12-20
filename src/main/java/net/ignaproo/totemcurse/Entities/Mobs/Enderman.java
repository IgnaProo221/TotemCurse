package net.ignaproo.totemcurse.Entities.Mobs;

import net.ignaproo.totemcurse.Entities.MobTypeList;
import net.ignaproo.totemcurse.Utils.Utils;
import net.ignaproo.totemcurse.main;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Enderman {
    public static void EndermanBuilder(org.bukkit.entity.Enderman enderman, String name, int health, double followRange, double speed, double attackDamage, double knockbackResistance, double knockbackAmount, Player p, String customEntityType) {
        enderman.setCustomName(name);
        enderman.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);

        if (followRange != 0) {
            enderman.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);
        }

        makeEndermanAngry(enderman, p);
        enderman.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
        enderman.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(attackDamage);
        enderman.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);
        enderman.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(knockbackAmount);
        enderman.setHealth(enderman.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        enderman.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "MobType"), PersistentDataType.STRING, customEntityType);
    }
    public static void makeEndermanAngry(org.bukkit.entity.Enderman enderman, Player targetPlayer) {
        enderman.setTarget(targetPlayer);
    }
    public static void addTag(LivingEntity entity, String tag, String value) {
        @NotNull PersistentDataContainer nbt = entity.getPersistentDataContainer();
        nbt.set(new NamespacedKey(main.getInstance(), tag), PersistentDataType.STRING, value);
    }

    public static void ChangerEnderman(org.bukkit.entity.Enderman entity, Player p) {
        Color color1 = new Color(145, 113, 53);
        Color color2 = new Color(131, 77, 40, 255);
        Enderman.EndermanBuilder((org.bukkit.entity.Enderman) entity, Utils.ib(color1, color2, "&lChanger Enderman"), 200, 80, 0.12F, 6, 0.05, 0.3, p, MobTypeList.ChangerEnderman());
    }
}
