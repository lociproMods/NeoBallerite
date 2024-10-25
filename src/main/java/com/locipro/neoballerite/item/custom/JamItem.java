package com.locipro.neoballerite.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ItemLike;

import java.util.List;


public class JamItem extends Item {
    private final ItemLike baseItem;
    public JamItem(ItemLike baseItem) {
        super(new Item.Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(baseItem.asItem().components().get(DataComponents.FOOD).nutrition() * 2)
                        .saturationModifier(baseItem.asItem().components().get(DataComponents.FOOD).saturation() * 2)
                        .build())
                .stacksTo(32));

        /*// Doesn't work.
        FoodProperties originalFood = baseItem.asItem().components().get(DataComponents.FOOD);
        FoodProperties newFood = new FoodProperties.Builder()
                .saturationModifier(originalFood.saturation() * 2)
                .nutrition(originalFood.nutrition() * 2)
                .build();
        this.getDefaultInstance().set(DataComponents.FOOD, newFood); // <- ?*/

        this.baseItem = baseItem;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(
                Component.literal("A jam from " + getBaseItemNamePlural(baseItem) + ".")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.literal("--Jam item").withStyle(ChatFormatting.DARK_GREEN));
            tooltipComponents.add(Component.literal("--Valuable Trade").withStyle(ChatFormatting.GREEN));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    public static String getBaseItemNamePlural(ItemLike item) {
        /*String itemName = item
                .toString()
                .toLowerCase()
                .replace('_', ' ');*/
//        String itemName = item.asItem().getDescriptionId();
        String itemName = item.asItem()
                .toString()
                .toLowerCase()
                .replace('_', ' ')
                .replace(":", "")
                .replace(item.asItem().getCreatorModId(item.asItem().getDefaultInstance()), "");
        char lastCharacter = itemName.charAt(itemName.length() - 1);
        return
            switch (lastCharacter) {
                case 'y' -> itemName.replace("y", "ies");
                case 'o' -> itemName + "es";
                default -> itemName;
            };
    }
    public ItemLike getBaseItem() {
        return baseItem;
    }
}
