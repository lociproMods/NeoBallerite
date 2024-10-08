package net.locipro.balleritemod.painting;

import net.locipro.balleritemod.BalleriteMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class ModPaintings {

    public static void registerPaintings() {
        BalleriteMod.LOGGER.info("Registering paintings for " + BalleriteMod.MOD_ID);
    }
    public static final PaintingVariant CRAPPY_OCEAN = registerPainting("crappy_ocean", new PaintingVariant(32, 32));
    public static final PaintingVariant CRAPPY_MOON = registerPainting("crappy_moon", new PaintingVariant(64, 64));
    public static final PaintingVariant CRAPPY_LANDSCAPE = registerPainting("crappy_landscape", new PaintingVariant(48, 32));
    private static PaintingVariant registerPainting(String name, PaintingVariant paintingVariant) {
        return Registry.register(BuiltInRegistries.PAINTING_VARIANT, new ResourceLocation(BalleriteMod.MOD_ID, name), paintingVariant);
    }

}
