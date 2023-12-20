package net.ignaproo.totemcurse.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.ignaproo.totemcurse.Controller.RadioactiveStorm;
import net.ignaproo.totemcurse.Days.*;
import net.ignaproo.totemcurse.Items.Items;
import net.ignaproo.totemcurse.Utils.ConfigData;
import net.ignaproo.totemcurse.main;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitTask;

import static net.ignaproo.totemcurse.Utils.Utils.c;
import static net.ignaproo.totemcurse.Utils.Utils.chatColor;

@CommandAlias("container|totem")
@CommandPermission("container.admin") // Si se remueve todos los jugadores sin OP pueden usar el comando
public class Commands extends BaseCommand {
    BukkitTask stormTask;
    private BossBar radioactiveStormTimer;
    @Subcommand("mechTotem")
    @CommandCompletion("true|false")
    @Syntax("<true|false>")
    public void setTotemMech(Player player, String[] args) {
        if (args.length >= 1) {
            Bukkit.getWorld("world").getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "mechTotem"), PersistentDataType.STRING, args[0]);
            player.sendMessage("Mecanica de totem cambiada a: " + args[0]);
        }else {

        }
    }


    @Subcommand("item")
    @CommandCompletion("emperortotem|depthtotem|kaboomtotem|refusedtotem|rebirthtotem|emergencyaxe|amulet|ignitedpie|upgnetheritehelmet|upgnetheritechestplate|upgnetheriteleggings|upgnetheriteboots|deepsword|deeppickaxe|deepaxe|deepshovel|deephoe|grapplinghook|speedier|skullshield|syringe|emptysyringe|depthapple" +
            "|netheritecompactedcarrot|mysticalhelmet|mysticalchestplate|mysticalleggings|mysticalboots" +
            "|orb|mysticalsword|mysticalpickaxe|mysticalaxe|mysticalshovel|mysticalhoe|oldancestralorb @nothing")
    public void item(Player sender, String[] args) {
        if (args.length <= 1) {
            sender.sendMessage(c("&4Error &c(Recuerda ingresar una cantidad y obviamente no negativa ni nula[0]"));
            return;
        }
        if (args.length > 0) {

            if ( Integer.parseInt(args[1]) > 50 || Integer.parseInt(args[1] )< 0){
                sender.sendMessage(c("&4&lError(Recuerda ingresar una cantidad y esta debe ser menor a 50 y obviamente no negativa ni nula[0]"));
            }else {
                try {
                    for (int i = 1; i <= Integer.parseInt(args[1]); i++) {
                        giveitem(args,sender);
                    }
                }catch(NumberFormatException e){
                    sender.sendMessage(c("&4&lError tienes que ingresar una cantidad en numeros enteros!"));
                }
            }
        }else {

            giveitem(args,sender);


        }
    }
    public void giveitem(String args[],Player sender){
        if(args.length == 0) {
            sender.sendMessage(c(" &eUso correcto del comando: &f/totem item <item> <cantidad>"));
            sender.playSound(sender.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.2F);
            return;
        }else if(args.length == 1) {
            sender.sendMessage(c(" &eUso correcto del comando: &f/totem item <item> <cantidad>"));
            sender.playSound(sender.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.2F);
            return;
        }

        Item item = (Item) sender.getWorld().spawnEntity(sender.getLocation(), EntityType.DROPPED_ITEM,false);
        switch (args[0].toLowerCase()) {
            case "emperortotem" -> {
                item.setItemStack(Items.EmperorTotem());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.EmperorTotem().getItemMeta().getDisplayName()));
            }
            case "depthtotem" -> {
                item.setItemStack(Items.DepthTotem());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.DepthTotem().getItemMeta().getDisplayName()));
            }
            case "kaboomtotem" -> {
                item.setItemStack(Items.KaboomTotem());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.KaboomTotem().getItemMeta().getDisplayName()));
            }
            case "refusedtotem" -> {
                item.setItemStack(Items.RefusedTotem());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.RefusedTotem().getItemMeta().getDisplayName()));
            }
            case "rebirthtotem" -> {
                item.setItemStack(Items.RebirthTotem());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.RebirthTotem().getItemMeta().getDisplayName()));
            }
            case "emergencyaxe" -> {
                item.setItemStack(Items.EmergencyAxe());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.EmergencyAxe().getItemMeta().getDisplayName()));
            }
            case "amulet" -> {
                item.setItemStack(Items.Amulet());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.Amulet().getItemMeta().getDisplayName()));
            }
            case "ignitedpie" -> {
                item.setItemStack(Items.IgnitedPies());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.IgnitedPies().getItemMeta().getDisplayName()));
            }

            case "upgnetheritehelmet" -> {
                item.setItemStack(Items.UpgradedNetheriteHelmet());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.UpgradedNetheriteHelmet().getItemMeta().getDisplayName()));
            }
            case "upgnetheritechestplate" -> {
                item.setItemStack(Items.UpgradedNetheriteChestplate());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.UpgradedNetheriteChestplate().getItemMeta().getDisplayName()));
            }
            case "upgnetheriteleggings" -> {
                item.setItemStack(Items.UpgradedNetheriteLeggings());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.UpgradedNetheriteLeggings().getItemMeta().getDisplayName()));
            }
            case "upgnetheriteboots" -> {
                item.setItemStack(Items.UpgradedNetheriteBoots());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.UpgradedNetheriteBoots().getItemMeta().getDisplayName()));
            }
            case "deepsword" -> {
                item.setItemStack(Items.DeepSword());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.DeepSword().getItemMeta().getDisplayName()));
            }
            case "deeppickaxe" -> {
                item.setItemStack(Items.DeepPickaxe());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.DeepPickaxe().getItemMeta().getDisplayName()));
            }
            case "deepaxe" -> {
                item.setItemStack(Items.DeepAxe());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.DeepAxe().getItemMeta().getDisplayName()));
            }
            case "deepshovel" -> {
                item.setItemStack(Items.DeepShovel());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.DeepShovel().getItemMeta().getDisplayName()));
            }
            case "deephoe" -> {
                item.setItemStack(Items.DeepHoe());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.DeepHoe().getItemMeta().getDisplayName()));
            }
            case "grapplinghook" -> {
                item.setItemStack(Items.GrapplingHook());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.GrapplingHook().getItemMeta().getDisplayName()));
            }
            case "speedier" -> {
                item.setItemStack(Items.Speedier());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.Speedier().getItemMeta().getDisplayName()));
            }
            case "skullshield" -> {
                item.setItemStack(Items.SkullShield());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.SkullShield().getItemMeta().getDisplayName()));
            }
            case "syringe" -> {
                item.setItemStack(Items.Syringe());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.Syringe().getItemMeta().getDisplayName()));
            }
            case "emptysyringe" -> {
                item.setItemStack(Items.EmptySyringe());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.EmptySyringe().getItemMeta().getDisplayName()));
            }
            case "depthapple" -> {
                item.setItemStack(Items.DepthApple());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.DepthApple().getItemMeta().getDisplayName()));
            }


            case "netheritecompactedcarrot" -> {
                item.setItemStack(Items.NetheriteCompactedCarrot());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.NetheriteCompactedCarrot().getItemMeta().getDisplayName()));
            }
            case "mysticalhelmet" -> {
                item.setItemStack(Items.MysticalHelmet());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.MysticalHelmet().getItemMeta().getDisplayName()));
            }
            case "mysticalchestplate" -> {
                item.setItemStack(Items.MysticalChestplate());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.MysticalChestplate().getItemMeta().getDisplayName()));
            }
            case "mysticalleggings" -> {
                item.setItemStack(Items.MysticalLeggings());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.MysticalLeggings().getItemMeta().getDisplayName()));
            }
            case "mysticalboots" -> {
                item.setItemStack(Items.MysticalBoots());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.MysticalBoots().getItemMeta().getDisplayName()));
            }
            case "orb" -> {
                item.setItemStack(Items.Orb());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.Orb().getItemMeta().getDisplayName()));
            }
            case "mysticalsword" -> {
                item.setItemStack(Items.MysticalSword());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.MysticalSword().getItemMeta().getDisplayName()));
            }
            case "mysticalpickaxe" -> {
                item.setItemStack(Items.MysticalPickaxe());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.MysticalPickaxe().getItemMeta().getDisplayName()));
            }
            case "mysticalaxe" -> {
                item.setItemStack(Items.MysticalAxe());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.MysticalAxe().getItemMeta().getDisplayName()));
            }
            case "mysticalshovel" -> {
                item.setItemStack(Items.MysticalShovel());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.MysticalShovel().getItemMeta().getDisplayName()));
            }
            case "mysticalhoe" -> {
                item.setItemStack(Items.MysticalHoe());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.MysticalHoe().getItemMeta().getDisplayName()));
            }
            case "oldancestralorb" -> {
                item.setItemStack(Items.OldAncestralOrb());
                sender.sendMessage(c("&eItem dado: &c&l" + Items.OldAncestralOrb().getItemMeta().getDisplayName()));
            }
        }
    }
    @Subcommand("totemMultiply")
    public void totemMultiply(Player sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(c(" &eUso correcto del comando: &f/container totemMultiply <cantidad>"));
            sender.playSound(sender.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.2F);
            return;
        }

        try {
            int cantidad = Integer.parseInt(args[0]);
            World world = Bukkit.getWorld("world");
            NamespacedKey key = new NamespacedKey(main.getInstance(), "MultiplyTotem");
            PersistentDataContainer dataContainer = world.getPersistentDataContainer();
            dataContainer.set(key, PersistentDataType.INTEGER, cantidad);
            sender.sendMessage(c("&aValor establecido correctamente en el almacenamiento persistente."));
        } catch (NumberFormatException e) {
            sender.sendMessage(c("&cEl segundo argumento debe ser un número válido."));
        }
    }
    @Subcommand("startStorm")
    @CommandCompletion("Tier1|Tier2|Tier3")
    public void startStorm(Player sender, String[] args) {
        if (args.length >= 2) {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                RadioactiveStorm.startStorm(args[0], pl, Integer.parseInt(args[1]),Integer.parseInt(args[1]));
            }
        }
    }
    @Subcommand("day")
    @CommandCompletion("day <día> @nothing")
    @Syntax("<nuevoDía>")
    public void setDay(Player player, int day) {
        ConfigData.setConfigValue("day", String.valueOf(day));
        player.sendMessage(chatColor("&aSe establecio correctamente el día &c" + ConfigData.getDay() + "&a."));
        for (Player pl : Bukkit.getOnlinePlayers()) {
            Day3.checkDayThree(pl);
            Day15.checkDayFifteen(pl);
            Day15.updatePlayerData();
            Day30.underWaterFall(pl);
            Day35.updatePlayerData();
            Day40.startTNTCounter(pl);
            Day40.updatePlayerData();
            pl.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "ignoreCurse"), PersistentDataType.STRING, "false");
        }
    }
}
