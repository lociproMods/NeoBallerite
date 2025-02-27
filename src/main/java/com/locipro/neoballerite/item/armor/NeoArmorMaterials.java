package com.locipro.neoballerite.item.armor;

import com.locipro.neoballerite.util.ModTags;
import net.minecraft.Util;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.neoforged.neoforge.common.Tags;

import java.util.EnumMap;

import static com.locipro.neoballerite.NeoBallerite.MODID;

public class NeoArmorMaterials {
    public static final ArmorMaterial LEAD = new ArmorMaterial(
            23,
            Util.make(new EnumMap<>(ArmorType.class), map ->{
                map.put(ArmorType.BOOTS, 2);
                map.put(ArmorType.LEGGINGS, 5);
                map.put(ArmorType.CHESTPLATE, 7);
                map.put(ArmorType.HELMET, 2);
                map.put(ArmorType.BODY, 8);
            }),
            20,
            SoundEvents.ARMOR_EQUIP_CHAIN,
            0,
            0.15f,
            ModTags.Items.LEAD_REPAIR_MATERIALS,
            ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(MODID, "lead"))
    );
    public static final ArmorMaterial BALLERITE = new ArmorMaterial(
            35,
            Util.make(new EnumMap<>(ArmorType.class), map ->{
                map.put(ArmorType.BOOTS, 3);
                map.put(ArmorType.LEGGINGS, 6);
                map.put(ArmorType.CHESTPLATE, 8);
                map.put(ArmorType.HELMET, 3);
                map.put(ArmorType.BODY, 11);
            }),
            20,
            SoundEvents.ARMOR_EQUIP_TURTLE,
            1.3f,
            0.04f,
            ModTags.Items.BALLERITE_REPAIR_MATERIALS,
            ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(MODID, "ballerite"))
    );
    public static final ArmorMaterial LEAVES = new ArmorMaterial(
            0,
            Util.make(new EnumMap<>(ArmorType.class), map ->{
                map.put(ArmorType.BOOTS, 1);
                map.put(ArmorType.LEGGINGS, 0);
                map.put(ArmorType.CHESTPLATE, 0);
                map.put(ArmorType.HELMET, 0);
                map.put(ArmorType.BODY, 0);
            }),
            0,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0f,
            0.0f,
            Tags.Items.SANDSTONE_RED_STAIRS, // xd but in item properties should be unrepairable
            ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(MODID, "leaves"))
    );
}
