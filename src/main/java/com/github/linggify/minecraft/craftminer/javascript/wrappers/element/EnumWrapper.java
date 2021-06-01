package com.github.linggify.minecraft.craftminer.javascript.wrappers.element;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class EnumWrapper<T extends Enum<?>> extends ElementWrapperBase<T> {
    public EnumWrapper(T target) {
        super(target);
    }

    @Override
    public JsonElement getJsonValue() {
        return new JsonPrimitive(getValue().name());
    }
}
