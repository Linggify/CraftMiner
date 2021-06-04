package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.item;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.text.TextFormattingWrapper;
import net.minecraft.item.Rarity;

import javax.annotation.Nonnull;

public class ItemRarityWrapper extends ElementWrapperBase<Rarity> {

    public ItemRarityWrapper(@Nonnull Rarity target) {
        super(target);
    }

    @Export
    @Nonnull
    public StringWrapper name() {
        return new StringWrapper(getValue().name());
    }

    @Export
    @Nonnull
    public TextFormattingWrapper color() {
        return new TextFormattingWrapper(getValue().color);
    }
}
