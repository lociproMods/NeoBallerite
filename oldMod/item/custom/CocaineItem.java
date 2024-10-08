package net.locipro.balleritemod.item.custom;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import static net.locipro.balleritemod.util.foodcomponents.NarcoticsFoodComponents.COCAINE;

public class CocaineItem extends Item {
    public CocaineItem(Properties settings) {
        super(settings.food(COCAINE).stacksTo(8));
    }
    private final int ticker = 0;
    private final boolean ate = false;
    private boolean high = false;
    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (entity instanceof Player player) {
            /*if (ate) {
                ticker++;
                if (ticker > 600) {
                    ticker = 0;
                    ate = false;
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0, false, true));
                }
            }*/
            if (high) {
                if (!(player.hasEffect(MobEffects.MOVEMENT_SPEED))) {
                    high = false;
                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0, false, true));
                    player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 300, 0, false, true));
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        if (!world.isClientSide() && user instanceof Player) {
            //ate = true;
            high = true;
        }
        return super.finishUsingItem(stack, world, user);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        tooltip.add( Component.literal("Powerful narcotic that makes you feel speedy.").withStyle(ChatFormatting.RED));
        tooltip.add( Component.literal("Be careful for the withdrawal symptoms!").withStyle(ChatFormatting.RED));
        if (Screen.hasShiftDown()) {
            tooltip.add( Component.literal("-Horse Food").withStyle(ChatFormatting.GRAY));
            tooltip.add( Component.literal("--Addictive").withStyle(ChatFormatting.DARK_GREEN));
            tooltip.add( Component.literal("--Harmful").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
