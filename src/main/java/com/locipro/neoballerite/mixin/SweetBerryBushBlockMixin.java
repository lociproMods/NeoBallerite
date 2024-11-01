package com.locipro.neoballerite.mixin;


import com.locipro.neoballerite.item.armor.BushNegatingArmorItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** <a href="https://fabricmc.net/wiki/tutorial:mixin_examples#injecting_and_cancelling_with_a_return_value">check the wiki for examples and help</a>**/
@Mixin(SweetBerryBushBlock.class)
public abstract class SweetBerryBushBlockMixin extends BushBlock implements BonemealableBlock {
    protected SweetBerryBushBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "entityInside",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;makeStuckInBlock(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/phys/Vec3;)V",
                    shift = At.Shift.BEFORE),
            cancellable = true)
    protected void xEntityInside(BlockState state, Level level, BlockPos pos, Entity entity, CallbackInfo cbi) {
        if (entity instanceof Player player) {
            for (ItemStack itemStack : player.getInventory().armor)
            {
                if (itemStack.getItem() instanceof BushNegatingArmorItem armorItem)
                {
                    armorItem.onBushEntry(state, level, itemStack, player);
                    cbi.cancel();
                }
            }
        }
    }
}
