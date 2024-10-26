package com.locipro.neoballerite.event;

import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.item.NeoJams;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;

@EventBusSubscriber(modid = NeoBallerite.MODID, bus = EventBusSubscriber.Bus.MOD)
public class FMLLoadCompleteHandler {
    @SubscribeEvent
    public static void fmlLoadComplete(FMLLoadCompleteEvent event) {
        //NeoBallerite.LOGGER.debug("Jams list : {}", NeoJams.JAMS);
    }
}
