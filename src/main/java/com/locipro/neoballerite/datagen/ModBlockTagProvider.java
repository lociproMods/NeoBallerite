package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import static com.locipro.neoballerite.block.ModBlocks.*;
import static com.locipro.neoballerite.NeoBallerite.MODID;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BALLERITE_ORE.get())
                .add(BURNT_BALLERITE_BLOCK.get())
                .add(CHARRED_BALLERITE_BLOCK.get())
                .add(COMPRESSED_BALLERITE_BLOCK.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(RAW_BALLERITE_BLOCK.get())
                .add(COOKED_BALLERITE_BLOCK.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(BALLERITE_ORE.get())
                .add(BURNT_BALLERITE_BLOCK.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(CHARRED_BALLERITE_BLOCK.get())
                .add(COMPRESSED_BALLERITE_BLOCK.get());


        tag(ModTags.Blocks.BALLERITE_BLOCKS)
                .add(BALLERITE_ORE.get())
                .add(RAW_BALLERITE_BLOCK.get())
                .add(COOKED_BALLERITE_BLOCK.get())
                .add(BURNT_BALLERITE_BLOCK.get())
                .add(CHARRED_BALLERITE_BLOCK.get())
                .add(COMPRESSED_BALLERITE_BLOCK.get());
    }
}
