package com.locipro.neoballerite.recipe.serializers;

import com.locipro.neoballerite.recipe.ShapelessRepairRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

// unused.
public class ShapelessRepairSerializer implements RecipeSerializer<ShapelessRepairRecipe> {

    /*public static final MapCodec<ShapelessRepairRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ItemStack.CODEC.fieldOf("toRepair").forGetter(ShapelessRepairRecipe::getTool)
    ).apply(instance, ShapelessRepairRecipe::new));
*/
    // TODO Zawg idk

    @Override
    public MapCodec<ShapelessRepairRecipe> codec() {
        return null;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ShapelessRepairRecipe> streamCodec() {
        return null;
    }
}
