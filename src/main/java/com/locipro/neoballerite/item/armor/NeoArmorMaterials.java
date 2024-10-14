package com.locipro.neoballerite.item.armor;

import com.locipro.neoballerite.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

import static com.locipro.neoballerite.NeoBallerite.MODID;

public class NeoArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, MODID);

    /*public static final Holder<ArmorMaterial> IRON = register("iron", Util.make(new EnumMap<>(ArmorItem.Type.class), p_323378_ -> {
        p_323378_.put(ArmorItem.Type.BOOTS, 2);
        p_323378_.put(ArmorItem.Type.LEGGINGS, 5);
        p_323378_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_323378_.put(ArmorItem.Type.HELMET, 2);
        p_323378_.put(ArmorItem.Type.BODY, 5);
    }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT));
    public static final Holder<ArmorMaterial> GOLD = register("gold", Util.make(new EnumMap<>(ArmorItem.Type.class), p_323383_ -> {
        p_323383_.put(ArmorItem.Type.BOOTS, 1);
        p_323383_.put(ArmorItem.Type.LEGGINGS, 3);
        p_323383_.put(ArmorItem.Type.CHESTPLATE, 5);
        p_323383_.put(ArmorItem.Type.HELMET, 2);
        p_323383_.put(ArmorItem.Type.BODY, 7);
    }), 25, SoundEvents.ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> Ingredient.of(Items.GOLD_INGOT));*/
    /*  public static final Holder<ArmorMaterial> DIAMOND = register("diamond", Util.make(new EnumMap<>(ArmorItem.Type.class), p_323380_ -> {
        p_323380_.put(ArmorItem.Type.BOOTS, 3);
        p_323380_.put(ArmorItem.Type.LEGGINGS, 6);
        p_323380_.put(ArmorItem.Type.CHESTPLATE, 8);
        p_323380_.put(ArmorItem.Type.HELMET, 3);
        p_323380_.put(ArmorItem.Type.BODY, 11);
    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> Ingredient.of(Items.DIAMOND));*/
    /*public static final Holder<ArmorMaterial> NETHERITE = register("netherite", Util.make(new EnumMap<>(ArmorItem.Type.class), p_323379_ -> {
        p_323379_.put(ArmorItem.Type.BOOTS, 3);
        p_323379_.put(ArmorItem.Type.LEGGINGS, 6);
        p_323379_.put(ArmorItem.Type.CHESTPLATE, 8);
        p_323379_.put(ArmorItem.Type.HELMET, 3);
        p_323379_.put(ArmorItem.Type.BODY, 11);
    }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> Ingredient.of(Items.NETHERITE_INGOT));*/
    public static final Holder<ArmorMaterial> LEAD = ARMOR_MATERIALS.register("lead", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 7);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 8);
            }),
            20,
            SoundEvents.ARMOR_EQUIP_CHAIN,
            () -> Ingredient.of(ModItems.LEAD_INGOT),
            List.of(
                    // Creates a new armor texture that will be located at:
                    // - 'assets/mod_id/textures/models/armor/copper_layer_1.png' for the outer texture
                    // - 'assets/mod_id/textures/models/armor/copper_layer_2.png' for the inner texture (only legs)
                    new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(MODID, "lead")
                    )/*,
                    // Creates a new armor texture that will be rendered on top of the previous at:
                    // - 'assets/mod_id/textures/models/armor/copper_layer_1_overlay.png' for the outer texture
                    // - 'assets/mod_id/textures/models/armor/copper_layer_2_overlay.png' for the inner texture (only legs)
                    // 'true' means that the armor material is dyeable; however, the item must also be added to the 'minecraft:dyeable' tag
                    new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(MODID, "lead"), "_overlay", false
                    )*/
            ),
            0,
            0.15f //Cause like, lead. Yknow. Heavy and stuff? ok.

    ));



    public static final Holder<ArmorMaterial> BALLERITE = ARMOR_MATERIALS.register("ballerite", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 11);
            }),
            14,
            SoundEvents.ARMOR_EQUIP_TURTLE,
            () -> Ingredient.of(ModItems.COMPRESSED_BALLERITE_INGOT),
            List.of(
                    new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(MODID, "ballerite")
                    )
            ),
            1.3f,
            0.04f

    ));


    public static final Holder<ArmorMaterial> LEAVES = ARMOR_MATERIALS.register("leaves", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 0);
                map.put(ArmorItem.Type.CHESTPLATE, 0);
                map.put(ArmorItem.Type.HELMET, 0);
                map.put(ArmorItem.Type.BODY, 0);
            }),
            0,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(ItemStack.EMPTY),
            List.of(
                    // Creates a new armor texture that will be located at:
                    // - 'assets/mod_id/textures/models/armor/copper_layer_1.png' for the outer texture
                    // - 'assets/mod_id/textures/models/armor/copper_layer_2.png' for the inner texture (only legs)
                    new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(MODID, "leaves")
                    )
            ),
            0.0f,
            0.0f

    ));
}
