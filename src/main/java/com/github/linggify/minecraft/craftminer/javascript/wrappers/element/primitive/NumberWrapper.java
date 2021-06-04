package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import javax.annotation.Nonnull;

public class NumberWrapper extends ElementWrapperBase<Number> {
    public NumberWrapper(@Nonnull Number target) {
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
