package com.locipro.neoballerite.component;

import com.locipro.neoballerite.NeoBallerite;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class NeoDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, NeoBallerite.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> CAN_NEGATE_BUSH_SLOW = DATA_COMPONENT_TYPES.registerComponentType(
            "can_negate_bush_slow", builder -> builder.persistent(Codec.BOOL));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> ADDED_DURABILITY = DATA_COMPONENT_TYPES.registerComponentType(
            "added_durability", builder -> builder.persistent(Codec.INT));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Item>> SANDWICH_BREAD = DATA_COMPONENT_TYPES.registerComponentType(
            "sandwich_bread", itemBuilder -> itemBuilder.persistent(BuiltInRegistries.ITEM.byNameCodec())
    );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Item>> SANDWICH_CHEESE = DATA_COMPONENT_TYPES.registerComponentType(
            "sandwich_cheese", itemBuilder -> itemBuilder.persistent(BuiltInRegistries.ITEM.byNameCodec())
    );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Item>> SANDWICH_MEAT = DATA_COMPONENT_TYPES.registerComponentType(
            "sandwich_meat", itemBuilder -> itemBuilder.persistent(BuiltInRegistries.ITEM.byNameCodec())
    );

    /*private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                       UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator
                .apply(DataComponentType.builder()).build());

    }*/
}
