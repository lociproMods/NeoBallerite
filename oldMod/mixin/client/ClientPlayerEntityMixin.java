package net.locipro.balleritemod.mixin.client;

import com.mojang.authlib.GameProfile;
import net.locipro.balleritemod.hydration.HydrationManager;
import net.locipro.balleritemod.hydration.IHydratable;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayer {
    public ClientPlayerEntityMixin(ClientLevel world, GameProfile profile) {
        super(world, profile);
    }
    // TODO something is wrong here, player starts sprinting for a few frames then stops.
    @Inject(method = "sendPosition()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;sendIsSprintingIfNeeded()V", shift = At.Shift.AFTER))
    public void tickMovementMixin(CallbackInfo info) {
        HydrationManager hydrationManager = ((IHydratable) this).getHydrationManager();
        if (hydrationManager.hasThirst() && !this.isCreative() && hydrationManager.getHydration() <= 2)
            this.setSprinting(false);
    }

}
