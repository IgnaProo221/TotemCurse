package net.ignaproo.totemcurse.Entities;

import net.ignaproo.totemcurse.Utils.ConfigData;
import net.ignaproo.totemcurse.Utils.Utils;
import org.bukkit.entity.Entity;

public class MobUtils {
    public static void CustomMobEvent(Entity entity, String mob, int Day, Runnable code) {
        if (Utils.GetType(entity) == mob && ConfigData.getDay() >= Day ) {
            code.run();
        }
    }
}
