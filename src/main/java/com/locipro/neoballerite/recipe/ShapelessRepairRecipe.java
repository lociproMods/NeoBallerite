package com.locipro.neoballerite.recipe;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class ShapelessRepairRecipe extends CustomRecipe {
    public ShapelessRepairRecipe(CraftingBookCategory category) {
        super(category);
    }
    private Pair<ItemStack, ItemStack> getItemsToCombine(CraftingInput input) {
        ItemStack tool = null;
        ItemStack repairIngredient = null;

        for (int i = 0; i < input.size(); i++ ){
            ItemStack iterating = input.getItem(i);
            if (!iterating.isEmpty()) {
                if (tool == null && iterating.has(DataComponents.DAMAGE)) {
                    tool = iterating;
                }else {
                    if (repairIngredient != null) {
                        return null;
                    }
                    repairIngredient = iterating;
                }
            }
        }
        return tool != null && repairIngredient != null && canCombine(tool, repairIngredient)
                ? Pair.of(tool, repairIngredient) : null;
    }

    private boolean canCombine(ItemStack tool, ItemStack repairIngredient) {
        return tool.has(DataComponents.MAX_DAMAGE)
                && tool.has(DataComponents.DAMAGE)
                && tool.getCount() == 1
                && tool.isRepairable()
                && !repairIngredient.has(DataComponents.MAX_DAMAGE)
                && !repairIngredient.has(DataComponents.DAMAGE)
                && !repairIngredient.isRepairable();

    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return getItemsToCombine(input) != null;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        Pair<ItemStack, ItemStack> pair = getItemsToCombine(input);
        if (pair == null) return null;
        ItemStack tool = pair.getFirst();
        ItemStack repairIng = pair.getSecond();

        int maxDamage = tool.getMaxDamage();
        int currentDamage = tool.getDamageValue();

    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return NeoRecipeSerializers.SHAPELESS_REPAIR_SERIALIZER.get();
    }
}
