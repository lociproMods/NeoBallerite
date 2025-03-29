package com.locipro.neoballerite.item.custom;

import com.locipro.neoballerite.item.util.FoodMath;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;


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
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        FoodProperties foodProperties = stack.get(DataComponents.FOOD);

        if (foodProperties != null) {
            float satMod = FoodMath.saturationModifierFromSaturationAndNutrition(
                    foodProperties.nutrition(), foodProperties.saturation()
            );
            stack.set(DataComponents.FOOD, new FoodProperties.Builder()
                    .nutrition(foodProperties.nutrition() * 2)
                    .saturationModifier((float)(satMod * 1.25))
                    .build());
        }
        return super.use(level, player, hand);
    }

    public static String getBaseItemNamePlural(ItemLike item) {
        /*String itemName = item.asItem()
                .toString()
                .toLowerCase()
                .replace('_', ' ')
                .replace(":", "")
                .replace(item.asItem().getCreatorModId(), "");*/ // Remove mod id
        StringBuilder builder = new StringBuilder();
        for (char c : item.asItem().toString().toCharArray()) {
            if (c != ':') continue;
            builder.append(c);
        }
        String itemName = item.asItem()
                .toString()
                .toLowerCase()
                .replace('_', ' ')
                .replace(":", "")
                .replace(builder.toString(), "");

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
