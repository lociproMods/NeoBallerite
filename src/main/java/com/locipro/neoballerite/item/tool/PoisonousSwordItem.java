package com.locipro.neoballerite.item.tool;

import net.minecraft.ChatFormatting;
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

public class PoisonousSwordItem extends SwordItem {
    private boolean allowed = false;

    public PoisonousSwordItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties, boolean configValue) {
        super(material, attackDamage, attackSpeed, properties);
        allowed = configValue;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Does poison damage").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GREEN));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof ServerPlayer serverPlayer && allowed) {
            target.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 1));
            if (target instanceof ServerPlayer serverTarget) {
                serverTarget.displayClientMessage(Component.literal(
                        "You've been poisoned by " + attacker.getDisplayName() + "!")
                        .withStyle(ChatFormatting.RED)
                        .withStyle(ChatFormatting.BOLD), true);
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}
