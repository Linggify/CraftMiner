package com.github.linggify.minecraft.craftminer.javascript;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.set.ElementSetWrappers;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.set.IElementSetWrapper;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ICommandSource;
import net.minecraft.item.Item;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.Objects;

/**
 * This class is a container for accessing registries etc at runtime
 */
public class CraftMinerExecutionContext {

    private final JsonObject m_dumpRoot;
    private final CommandSource m_caller;

    private IElementSetWrapper<? extends IElementWrapper<Block>> m_blocks;
    private IElementSetWrapper<? extends IElementWrapper<Item>> m_items;

    public CraftMinerExecutionContext(CommandSource caller) {
        m_dumpRoot = new JsonObject();
        m_caller = caller;
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

    /**
     * Sends the given string as a message to the script caller
     * @param message
     * @param formatting the formatting to use
     */
    public void show(String message, String... formatting) {
        IFormattableTextComponent component = new StringTextComponent(message);
        for (String format : formatting) {
            component = component.withStyle(Objects.requireNonNull(TextFormatting.getByName(format)));
        }

        m_caller.sendSuccess(component, false);
    }

    /**
     * Sends the given string as a message to the script caller
     * @param message
     */
    public void show(String message) {
        show(message, TextFormatting.WHITE.getName());
    }
}
