package com.locipro.neoballerite.recipe;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.level.ItemLike;

// TODO look into this another day. (The point is procedural data gen)
// https://docs.neoforged.net/docs/resources/server/recipes/
public class NeoShapelessRepairBuilder extends ShapelessRecipeBuilder {
    public NeoShapelessRepairBuilder(RecipeCategory category, ItemLike result, int count) {
        super(category, result, count);
    }
}
