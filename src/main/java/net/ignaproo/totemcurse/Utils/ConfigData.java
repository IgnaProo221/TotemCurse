package net.ignaproo.totemcurse.Utils;

import net.ignaproo.totemcurse.Data.CreateFile;
import net.ignaproo.totemcurse.main;

import java.util.Date;

public class ConfigData {

    private static final CreateFile config = new CreateFile(main.getInstance(), "config.yml");

    public static void setConfigValue(String id, String value) {
        config.setConfig(id, value);
    }

    public static void reloadConfig() {
        config.reloadConfig();
    }

    public static String getConfigValue(String id, String defaultValue) {
        if (config.getConfig().getString(id) == null) {
            config.setConfig(id, defaultValue);
        }
        return config.getConfig().getString(id);
    }
    public static String getServerDate() {
        return getConfigValue("date", Utils.dateFormat(new Date()));
    }
    public static int getDay(){
        return Integer.parseInt(getConfigValue("day", "0"));
    }

}
