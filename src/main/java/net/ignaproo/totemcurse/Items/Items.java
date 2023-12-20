package net.ignaproo.totemcurse.Items;

import net.ignaproo.totemcurse.Utils.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.awt.*;

public class Items {
    public static ItemStack EmperorTotem() {
        Color color1 = new Color(190, 123, 13);
        Color color2 = new Color(161, 145, 132);
        ItemStack i = new ItemBuilder(Material.TOTEM_OF_UNDYING)
                .setDisplayName((Utils.ib(color1, color2, "&lEmperor Totem")))
                .setAmount(1)
                .setCustomModelData(1)


                .build();

        return i;
    }

    public static ItemStack DepthTotem() {
        Color color1 = new Color(51, 51, 51);
        Color color2 = new Color(155, 155, 155);
        ItemStack i = new ItemBuilder(Material.TOTEM_OF_UNDYING)
                .setDisplayName((Utils.ib(color1, color2, "&lDepth Totem")))
                .setAmount(1)
                .setCustomModelData(2)

                .build();

        return i;
    }

    public static ItemStack KaboomTotem() {
        Color color1 = new Color(105, 5, 5);
        Color color2 = new Color(154, 93, 0);
        ItemStack i = new ItemBuilder(Material.TOTEM_OF_UNDYING)
                .setDisplayName((Utils.ib(color1, color2, "&lKaboom Totem")))
                .setAmount(1)
                .setCustomModelData(3)

                .build();

        return i;
    }

    public static ItemStack RefusedTotem() {
        Color color1 = new Color(0, 255, 213);
        Color color2 = new Color(2, 51, 171);
        ItemStack i = new ItemBuilder(Material.TOTEM_OF_UNDYING)
                .setDisplayName((Utils.ib(color1, color2, "&lRefused Totem")))
                .setAmount(1)
                .setCustomModelData(4)

                .build();

        return i;
    }

    public static ItemStack RebirthTotem() {
        Color color1 = new Color(147, 234, 104);
        Color color2 = new Color(93, 229, 11);
        ItemStack i = new ItemBuilder(Material.TOTEM_OF_UNDYING)
                .setDisplayName((Utils.ib(color1, color2, "&lRebirth Totem")))
                .setAmount(1)
                .setCustomModelData(5)

                .build();

        return i;
    }

    public static ItemStack EmergencyAxe() {
        Color color1 = new Color(138, 105, 105);
        Color color2 = new Color(84, 57, 57);
        ItemStack i = new ItemBuilder(Material.IRON_AXE)
                .setDisplayName((Utils.ib(color1, color2, "&lEmergency Axe")))
                .setAmount(1)
                .setCustomModelData(1)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack Amulet() {
        Color color1 = new Color(210, 255, 118);
        Color color2 = new Color(56, 192, 130);
        ItemStack i = new ItemBuilder(Material.GREEN_DYE)
                .setDisplayName((Utils.ib(color1, color2, "&lAmulet")))
                .setAmount(1)
                .setCustomModelData(1)

                .build();

        return i;
    }

    public static ItemStack IgnitedPies() {
        Color color1 = new Color(217, 1, 1);
        Color color2 = new Color(231, 91, 0);
        ItemStack i = new ItemBuilder(Material.PUMPKIN_PIE)
                .setDisplayName((Utils.ib(color1, color2, "&lIgnited Pies")))
                .setAmount(1)
                .setCustomModelData(1)

                .build();

        return i;
    }


    public static ItemStack UpgradedNetheriteHelmet() {
        Color color1 = new Color(24, 12, 255);
        Color color2 = new Color(94, 0, 255);
        ItemStack i = new ItemBuilder(Material.NETHERITE_HELMET)
                .setDisplayName((Utils.ib(color1, color2, "&lUpgraded netherite helmet")))
                .setAmount(1)
                .setCustomModelData(1)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack UpgradedNetheriteChestplate() {
        Color color1 = new Color(24, 12, 255);
        Color color2 = new Color(94, 0, 255);
        ItemStack i = new ItemBuilder(Material.NETHERITE_CHESTPLATE)
                .setDisplayName((Utils.ib(color1, color2, "&lUpgraded netherite chestplate")))
                .setAmount(1)
                .setCustomModelData(1)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack UpgradedNetheriteLeggings() {
        Color color1 = new Color(24, 12, 255);
        Color color2 = new Color(94, 0, 255);
        ItemStack i = new ItemBuilder(Material.NETHERITE_LEGGINGS)
                .setDisplayName((Utils.ib(color1, color2, "&lUpgraded netherite leggings")))
                .setAmount(1)
                .setCustomModelData(1)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack UpgradedNetheriteBoots() {
        Color color1 = new Color(24, 12, 255);
        Color color2 = new Color(94, 0, 255);
        ItemStack i = new ItemBuilder(Material.NETHERITE_BOOTS)
                .setDisplayName((Utils.ib(color1, color2, "&lUpgraded netherite boots")))
                .setAmount(1)
                .setCustomModelData(1)
                .setUnbreakable(true)

                .build();

        return i;
    }


    public static ItemStack DeepSword() {
        Color color1 = new Color(117, 103, 103);
        Color color2 = new Color(44, 31, 31);
        ItemStack i = new ItemBuilder(Material.NETHERITE_SWORD)
                .setDisplayName((Utils.ib(color1, color2, "&lDeep Sword")))
                .setAmount(1)
                .addEnchant(Enchantment.DAMAGE_ALL, 6)
                .addEnchant(Enchantment.FIRE_ASPECT, 6)
                .addEnchant(Enchantment.DIG_SPEED, 6)
                .setCustomModelData(1)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack DeepPickaxe() {
        Color color1 = new Color(117, 103, 103);
        Color color2 = new Color(44, 31, 31);
        ItemStack i = new ItemBuilder(Material.NETHERITE_PICKAXE)
                .setDisplayName((Utils.ib(color1, color2, "&lDeep Pickaxe")))
                .setAmount(1)
                .addEnchant(Enchantment.DIG_SPEED, 6)
                .addEnchant(Enchantment.LUCK, 6)
                .setCustomModelData(1)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack DeepAxe() {
        Color color1 = new Color(117, 103, 103);
        Color color2 = new Color(44, 31, 31);
        ItemStack i = new ItemBuilder(Material.NETHERITE_AXE)
                .setDisplayName((Utils.ib(color1, color2, "&lDeep Axe")))
                .setAmount(1)
                .addEnchant(Enchantment.DIG_SPEED, 6)
                .setCustomModelData(1)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack DeepShovel() {
        Color color1 = new Color(117, 103, 103);
        Color color2 = new Color(44, 31, 31);
        ItemStack i = new ItemBuilder(Material.NETHERITE_SHOVEL)
                .setDisplayName((Utils.ib(color1, color2, "&lDeep Shovel")))
                .setAmount(1)
                .addEnchant(Enchantment.DIG_SPEED, 6)
                .setCustomModelData(1)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack DeepHoe() {
        Color color1 = new Color(117, 103, 103);
        Color color2 = new Color(44, 31, 31);
        ItemStack i = new ItemBuilder(Material.NETHERITE_HOE)
                .setDisplayName((Utils.ib(color1, color2, "&lDeep Hoe")))
                .setAmount(1)
                .addEnchant(Enchantment.DIG_SPEED, 5)
                .setCustomModelData(1)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack GrapplingHook() {
        Color color1 = new Color(192, 252, 39);
        Color color2 = new Color(16, 159, 3);
        ItemStack i = new ItemBuilder(Material.FISHING_ROD)
                .setDisplayName((Utils.ib(color1, color2, "&lGrappling Hook")))
                .setAmount(1)
                .setCustomModelData(1)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack Speedier() {
        Color color1 = new Color(124, 246, 255);
        Color color2 = new Color(229, 185, 255);
        ItemStack i = new ItemBuilder(Material.FEATHER)
                .setDisplayName((Utils.ib(color1, color2, "&lSpeedier")))
                .setAmount(1)
                .setCustomModelData(1)

                .build();

        return i;
    }

    public static ItemStack SkullShield() {
        Color color1 = new Color(200, 204, 204);
        Color color2 = new Color(108, 96, 84);
        ItemStack i = new ItemBuilder(Material.SHIELD)
                .setDisplayName((Utils.ib(color1, color2, "&lSkull Shield")))
                .setAmount(1)
                .setCustomModelData(1)
                .setUnbreakable(true)

                .build();

        return i;
    }
    public static ItemStack Syringe() {
        Color color1 = new Color(168, 99, 187);
        Color color2 = new Color(78, 86, 159);
        ItemStack i = new ItemBuilder(Material.STICK)
                .setDisplayName((Utils.ib(color1, color2, "&LSyringe")))
                .setAmount(1)
                .setCustomModelData(1)

                .build();

        return i;
    }
    public static ItemStack EmptySyringe() {
        Color color1 = new Color(168, 99, 187);
        Color color2 = new Color(78, 86, 159);
        ItemStack i = new ItemBuilder(Material.STICK)
                .setDisplayName((Utils.ib(color1, color2, "&lEmpty Syringe")))
                .setAmount(1)
                .setCustomModelData(2)

                .build();

        return i;
    }
    public static ItemStack DepthApple() {
        Color color1 = new Color(38, 38, 38);
        Color color2 = new Color(78, 81, 98);
        ItemStack i = new ItemBuilder(Material.APPLE)
                .setDisplayName((Utils.ib(color1, color2, "&lDepth Apple")))
                .setAmount(1)
                .setCustomModelData(1)

                .build();

        return i;
    }
    public static ItemStack NetheriteCompactedCarrot() {
        Color color1 = new Color(66, 25, 25);
        Color color2 = new Color(140, 99, 0);
        ItemStack i = new ItemBuilder(Material.GOLDEN_CARROT)
                .setDisplayName((Utils.ib(color1, color2, "&lNetherite Compacted Carrot")))
                .setAmount(1)
                .setCustomModelData(1)

                .build();

        return i;
    }
    public static ItemStack MysticalHelmet() {
        Color color1 = new Color(255, 41, 252);
        Color color2 = new Color(136, 9, 255);
        ItemStack i = new ItemBuilder(Material.NETHERITE_HELMET)
                .setDisplayName((Utils.ib(color1, color2, "&lMystical helmet")))
                .setAmount(1)
                .setCustomModelData(2)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack MysticalChestplate() {
        Color color1 = new Color(255, 41, 252);
        Color color2 = new Color(136, 9, 255);
        ItemStack i = new ItemBuilder(Material.NETHERITE_CHESTPLATE)
                .setDisplayName((Utils.ib(color1, color2, "&lMystical chestplate")))
                .setAmount(1)
                .setCustomModelData(2)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack MysticalLeggings() {
        Color color1 = new Color(255, 41, 252);
        Color color2 = new Color(136, 9, 255);
        ItemStack i = new ItemBuilder(Material.NETHERITE_LEGGINGS)
                .setDisplayName((Utils.ib(color1, color2, "&lMystical leggings")))
                .setAmount(1)
                .setCustomModelData(2)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack MysticalBoots() {
        Color color1 = new Color(255, 41, 252);
        Color color2 = new Color(136, 9, 255);
        ItemStack i = new ItemBuilder(Material.NETHERITE_BOOTS)
                .setDisplayName((Utils.ib(color1, color2, "&lMystical boots")))
                .setAmount(1)
                .setCustomModelData(2)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack Orb() {
        Color color1 = new Color(68, 166, 95);
        Color color2 = new Color(8, 86, 168);
        ItemStack i = new ItemBuilder(Material.STICK)
                .setDisplayName((Utils.ib(color1, color2, "&lCuration ORB")))
                .setAmount(1)
                .setCustomModelData(3)

                .build();

        return i;
    }
    public static ItemStack MysticalSword() {
        Color color1 = new Color(76, 37, 217);
        Color color2 = new Color(103, 28, 169);
        ItemStack i = new ItemBuilder(Material.NETHERITE_SWORD)
                .setDisplayName((Utils.ib(color1, color2, "&lMystical Sword")))
                .setAmount(1)
                .addEnchant(Enchantment.DAMAGE_ALL, 8)
                .addEnchant(Enchantment.FIRE_ASPECT, 8)
                .addEnchant(Enchantment.DIG_SPEED, 8)
                .setCustomModelData(2)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack MysticalPickaxe() {
        Color color1 = new Color(76, 37, 217);
        Color color2 = new Color(103, 28, 169);
        ItemStack i = new ItemBuilder(Material.NETHERITE_PICKAXE)
                .setDisplayName((Utils.ib(color1, color2, "&lMystical Pickaxe")))
                .setAmount(1)
                .addEnchant(Enchantment.DIG_SPEED, 8)
                .addEnchant(Enchantment.LUCK, 8)
                .setCustomModelData(2)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack MysticalAxe() {
        Color color1 = new Color(76, 37, 217);
        Color color2 = new Color(103, 28, 169);
        ItemStack i = new ItemBuilder(Material.NETHERITE_AXE)
                .setDisplayName((Utils.ib(color1, color2, "&lMystical Axe")))
                .setAmount(1)
                .addEnchant(Enchantment.DIG_SPEED, 8)
                .setCustomModelData(2)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack MysticalShovel() {
        Color color1 = new Color(76, 37, 217);
        Color color2 = new Color(103, 28, 169);
        ItemStack i = new ItemBuilder(Material.NETHERITE_SHOVEL)
                .setDisplayName((Utils.ib(color1, color2, "&lMystical Shovel")))
                .setAmount(1)
                .addEnchant(Enchantment.DIG_SPEED, 8)
                .setCustomModelData(2)
                .setUnbreakable(true)

                .build();

        return i;
    }

    public static ItemStack MysticalHoe() {
        Color color1 = new Color(76, 37, 217);
        Color color2 = new Color(103, 28, 169);
        ItemStack i = new ItemBuilder(Material.NETHERITE_HOE)
                .setDisplayName((Utils.ib(color1, color2, "&lMystical Hoe")))
                .setAmount(1)
                .addEnchant(Enchantment.DIG_SPEED, 8)
                .setCustomModelData(2)
                .setUnbreakable(true)

                .build();

        return i;
    }
    public static ItemStack OldAncestralOrb() {
        Color color1 = new Color(84, 84, 84);
        Color color2 = new Color(73, 35, 35);
        ItemStack i = new ItemBuilder(Material.STICK)
                .setDisplayName((Utils.ib(color1, color2, "&lOld Ancestral ORB")))
                .setAmount(1)
                .setCustomModelData(4)

                .build();

        return i;
    }


}
