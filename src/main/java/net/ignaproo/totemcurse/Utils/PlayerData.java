package net.ignaproo.totemcurse.Utils;

import net.ignaproo.totemcurse.Data.CreateFile;
import net.ignaproo.totemcurse.main;
import org.bukkit.entity.Player;

public class PlayerData {
    private static final CreateFile data = new CreateFile(main.getInstance(), "data.yml");

    public static void setPlayerData(Player target, String id, String value){
        data.setConfig(target.getName() + "." + id, value);
    }

    public static void reloadPlayerData() {
        data.reloadConfig();
    }

    public static String getPlayerData(Player target, String id, String defaultValue){
        if(data.getConfig().getString(target.getName() + "." + id) == null){
            setPlayerData(target, id, defaultValue);
        }
        return data.getConfig().getString(target.getName() + "." + id);
    }
}
