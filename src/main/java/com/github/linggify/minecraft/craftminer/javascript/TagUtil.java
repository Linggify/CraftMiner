package com.github.linggify.minecraft.craftminer.javascript;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block.BlockWrapper;
import net.minecraft.util.ResourceLocation;

import java.util.function.Predicate;

public class TagUtil {

    public ResourceLocation get(String namespace, String path) {
        return new ResourceLocation(namespace, path);
    }

    public UnsafePredicate<BlockWrapper> checkFor(String namespace, String path) {
        return checkFor(get(namespace, path));
    }

    public UnsafePredicate<BlockWrapper> checkFor(ResourceLocation tag) {
        return blockWrapper -> blockWrapper.isTagged(tag);
    }
}
