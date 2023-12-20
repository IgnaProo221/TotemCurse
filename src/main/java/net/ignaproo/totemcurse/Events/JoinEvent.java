package net.ignaproo.totemcurse.Events;

import net.ignaproo.totemcurse.Controller.RadioactiveStorm;
import net.ignaproo.totemcurse.Utils.ConfigData;
import net.ignaproo.totemcurse.main;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;

public class JoinEvent implements Listener {
    @EventHandler
    private void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.getPersistentDataContainer().get(new NamespacedKey(main.getInstance(), "ignoreCurse"), PersistentDataType.STRING) == null) {
            p.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "ignoreCurse"), PersistentDataType.STRING, "false");
        }
        if (!p.hasPlayedBefore()) {
            p.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "ignoreCurse"), PersistentDataType.STRING, "false");
        }
        p.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "ignoreCurse"), PersistentDataType.STRING, "false");
        if (ConfigData.getConfigValue("Storm.active", "false").equalsIgnoreCase("true")) {
            String tier = ConfigData.getConfigValue("Storm.tier", "Tier1");
            RadioactiveStorm.startStorm(tier, p, Integer.parseInt(ConfigData.getConfigValue("Storm.duration", "0")), Integer.parseInt(ConfigData.getConfigValue("Storm.originDuration", "0")));
        }
    }
}