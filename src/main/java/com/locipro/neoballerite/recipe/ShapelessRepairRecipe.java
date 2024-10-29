package com.locipro.neoballerite.recipe;

import com.locipro.neoballerite.component.NeoDataComponents;
import com.locipro.neoballerite.item.ModItems;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

// Hmm... Maybe I should've extended ShapelessRecipe instead.
// Most of these "custom" recipes in vanilla are hardcoded. No need to make our own builder, just hard code that bitch. check hierarchy of CustomRecipe
public class ShapelessRepairRecipe extends CustomRecipe {
    private static final int addedDurability = 16;
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
        /*for (int i = 0; i < input.size(); i++ ){
            ItemStack iterating = input.getItem(i);
            if (!iterating.isEmpty()) {
                if (tool == null && Ingredient.of(iterating).test(ModItems.LEAVES_BOOTS.toStack())) {
                    tool = iterating;
                }else {
                    if (repairIngredient != null) {
                        return null;
                    }
                    if (iterating.is(ModBlocks.STAR_LEAVES.asItem())) {
                        repairIngredient = iterating;
                    }
                }
            }
        }*/
        return tool != null && repairIngredient != null && canCombine(tool, repairIngredient)
                ? Pair.of(tool, repairIngredient) : null;
    }

    private boolean canCombine(ItemStack tool, ItemStack repairIngredient) {
        return tool.is(ModItems.LEAVES_BOOTS)
            && repairIngredient.is(ItemTags.LEAVES);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return getItemsToCombine(input) != null;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        Pair<ItemStack, ItemStack> pair = getItemsToCombine(input);
        if (pair == null) return null;


        ItemStack tool = pair.getFirst().copy();

        ItemStack repairIngredient = pair.getSecond();
        int count = repairIngredient.getCount();


        int currentDamage = tool.getDamageValue();
        int maxDamage = tool.getMaxDamage();

        int originalMaxDamage = new ItemStack(tool.getItem()).getMaxDamage();


        if (!tool.isDamaged()) {
            int newMaxDamage = maxDamage + addedDurability * count;
            tool.set(DataComponents.MAX_DAMAGE, maxDamage + addedDurability * count);
            tool.set(NeoDataComponents.ADDED_DURABILITY, newMaxDamage - originalMaxDamage);

            //repairIngredient.setCount(0); No, doesn't work, because removes items even if you don't actually craft.
            // Remember, assemble gets executed even if you don't actually craft the item.


            if (!tool.has(DataComponents.ENCHANTMENT_GLINT_OVERRIDE)) {
                tool.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
            }
            return tool;
        }else {
            tool.set(DataComponents.DAMAGE, Math.max(currentDamage - addedDurability, 0));
        }


        return tool;

    }


    // This doesn't actually determine what stays in the crafting menu all the time
    // But this only runs AFTER you take the item out.
    @Override
    public @NotNull NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        Pair<ItemStack, ItemStack> pair = getItemsToCombine(input);
        if (pair == null) return nonnulllist;

        ItemStack tool = pair.getFirst().copy();
        if (!tool.isDamaged()) {
            pair.getSecond().setCount(0);
        }
        return super.getRemainingItems(input);
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
