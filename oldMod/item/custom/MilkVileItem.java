package net.locipro.balleritemod.item.custom;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

public class MilkVileItem extends PotionItem {
    public MilkVileItem(Properties settings) {
        super(settings);
    }
    @Override
    public ItemStack getDefaultInstance() {
        return PotionUtils.setPotion(super.getDefaultInstance(), Potions.WATER);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        // REALLY SILLY TO DO THIS BECAUSE THIS EXTENDS "PotionItem" CLASS. WE ALREADY MIXED-INTO THAT CLASS
        // YOU FUCKING BOZO.
        /*if (!world.isClient() && user instanceof ServerPlayerEntity player) {
            HydrationManager manager = ((IHydratable)player).getHydrationManager();
            manager.addHydration(2);
        }*/
        if (!world.isClientSide()) {
            user.removeAllEffects();
        }
        return super.finishUsingItem(stack, world, user);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        tooltip.add( Component.literal("Milk vile, drink to rehydrate yourself!").withStyle(ChatFormatting.GRAY));
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.literal("Adds +2 hydration.").withStyle(ChatFormatting.AQUA));
        }
    }
}
