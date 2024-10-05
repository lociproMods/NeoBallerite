package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import static com.locipro.neoballerite.NeoBallerite.MODID;
import static com.locipro.neoballerite.item.ModItems.*;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {


    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.BALLERITE_ITEMS)
                .add(RAW_BALLERITE.get())
                .add(COOKED_BALLERITE.get())
                .add(CHARRED_BALLERITE.get())
                .add(COMPRESSED_BALLERITE_INGOT.get());
    }
}
