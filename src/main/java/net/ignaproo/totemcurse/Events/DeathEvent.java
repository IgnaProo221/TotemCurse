package net.ignaproo.totemcurse.Events;

import com.github.shynixn.structureblocklib.api.bukkit.StructureBlockLibApi;
import net.ignaproo.totemcurse.Utils.ConfigData;
import net.ignaproo.totemcurse.main;
import org.bukkit.*;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import java.util.logging.Level;

public class DeathEvent implements Listener {
    @EventHandler
    private void onDeath(PlayerDeathEvent e) {
        Player p = e.getPlayer();
        int x = p.getLocation().getBlockX();
        int y = p.getLocation().getBlockY();
        int z = p.getLocation().getBlockZ();
        if (ConfigData.getConfigValue("death_structure", "false").equalsIgnoreCase("true")) {
            if (p.getWorld().equals(Bukkit.getWorld("world"))) {
                generateTree(p.getLocation(), ConfigData.getConfigValue("structure_overworld", "muerte"), p);
                p.sendMessage("X: " + x + " Y:" + y + " Z:" + z);
            } else if (p.getWorld().equals(Bukkit.getWorld("world_nether"))) {
                generateTree(p.getLocation(), ConfigData.getConfigValue("structure_nether", "muertenether"), p);
            } else if (p.getWorld().equals(Bukkit.getWorld("world_the_end"))) {
                generateTree(p.getLocation(), ConfigData.getConfigValue("structure_end", "muerteend"), p);
            }
        }
    }
    private static void loadStructure(Location location, String structureName, Player p) {
        StructureBlockLibApi.INSTANCE
                .loadStructure(main.getInstance())
                .at(location)
                .loadFromWorld("world", "minecraft", structureName)
                .onException(e -> main.getInstance().getLogger().log(Level.SEVERE, "Failed to load structure.", e))
                .onResult(e -> main.getInstance().getLogger().log(Level.INFO, ChatColor.GREEN + "Loaded structure 'mystructure'."));
    }

    public static void generateTree(Location playerDeathLoc, String customStructure, Player p) {
        int structureBlockX = playerDeathLoc.getBlockX() - 4;
        int structureBlockY = playerDeathLoc.getBlockY()  - 1;
        int structureBlockZ = playerDeathLoc.getBlockZ()  - 3;

        Location structureBlockLocation1 = playerDeathLoc.clone().add(structureBlockX, structureBlockY, structureBlockZ);

        loadStructure(structureBlockLocation1, customStructure, p);
    }
}
