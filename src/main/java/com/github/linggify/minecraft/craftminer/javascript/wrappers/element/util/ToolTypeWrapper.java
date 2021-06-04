package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.minecraftforge.common.ToolType;

public class ToolTypeWrapper extends ElementWrapperBase<ToolType> {
    public ToolTypeWrapper(ToolType target) {
        super(target);
    }

    public StringWrapper name() {
        return new StringWrapper(getValue().getName());
    }

    @Override
    public JsonElement getJsonValue() {
        return new JsonPrimitive(getValue().getName());
    }
}
