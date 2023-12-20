package net.ignaproo.totemcurse.Days;

import net.ignaproo.totemcurse.Utils.DayUtils;
import net.ignaproo.totemcurse.main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class Day17 implements Listener {
    @EventHandler
    private void onEat(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        DayUtils.onDay(17, null, () -> {

            if (item != null && item.getType() == Material.APPLE && item.hasItemMeta()) {
                ItemMeta itemMeta = item.getItemMeta();
                if (itemMeta != null && itemMeta.hasCustomModelData() && itemMeta.getCustomModelData() == 1) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 20, 2));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 3, 4));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 10, 2));
                }

            }
        });
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            DayUtils.onDay(17, null, () -> {
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        player.setFoodLevel(player.getFoodLevel() - 1);
                    }
                }.runTaskLater(main.getInstance(), 20);
            });
        }
    }
}
