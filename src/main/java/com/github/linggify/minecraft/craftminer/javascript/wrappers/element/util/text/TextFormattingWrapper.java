package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.text;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ExceptionWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.BooleanWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.NumberWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.ColorWrapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;

public class TextFormattingWrapper extends ElementWrapperBase<TextFormatting> {
    public TextFormattingWrapper(@Nonnull TextFormatting target) {
        super(target);
    }

    @Export
    @Nonnull
    public StringWrapper name() {
        return new StringWrapper(getValue().name());
    }

    @Export
    @Nonnull
    public NumberWrapper id() {
        return new NumberWrapper(getValue().getId());
    }

    @Export
    @Nonnull
    public BooleanWrapper isColor() {
        return new BooleanWrapper(getValue().isColor());
    }

    @Export
    @Nonnull
    public IElementWrapper<?> color() {
        if (isColor().get()) {
            return new ColorWrapper(getValue().getColor());
        }

        return new ExceptionWrapper(new IllegalArgumentException("not a color"));
    }

    @Export
    @Nonnull
    public BooleanWrapper isFormat() {
        return new BooleanWrapper(getValue().isFormat());
    }

    @Export
    @Nonnull
    public StringWrapper asString() {
        return new StringWrapper(getValue().toString());
    }
}
