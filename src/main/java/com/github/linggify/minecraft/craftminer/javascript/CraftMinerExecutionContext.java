package com.github.linggify.minecraft.craftminer.javascript;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.set.ElementSetWrappers;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.set.IElementSetWrapper;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * This class is a container for accessing registries etc at runtime
 */
public class CraftMinerExecutionContext {

    private final JsonObject m_dumpRoot;

    private IElementSetWrapper<? extends IElementWrapper<Block>> m_blocks;
    private IElementSetWrapper<? extends IElementWrapper<Item>> m_items;

    public CraftMinerExecutionContext() {
        m_dumpRoot = new JsonObject();
    }

    protected JsonObject getDumps() {
        return m_dumpRoot;
    }

    /**
     * Retrieve a set containing all registered blocks
     * @return a set containing all registered blocks
     */
    public IElementSetWrapper<? extends IElementWrapper<Block>> blocks() {
        if (m_blocks == null) {
            m_blocks = ElementSetWrappers.fromBlocksRegistry();
            m_blocks.setDumpOutput(m_dumpRoot);
        }

        return m_blocks;
    }

    /**
     * Retrieve a set containing all registered blocks
     * @return a set containing all registered blocks
     */
    public IElementSetWrapper<? extends IElementWrapper<Item>> items() {
        if (m_items == null) {
            m_items = ElementSetWrappers.fromItemsRegistry();
            m_items.setDumpOutput(m_dumpRoot);
        }

        return m_items;
    }
}
