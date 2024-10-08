package com.locipro.neoballerite.item;

import com.locipro.neoballerite.datagen.ModBlockTagProvider;
import com.locipro.neoballerite.util.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModTiers {
    /*  WOOD(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 2.0F, 0.0F, 15, () -> Ingredient.of(ItemTags.PLANKS)),
    STONE(BlockTags.INCORRECT_FOR_STONE_TOOL, 131, 4.0F, 1.0F, 5, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)),
    IRON(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6.0F, 2.0F, 14, () -> Ingredient.of(Items.IRON_INGOT)),
    DIAMOND(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 8.0F, 3.0F, 10, () -> Ingredient.of(Items.DIAMOND)),
    GOLD(BlockTags.INCORRECT_FOR_GOLD_TOOL, 32, 12.0F, 0.0F, 22, () -> Ingredient.of(Items.GOLD_INGOT)),
    NETHERITE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9.0F, 4.0F, 15, () -> Ingredient.of(Items.NETHERITE_INGOT));
*/
/*COMPRESSED_BALLERITE(3, 956, 8.0f, 2.0f, 24, ModItems.COMPRESSED_BALLERITE),
    LEAD(2, 300, 6.0f, 2.0f, 20, ModItems.LEAD_INGOT);*/
    public static final Tier BALLERITE_TIER = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_BALLERITE_TOOL,
            956,
            7.5f,
            2.5f,
            19,
            () -> Ingredient.of(ModItems.COMPRESSED_BALLERITE_INGOT.get())
    );
    public static final Tier LEAD_TIER = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            400,
            6.0f,
            1.5f,
            19,
            () -> Ingredient.of(ModItems.LEAD_INGOT.get())
    );
}