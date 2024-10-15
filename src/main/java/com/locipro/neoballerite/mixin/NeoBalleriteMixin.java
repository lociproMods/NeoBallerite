package com.locipro.neoballerite.mixin;


import com.locipro.neoballerite.NeoBallerite;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** <a href="https://fabricmc.net/wiki/tutorial:mixin_examples#injecting_and_cancelling_with_a_return_value">check the wiki for examples and help</a>**/
@Mixin(TitleScreen.class)
public abstract class NeoBalleriteMixin {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo cbi) {
        NeoBallerite.LOGGER.info("This line is printed from the "  + NeoBallerite.MODID + " initializer mixin!");
    }
}
