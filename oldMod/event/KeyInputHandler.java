package net.locipro.balleritemod.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.locipro.balleritemod.network.BalleritePackets;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String TRANSLATION_KEY_CATEGORY_BALLERITEMOD = "key.category.balleritemod.balleritemod";
    public static final String TRANSLATION_KEY_DRINK= "key.balleritemod.drink";
    public static KeyMapping drinkingKey;
    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (drinkingKey.consumeClick()) {
                ClientPlayNetworking.send(BalleritePackets.DRINKING_ID, PacketByteBufs.create());
            }
        });
    }

    public static void register() {
        drinkingKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                TRANSLATION_KEY_DRINK,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_P,
                TRANSLATION_KEY_CATEGORY_BALLERITEMOD
        ));
        registerKeyInputs();
    }

}
