package net.locipro.balleritemod.mixin;

import net.locipro.balleritemod.hydration.HydrationManager;
import net.locipro.balleritemod.hydration.IHydratable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity implements IHydratable {
    @Unique
    private final HydrationManager hydrationManager = new HydrationManager((Player)(Object)this);

    @Override
    public HydrationManager getHydrationManager() {
        return hydrationManager;
    }

    @Inject(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;tick(Lnet/minecraft/world/entity/player/Player;)V", shift = At.Shift.AFTER))
    private void playerTick(CallbackInfo ci) {
        if (hydrationManager.hasThirst()) {
            hydrationManager.update();
        }
    }

    @Inject(method = "readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V", at = @At(value = "TAIL"))
    private void readCustomDataFromTagMixin(CompoundTag tag, CallbackInfo info) {
        hydrationManager.readNbt(tag);
    }

    @Inject(method = "addAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V", at = @At(value = "TAIL"))
    private void writeCustomDataToTagMixin(CompoundTag tag, CallbackInfo info) {
        hydrationManager.writeNbt(tag);
    }
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }
}
