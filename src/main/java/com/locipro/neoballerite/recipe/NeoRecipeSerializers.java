package com.locipro.neoballerite.recipe;

import com.locipro.neoballerite.NeoBallerite;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoRecipeSerializers {
    public static DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, NeoBallerite.MODID);


    public static Supplier<RecipeSerializer<ShapelessRepairRecipe>> SHAPELESS_REPAIR_SERIALIZER =
            RECIPE_SERIALIZERS.register("shapeless_repair_serializer",
                    () ->  new SimpleCraftingRecipeSerializer<>(ShapelessRepairRecipe::new));
}
