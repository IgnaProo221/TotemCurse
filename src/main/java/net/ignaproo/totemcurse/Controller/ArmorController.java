package net.ignaproo.totemcurse.Controller;

import net.ignaproo.totemcurse.Utils.ConfigData;
import net.ignaproo.totemcurse.Utils.DayUtils;
import net.ignaproo.totemcurse.main;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class ArmorController extends BukkitRunnable {

    private static final UUID HEALTH_MODIFIER_UUID = UUID.randomUUID();
    private static final AttributeModifier HEALTH_MODIFIER = new AttributeModifier(
            HEALTH_MODIFIER_UUID, "HealthBonus", 8.0, AttributeModifier.Operation.ADD_NUMBER);

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (ConfigData.getDay() >= 10 && wearingUpgradedArmor(player)) {
                updateHealth(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 0));
            } else {
                removeHealthModifier(player);
            }
        }
    }

    public void startTask() {
        runTaskTimer(main.getInstance(), 0, 20);
    }

    public void updateHealth(Player player) {
        if (wearingUpgradedArmor(player)) {
            applyHealthModifier(player);
        } else {
            removeHealthModifier(player);
        }
    }

    public boolean wearingUpgradedArmor(Player player) {
        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack chestplate = player.getInventory().getChestplate();
        ItemStack leggings = player.getInventory().getLeggings();
        ItemStack boots = player.getInventory().getBoots();

        return isUpgradedArmorPiece(helmet) && isUpgradedArmorPiece(chestplate)
                && isUpgradedArmorPiece(leggings) && isUpgradedArmorPiece(boots);
    }

    public boolean isUpgradedArmorPiece(ItemStack item) {
        return item != null && item.hasItemMeta() &&
                item.getItemMeta().hasCustomModelData() &&
                item.getItemMeta().getCustomModelData() == 1;
    }

    public void applyHealthModifier(Player player) {
        Attribute healthAttribute = Attribute.GENERIC_MAX_HEALTH;
        double currentHealth = player.getAttribute(healthAttribute).getBaseValue();

        if (!hasHealthModifier(player)) {
            player.getAttribute(healthAttribute).addModifier(HEALTH_MODIFIER);
            player.setHealth(currentHealth + 8); // Ajustar la salud actual al modificar el atributo
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

    public void removeHealthModifier(Player player) {
        if (hasHealthModifier(player)) {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).removeModifier(HEALTH_MODIFIER);
            player.setHealth(Math.min(player.getHealth(), player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()));
        }
    }
}
