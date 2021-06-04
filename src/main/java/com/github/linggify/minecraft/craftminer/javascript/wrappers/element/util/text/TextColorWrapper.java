package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.text;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.ColorWrapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.text.Color;

import javax.annotation.Nonnull;

public class TextColorWrapper extends ElementWrapperBase<Color> {
    public TextColorWrapper(Color target) {
        super(target);
    }

    @Export
    @Nonnull
    public ColorWrapper color() {
        return new ColorWrapper(getValue().getValue());
    }

    @Export
    @Nonnull
    public StringWrapper name() {
        return new StringWrapper(getValue().serialize());
    }
}
