package com.github.linggify.minecraft.craftminer.javascript.wrappers.set;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block.BlockWrapper;
import net.minecraft.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashSet;
import java.util.Set;

public class ElementSetWrappers {

    /**
     * Wrapper for a set of blocks
     */
    private static class BlockSetWrapper extends ElementSetWrapperBase<BlockWrapper>{
        public BlockSetWrapper(Set<BlockWrapper> values) {
            super(BlockSetWrapper::new, values);
        }
    }

    public static BlockSetWrapper fromBlocksRegistry() {
        Set<BlockWrapper> wrapped = new HashSet<>();

        for (Block block : ForgeRegistries.BLOCKS) {
            wrapped.add(new BlockWrapper(block));
        }

        return new BlockSetWrapper(wrapped);
    }
}
