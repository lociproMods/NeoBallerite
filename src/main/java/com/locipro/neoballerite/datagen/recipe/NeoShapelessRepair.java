package com.locipro.neoballerite.datagen.recipe;

import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;

import java.util.List;
import java.util.function.Predicate;

// TODO this is just broken as fuck

public class NeoShapelessRepair extends ShapelessRecipe {
    /*private final List<Predicate<ItemStack>> ITEM_PREDICATES = List.of(
            stack -> stack.has(DataComponents.MAX_DAMAGE),
            stack -> stack.has(DataComponents.DAMAGE),
            stack -> stack.isRepairable()
    );*/

    private int addedDurability;
//    /** Ingredients should have all possible ingredients, like a sword and its repair item.**/
//    public NeoShapelessRepair(String group, CraftingBookCategory category, ItemStack result, NonNullList<Ingredient> ingredients, int addedDurability) {
//        super(group, category, result, ingredients);
//        this.addedDurability = addedDurability;
//    }
    public NeoShapelessRepair(CraftingBookCategory category) {
        super("",
                CraftingBookCategory.EQUIPMENT,
                new ItemStack(ModItems.LEAVES_BOOTS.get()),
                NonNullList.of(Ingredient.of(ItemTags.LEAVES), Ingredient.of(Items.STICK)));
        this.addedDurability = 8;
    }


    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        NeoBallerite.LOGGER.debug("assmebling");
        List<ItemStack> stacks = input.items();

        ItemStack repairable = stacks.get(0).copy();
        ItemStack ingredient = stacks.get(1).copy(); // We don't actually need the ingredient in anything rn.

        if (!repairable.has(DataComponents.DAMAGE)) return ItemStack.EMPTY; //!!nvm!!!!Ruled out by matches()

        int maxDamage = repairable.getMaxDamage();
        int currentDamage = repairable.getDamageValue();

        if (currentDamage == maxDamage) {
            repairable.set(DataComponents.MAX_DAMAGE, maxDamage + addedDurability);
        }else {
            repairable.set(DataComponents.DAMAGE, Math.max(currentDamage + addedDurability, maxDamage));
        }

        return repairable;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        // TODO fix
//        return NeoRecipeHelper.allPresent(input, ITEM_PREDICATES);
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return NeoRecipeSerializers.SHAPELESS_REPAIR_CRAFTING.get();
    }
}
