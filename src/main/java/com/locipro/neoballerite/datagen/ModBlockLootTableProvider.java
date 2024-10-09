package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;
import static com.locipro.neoballerite.block.ModBlocks.*;
import static com.locipro.neoballerite.item.ModItems.*;


public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }
    protected static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    protected static final float[] HIGHER_LEAVES_SAPLING_CHANCES = new float[]{0.06F, 0.0725F, 0.093333336F, 0.12F};


    @Override
    protected void generate() {

        add(BALLERITE_ORE.get(),
                createVariableOreDrops(BALLERITE_ORE.get(), RAW_BALLERITE, UniformGenerator.between(1, 4)));
        add(LEAD_ORE.get(),
                createVariableOreDrops(LEAD_ORE.get(), RAW_LEAD, UniformGenerator.between(1, 2)));
        add(DEEPSLATE_LEAD_ORE.get(),
                createVariableOreDrops(DEEPSLATE_LEAD_ORE.get(), RAW_LEAD, UniformGenerator.between(1, 2)));
        // Like clay
        add(RAW_BALLERITE_BLOCK.get(),
                createSingleItemTableWithSilkTouch(RAW_BALLERITE_BLOCK.get(), RAW_BALLERITE, ConstantValue.exactly(4.0f)));
        add(COOKED_BALLERITE_BLOCK.get(),
                createSingleItemTableWithSilkTouch(COOKED_BALLERITE_BLOCK.get(), COOKED_BALLERITE, ConstantValue.exactly(4.0f)));
        dropSelf(BURNT_BALLERITE_BLOCK.get());
        dropSelf(CHARRED_BALLERITE_BLOCK.get());
        dropSelf(COMPRESSED_BALLERITE_BLOCK.get());
        dropSelf(LEAD_BLOCK.get());
        dropSelf(RAW_LEAD_BLOCK.get());


        dropSelf(WITHERED_SAPLING.get());
        dropSelf(WITHERED_LOG.get());
        dropSelf(STRIPPED_WITHERED_LOG.get());

        add(WITHERED_LEAVES.get(), createLeavesDrops(WITHERED_LEAVES.get(), WITHERED_SAPLING.get(), HIGHER_LEAVES_SAPLING_CHANCES));
        dropSelf(WITHERED_WOOD.get());
        dropSelf(STRIPPED_WITHERED_WOOD.get());

        dropSelf(WITHERED_PLANKS.get());
        add(WITHERED_SLAB.get(), createSlabItemTable(WITHERED_SLAB.get()));
        dropSelf(WITHERED_STAIRS.get());
        dropSelf(WITHERED_TRAPDOOR.get());
        dropSelf(WITHERED_FENCE.get());
        dropSelf(WITHERED_FENCE_GATE.get());
        dropSelf(WITHERED_BUTTON.get());
        dropSelf(WITHERED_PRESSURE_PLATE.get());
        add(WITHERED_DOOR.get(), createDoorTable(WITHERED_DOOR.get()));


        dropSelf(STAR_SAPLING.get());
        dropSelf(STAR_LOG.get());
        dropSelf(STRIPPED_STAR_LOG.get());

        add(STAR_LEAVES.get(), createLeavesDrops(STAR_LEAVES.get(), STAR_SAPLING.get(), HIGHER_LEAVES_SAPLING_CHANCES));
        dropSelf(STAR_WOOD.get());
        dropSelf(STRIPPED_STAR_WOOD.get());

        dropSelf(STAR_PLANKS.get());
        add(STAR_SLAB.get(), createSlabItemTable(STAR_SLAB.get()));
        dropSelf(STAR_STAIRS.get());
        dropSelf(STAR_TRAPDOOR.get());
        dropSelf(STAR_FENCE.get());
        dropSelf(STAR_FENCE_GATE.get());
        dropSelf(STAR_BUTTON.get());
        dropSelf(STAR_PRESSURE_PLATE.get());
        add(STAR_DOOR.get(), createDoorTable(STAR_DOOR.get()));

    }

    protected LootTable.Builder createVariableOreDrops(Block block, ItemLike item, NumberProvider numberProvider) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(numberProvider))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    // These are all the blocks that will need a loot table generated.
    // If you want a block to drop nothing, simply add ".noLootTable()" to its builder pattern when making the block
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
