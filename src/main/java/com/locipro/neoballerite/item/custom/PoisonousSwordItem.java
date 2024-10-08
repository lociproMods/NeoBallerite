package com.locipro.neoballerite.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class PoisonousSwordItem extends SwordItem {
    public PoisonousSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide) {
            target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
            if (target instanceof ServerPlayer player) {
                player.displayClientMessage(Component.literal(
                        "You've been poisoned by " + attacker.getDisplayName() + "!")
                        .withStyle(ChatFormatting.RED)
                        .withStyle(ChatFormatting.BOLD), true);
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}
