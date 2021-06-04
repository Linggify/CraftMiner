package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class EnumWrapper<T extends Enum<?>> extends ElementWrapperBase<T> {
    public EnumWrapper(T target) {
        super(target);
    }

    public String get() {
        return getValue().name();
    }

    @Override
    public JsonElement getJsonValue() {
        return new JsonPrimitive(getValue().name());
    }
}
