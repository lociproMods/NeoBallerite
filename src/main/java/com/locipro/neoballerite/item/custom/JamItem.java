package com.locipro.neoballerite.item.custom;

import com.locipro.neoballerite.item.util.FoodMath;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class JamItem extends Item {
    private final ItemLike baseItem;
    public JamItem(ItemLike baseItem) {
        super(new Item.Properties());

        this.baseItem = baseItem;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(
                Component.literal("A jam made from " + getBaseItemNamePlural(baseItem) + ".")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.literal("--Jam item").withStyle(ChatFormatting.DARK_GREEN));
            tooltipComponents.add(Component.literal("--Valuable Trade").withStyle(ChatFormatting.GREEN));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public @NotNull ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.EAT;
    }

    @Override
    public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        FoodProperties baseItem = getBaseItem().asItem().getFoodProperties(getBaseItem().asItem().getDefaultInstance(), entity);
        assert baseItem != null;
        float saturationModifier = FoodMath.saturationModifierFromSaturationAndNutrition(
                baseItem.nutrition(), baseItem.saturation()
        );
        return new FoodProperties.Builder()
                .nutrition(baseItem.nutrition() * 2)
                .saturationModifier((float) (saturationModifier * 1.25))
                .build();
    }

    public static String getBaseItemNamePlural(ItemLike item) {
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
