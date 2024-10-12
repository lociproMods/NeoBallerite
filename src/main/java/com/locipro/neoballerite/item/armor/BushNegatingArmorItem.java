package com.locipro.neoballerite.item.armor;

import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.component.NeoDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.List;


// TODO Make repairable in normal recipes.
public class BushNegatingArmorItem extends ArmorItem {
    public BushNegatingArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (stack.get(NeoDataComponents.CAN_NEGATE_BUSH_SLOW) != null &&
                !stack.get(NeoDataComponents.CAN_NEGATE_BUSH_SLOW)) {
            tooltipComponents.add(Component.literal("Will not negate speed de-buffs until repaired.").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
        }else {
            tooltipComponents.add(Component.literal("Negates berry bush damage.").withStyle(ChatFormatting.GRAY));
        }
        if (stack.get(NeoDataComponents.ADDED_DURABILITY) != null) {
            tooltipComponents.add(Component.literal("Upgraded to durability " + stack.get(NeoDataComponents.ADDED_DURABILITY)));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    public void onBushEntry(BlockState blockState, Level level, ItemStack itemStack, Player player) {
        if (itemStack.get(NeoDataComponents.CAN_NEGATE_BUSH_SLOW)) {
            player.makeStuckInBlock(blockState, new Vec3(1.5F, 1.5f, 1.5f));
        }else {
            player.makeStuckInBlock(blockState, new Vec3(0.8F, 0.75, 0.8F));
        }



        if ((player.xOld != player.getX() || player.zOld != player.getZ()) && !level.isClientSide)
        {
            double travelledX = Math.abs(player.getX() - player.xOld);
            double travelledZ = Math.abs(player.getZ() - player.zOld);
            if (travelledX >= 0.25F || travelledZ >= 0.25F)
            {
                // Damages until minimum damage, then does nothing.
                if (itemStack.getDamageValue() + 1 > itemStack.getMaxDamage()) {
                    itemStack.setDamageValue(itemStack.getMaxDamage());
                    return;
                }
                itemStack.setDamageValue(itemStack.getDamageValue() + 1);
            }
        }
    }

    // Technically unnecessary.
    @Override
    public void onCraftedPostProcess(ItemStack stack, Level level) {
        stack.set(NeoDataComponents.CAN_NEGATE_BUSH_SLOW, true);
        super.onCraftedPostProcess(stack, level);
    }

    // This is where we actually handle CAN_NEGATE_BUSH_SLOW setting logic.
    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof ServerPlayer serverPlayer) {
            if (itemStack.getDamageValue() < itemStack.getMaxDamage()) {
                itemStack.set(NeoDataComponents.CAN_NEGATE_BUSH_SLOW, true);
            }else {
                itemStack.set(NeoDataComponents.CAN_NEGATE_BUSH_SLOW, false);
            }
            super.inventoryTick(itemStack, level, entity, slotId, isSelected);
        }
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return super.isValidRepairItem(toRepair, repair);
    }
}
