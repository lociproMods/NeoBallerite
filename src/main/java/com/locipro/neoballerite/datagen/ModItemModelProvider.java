package com.locipro.neoballerite.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import static com.locipro.neoballerite.item.ModItems.*;
import static com.locipro.neoballerite.NeoBallerite.MODID;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(RAW_BALLERITE.get());
        basicItem(COOKED_BALLERITE.get());
        basicItem(CHARRED_BALLERITE.get());
        basicItem(COMPRESSED_BALLERITE_INGOT.get());
        basicItem(BALL_DOWSER.get());
    }
}
