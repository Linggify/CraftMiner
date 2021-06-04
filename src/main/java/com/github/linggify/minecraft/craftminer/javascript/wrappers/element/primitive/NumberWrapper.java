package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class NumberWrapper extends ElementWrapperBase<Number> {
    public NumberWrapper(Number target) {
        super(target);
    }

    public Number get() {
        return getValue();
    }

    @Override
    public JsonElement getJsonValue() {
        return new JsonPrimitive(getValue());
    }
}
