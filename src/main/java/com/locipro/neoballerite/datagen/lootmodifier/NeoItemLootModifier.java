package com.locipro.neoballerite.datagen.lootmodifier;

import com.locipro.neoballerite.NeoBallerite;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class NeoItemLootModifier extends LootModifier {

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    public NeoItemLootModifier(LootItemCondition[] conditionsIn, int count1, int count2, Item drop, int maxBonusRolls) {
        super(conditionsIn);
        this.drop = drop;
        this.count1 = count1;
        this.count2 = count2;
        this.maxBonusRolls = maxBonusRolls;
    }
    private final Item drop;
    private final int count1;
    private final int count2;
    private final int maxBonusRolls;

    public static final MapCodec<NeoItemLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).and(inst.group(
                    Codec.INT.fieldOf("minCount").forGetter(e -> e.count1),
                    Codec.INT.fieldOf("maxCount").forGetter(e -> e.count2),
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("drop").forGetter(e -> e.drop),
                    Codec.INT.fieldOf("maxBonusRolls").forGetter(e -> e.maxBonusRolls)
            )).apply(inst, NeoItemLootModifier::new));
    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }



    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(context)) return generatedLoot;
        }


        UniformGenerator count = UniformGenerator.between(count1, count2);
        generatedLoot.add(new ItemStack(drop, count.getInt(context)));


        byte rolls = 0;
        // 33% for bonus rolls i guess
        while (rolls < maxBonusRolls) {
            if (context.getRandom().nextFloat() <= 0.33f) {
                generatedLoot.add(new ItemStack(drop, count.getInt(context)));
            }
            rolls++;
        }

        return generatedLoot;
    }


}
