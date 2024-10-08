package net.locipro.balleritemod.mixin;

import net.locipro.balleritemod.BalleriteMod;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class BalleriteModMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		BalleriteMod.LOGGER.info("This line is printed by the " + BalleriteMod.MOD_ID + " initializer mixin!");
	}
}
