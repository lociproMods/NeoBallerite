package com.locipro.neoballerite.recipe;

import com.locipro.neoballerite.NeoBallerite;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, NeoBallerite.MODID);


    public static final Supplier<RecipeSerializer<ShapelessRepairRecipe>> SHAPELESS_REPAIR_SERIALIZER =
            RECIPE_SERIALIZERS.register("shapeless_repair_serializer",
                    () ->  new CustomRecipe.Serializer<>(ShapelessRepairRecipe::new));

    public static final Supplier<RecipeSerializer<SandwichRecipe>> SANDWICH_RECIPE_SERIALIZER =
            RECIPE_SERIALIZERS.register("sandwich_recipe_serializer",
                    () ->  new CustomRecipe.Serializer<>(SandwichRecipe::new));
}
