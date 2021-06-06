package com.github.linggify.minecraft.craftminer.javascript.wrappers.set;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block.BlockWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.item.ItemWrapper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ElementSetWrappers {

    /**
     * Wrapper for a set of blocks
     */
    public static class BlockSetWrapper extends ElementSetWrapperBase<BlockWrapper>{
        public BlockSetWrapper(Set<BlockWrapper> values) {
            super(BlockSetWrapper::new, values);
        }

        public IElementSetWrapper<BlockWrapper> taggedWith(String namespace, String path) {
            return filter(block -> block.isTagged(new ResourceLocation(namespace, path)));
        }

        public IElementSetWrapper<BlockWrapper> inNamespace(String namespace) {
            return filter(block -> block.registryName().namespace().get().equals(namespace));
        }

        public IElementSetWrapper<BlockWrapper> withProperty(String property) {
            return filter(block -> block.hasProperty(property));
        }

        public IElementSetWrapper<ItemWrapper> getItems() {
            IElementSetWrapper<ItemWrapper> wrapper =  new ItemSetWrapper(getValues().stream().map(BlockWrapper::getItem).collect(Collectors.toSet()));
            wrapper.setDumpOutput(m_dumpRoot);

            return wrapper;
        }
    }

    public static BlockSetWrapper fromBlocksRegistry() {
        Set<BlockWrapper> wrapped = new HashSet<>();

        for (Block block : ForgeRegistries.BLOCKS) {
            wrapped.add(new BlockWrapper(block));
        }

        return new BlockSetWrapper(wrapped);
    }

    /**
     * Wrapper for items
     */
    public static class ItemSetWrapper extends ElementSetWrapperBase<ItemWrapper> {

        public ItemSetWrapper(Set<ItemWrapper> values) {
            super(ItemSetWrapper::new, values);
        }

        public IElementSetWrapper<ItemWrapper> taggedWith(String namespace, String path) {
            return filter(item -> item.isTagged(new ResourceLocation(namespace, path)));
        }

        public IElementSetWrapper<ItemWrapper> inNamespace(String namespace) {
            return filter(item -> item.registryName().namespace().get().equals(namespace));
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
