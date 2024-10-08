package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import static com.locipro.neoballerite.NeoBallerite.MODID;
import static com.locipro.neoballerite.item.ModItems.*;
import static com.locipro.neoballerite.block.ModBlocks.*;
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

        tag(ModTags.Items.WITHERED_LOGS)
                .add(WITHERED_LOG.asItem())
                .add(STRIPPED_WITHERED_LOG.asItem())
                .add(WITHERED_WOOD.asItem())
                .add(STRIPPED_WITHERED_WOOD.asItem());
        tag(ItemTags.PLANKS)
                .add(WITHERED_PLANKS.asItem());

        tag(ItemTags.LOGS)
                .add(WITHERED_LOG.get().asItem())
                .add(STRIPPED_WITHERED_LOG.get().asItem())
                .add(WITHERED_WOOD.get().asItem())
                .add(STRIPPED_WITHERED_WOOD.get().asItem());

        tag(ItemTags.LEAVES)
                .add(WITHERED_LEAVES.get().asItem());

        tag(ItemTags.LOGS_THAT_BURN)
                .add(WITHERED_LOG.get().asItem())
                .add(STRIPPED_WITHERED_LOG.get().asItem())
                .add(WITHERED_WOOD.get().asItem())
                .add(STRIPPED_WITHERED_WOOD.get().asItem());
        tag(ItemTags.WOODEN_FENCES)
                .add(WITHERED_FENCE.get().asItem());
        tag(ItemTags.FENCE_GATES)
                .add(WITHERED_FENCE_GATE.get().asItem());
        tag(ItemTags.WOODEN_DOORS)
                .add(WITHERED_DOOR.get().asItem());
        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(WITHERED_TRAPDOOR.get().asItem());
        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(WITHERED_PRESSURE_PLATE.get().asItem());
        tag(ItemTags.WOODEN_BUTTONS)
                .add(WITHERED_BUTTON.get().asItem());
        tag(ItemTags.WOODEN_SLABS)
                .add(WITHERED_SLAB.get().asItem());
        tag(ItemTags.WOODEN_STAIRS)
                .add(WITHERED_STAIRS.get().asItem());



    }
}
