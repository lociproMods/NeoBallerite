package com.locipro.neoballerite.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class NeoClaymoreItem extends SwordItem {

    private boolean doesPoison = false;

    public NeoClaymoreItem(ToolMaterial material, Properties properties) {
        super(material, 5.25f + material.attackDamageBonus(), -2.8f, properties);
    }


    public NeoClaymoreItem doesPoison() {
        this.doesPoison = true;
        return this;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(  Component.literal("Does more damage and slows enemies").withStyle(ChatFormatting.GRAY));
        if (doesPoison) {
            tooltipComponents.add(Component.literal("Does poison damage").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GREEN));
        }
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(  Component.literal("but is it better than an axe?").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
            if (Screen.hasControlDown()) {
                tooltipComponents.add(Component.literal("Ofcourse idiot, it's enchantable! Axes aren't!").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.RED));
            }
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof ServerPlayer serverAttacker) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 1));
            if (doesPoison) {
                target.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 1));
                if (target instanceof ServerPlayer serverTarget) {
                    serverTarget.displayClientMessage(Component.literal(
                                        "You've been poisoned by " + serverAttacker.getScoreboardName() + "!")
                                .withStyle(ChatFormatting.RED)
                                .withStyle(ChatFormatting.BOLD), true);
                }
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}