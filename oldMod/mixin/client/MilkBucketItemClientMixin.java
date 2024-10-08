package net.locipro.balleritemod.mixin.client;


import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

@Mixin(MilkBucketItem.class)
public abstract class MilkBucketItemClientMixin extends Item {
    public MilkBucketItemClientMixin(Properties settings) {
        super(settings);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        if (world != null) {
            super.appendHoverText(stack, world, tooltip, context);
            tooltip.add( Component.literal("Drinking the bucket massively rehydrates the player.").withStyle(ChatFormatting.GRAY));
            if (Screen.hasShiftDown()) {
                tooltip.add(Component.literal("Adds +6 hydration.").withStyle(ChatFormatting.AQUA));
            }
        }
    }
}
