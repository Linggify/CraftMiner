package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ToolTypeWrapper extends ElementWrapperBase<ToolType> {
    public ToolTypeWrapper(@Nonnull ToolType target) {
        super(target);
    }

    @Export
    @Nullable
    public StringWrapper name() {
        return optional(getValue().getName());
    }
}
