package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.text;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ListWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.google.gson.JsonElement;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextComponentWrapper extends ElementWrapperBase<ITextComponent> {
    public TextComponentWrapper(@Nonnull ITextComponent target) {
        super(target);
    }

    @Export
    @Nullable
    public StringWrapper string() {
        return optional(getValue().getString());
    }

    @Export
    @Nullable
    public StringWrapper contents() {
        return optional(getValue().getContents());
    }

    @Export
    @Nullable
    public StyleWrapper style() {
        return optional(StyleWrapper::new, getValue().getStyle());
    }

    @Export
    @Nonnull
    public ListWrapper<TextComponentWrapper> siblings() {
        List<ITextComponent> siblings = getValue().getSiblings();
        if (siblings == null) {
            return new ListWrapper<>(new ArrayList<>());
        }

        return new ListWrapper<>(siblings.stream().map(TextComponentWrapper::new).collect(Collectors.toList()));
    }
}
