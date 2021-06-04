package com.github.linggify.minecraft.craftminer.javascript.wrappers.set;

import com.github.linggify.minecraft.craftminer.javascript.TagUtil;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block.BlockWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.item.ItemWrapper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class ElementSetWrappers {

    /**
     * Wrapper for a set of blocks
     */
    private static class BlockSetWrapper extends ElementSetWrapperBase<BlockWrapper>{
        public BlockSetWrapper(Set<BlockWrapper> values) {
            super(BlockSetWrapper::new, values);
        }

        public IElementSetWrapper<BlockWrapper> taggedWith(String namespace, String path) {
            return filter(new TagUtil().checkFor(namespace, path));
        }

        public IElementSetWrapper<BlockWrapper> inNamespace(String namespace) {
            return filter(block -> block.registryName().namespace().get().equals(namespace));
        }
    }

    public static BlockSetWrapper fromBlocksRegistry() {
        Set<BlockWrapper> wrapped = new HashSet<>();

        for (Block block : ForgeRegistries.BLOCKS) {
            wrapped.add(new BlockWrapper(block));
        }

        return new BlockSetWrapper(wrapped);
    }

    private static class ItemSetWrapper extends ElementSetWrapperBase<ItemWrapper> {

        public ItemSetWrapper(Set<ItemWrapper> values) {
            super(ItemSetWrapper::new, values);
        }
    }

    public static ItemSetWrapper fromItemsRegistry() {
        Set<ItemWrapper> wrapped = new HashSet<>();

        for (Item item : ForgeRegistries.ITEMS) {
            wrapped.add(new ItemWrapper(item));
        }

        return new ItemSetWrapper(wrapped);
    }
}
