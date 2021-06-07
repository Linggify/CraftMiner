package com.github.linggify.minecraft.craftminer.javascript;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.state.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Contains utility methods for interacting with blockstates
 */
public class BlockStateUtil {

    public static void withGenSubStates(BlockState baseState, Consumer<BlockState> callback) {
        List<Property<?>> properties = new ArrayList<>();
        properties.addAll(baseState.getProperties());

        if (properties.isEmpty()) {
            callback.accept(baseState);
        } else {
            Property<? extends Comparable<?>> nextProperty = properties.get(0);
            List<Property<? extends Comparable<?>>> otherProperties = properties.size() > 1 ? properties.subList(1, properties.size()) : null;
            withGenSubStatesInner(baseState, callback, nextProperty, otherProperties);
        }
    }

    private static <T extends Comparable<T>> void withGenSubStatesInner(BlockState baseState, Consumer<BlockState> callback, Property<T> current, List<Property<? extends Comparable<?>>> properties) {
        for (T value : current.getPossibleValues()) {
            BlockState newState = baseState.setValue(current, value);

            if (properties != null) {
                Property<? extends Comparable<?>> nextProperty = properties.get(0);
                List<Property<? extends Comparable<?>>> otherProperties = properties.size() > 1 ? properties.subList(1, properties.size()) : null;
                withGenSubStatesInner(newState, callback, nextProperty, otherProperties);
            } else {
                callback.accept(newState);
            }
        }
    }
}
