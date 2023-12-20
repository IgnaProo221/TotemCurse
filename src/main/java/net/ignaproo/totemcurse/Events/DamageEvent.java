package net.ignaproo.totemcurse.Events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class DamageEvent implements Listener {

    List<EntityType> type = new ArrayList<>();
    @EventHandler
    public void onExplosion(EntityDamageEvent event) {
        type.add(EntityType.PLAYER);
        type.add(EntityType.WOLF);
        type.add(EntityType.PIG);
        type.add(EntityType.COW);
        type.add(EntityType.HORSE);
        type.add(EntityType.MULE);
        type.add(EntityType.DONKEY);
        type.add(EntityType.SHEEP);
        type.add(EntityType.BAT);
        type.add(EntityType.DOLPHIN);
        type.add(EntityType.AXOLOTL);

        type.add(EntityType.BEE);
        type.add(EntityType.CAT);
        type.add(EntityType.OCELOT);
        type.add(EntityType.CHICKEN);
        type.add(EntityType.COD);
        type.add(EntityType.SALMON);
        type.add(EntityType.FOX);
        type.add(EntityType.FROG);

        type.add(EntityType.GLOW_SQUID);
        type.add(EntityType.DROPPED_ITEM);
        type.add(EntityType.GLOW_ITEM_FRAME);
        type.add(EntityType.ITEM_FRAME);
        type.add(EntityType.GOAT);
        type.add(EntityType.LLAMA);
        type.add(EntityType.WANDERING_TRADER);
        type.add(EntityType.VILLAGER);

        type.add(EntityType.MUSHROOM_COW);
        type.add(EntityType.PANDA);
        type.add(EntityType.PARROT);
        type.add(EntityType.POLAR_BEAR);
        type.add(EntityType.PUFFERFISH);
        type.add(EntityType.RABBIT);
        type.add(EntityType.SKELETON_HORSE);
        type.add(EntityType.SQUID);

        type.add(EntityType.TRADER_LLAMA);
        type.add(EntityType.STRIDER);
        type.add(EntityType.TADPOLE);
        type.add(EntityType.TROPICAL_FISH);
        type.add(EntityType.TURTLE);
        type.add(EntityType.ALLAY);

        type.add(EntityType.ARMOR_STAND);
        type.add(EntityType.BOAT);
        type.add(EntityType.MINECART_CHEST);
        type.add(EntityType.MINECART_MOB_SPAWNER);
        type.add(EntityType.MINECART);
        type.add(EntityType.MINECART_FURNACE);
        type.add(EntityType.MINECART_COMMAND);
        type.add(EntityType.MINECART_HOPPER);
        type.add(EntityType.MINECART_TNT);

        type.add(EntityType.FISHING_HOOK);
        if (!type.contains(event.getEntityType())) {
            if (event.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) || event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
                event.setCancelled(true);
                event.getEntity().setVelocity(new Vector(0, 0, 0));
            }
        }
    }
}
