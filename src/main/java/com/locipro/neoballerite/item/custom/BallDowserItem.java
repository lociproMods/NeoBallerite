package com.locipro.neoballerite.item.custom;

import com.locipro.neoballerite.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Objects;


// CTRL+H WHEN TYPE-CURSOR IS ON CLASS TO SEE HIERARCHY
public class BallDowserItem extends Item {
   /* private static final List<Block> rare_blocks = new ArrayList<Block>(){
        ModBlocks.BALLERITE_ORE,
    }*/
    public BallDowserItem(Properties properties) {
        super(properties.durability(69));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(  Component.literal("Right click on a block to scan for any ballerite ore below.").withStyle(ChatFormatting.GRAY));
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(  Component.literal("--Detector").withStyle(ChatFormatting.AQUA));
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide()) {
            Player player = Objects.requireNonNull(context.getPlayer());
            if (player instanceof ServerPlayer serverPlayer) {
                if (context.getHand() == InteractionHand.MAIN_HAND) {
                    BlockPos startingPos = context.getClickedPos();
                    int starting_y = startingPos.getY();
                    for (int i = starting_y; i >= starting_y - 50; i--) {
                        BlockPos currentIteratingBlockPos = new BlockPos(startingPos.getX(), i, startingPos.getZ());
                        if (blockIsValuable(level, currentIteratingBlockPos)) {
                            player.displayClientMessage(Component.literal("Found ballerite block at " + new BlockPos(currentIteratingBlockPos.getX(), currentIteratingBlockPos.getY(), currentIteratingBlockPos.getZ()-1).toShortString()), false);
                            ServerLevel serverLevel = (ServerLevel) level;
                            serverLevel.sendParticles(
                                    ParticleTypes.HAPPY_VILLAGER,
                                    startingPos.getX() + 0.5,
                                    startingPos.getY() + 1.5,
                                    startingPos.getZ() + 0.5,
                                    10,
                                    0,
                                    0,
                                    0,
                                    0.1f
                            );
                            context.getItemInHand().hurtAndBreak(1, ((ServerLevel)level), player,
                                    item -> player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                            level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
                            return super.useOn(context);
                        }
                    }
                }
            }
            player.displayClientMessage(Component.literal("No ballerite blocks found in the 50 blocks below."), true);
            context.getItemInHand().hurtAndBreak(1, ((ServerLevel)level), player,
                    item -> player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
            level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
        }
        return InteractionResult.SUCCESS;
}

    private boolean blockIsValuable(Level level, BlockPos currentIteratingBlockPos) {
        return level.getBlockState(currentIteratingBlockPos).is(ModTags.Blocks.BALLERITE_BLOCKS);
    }
}
