package com.locipro.neoballerite.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.Objects;

public class JamItem extends Item {
    private final ItemLike baseItem;
    public JamItem(ItemLike baseItem) {
        super(new Item.Properties()
                .food(Objects.requireNonNull(baseItem.asItem().components().get(DataComponents.FOOD), "A jam item oughta have a gosh darn food component you daft cu##, *howls at the stars* "))
                .stacksTo(32));
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
        String itemName = item.toString().toLowerCase().replace('_', ' ');
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
