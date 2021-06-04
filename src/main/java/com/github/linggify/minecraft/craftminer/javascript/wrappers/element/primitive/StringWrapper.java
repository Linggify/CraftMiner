package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import javax.annotation.Nonnull;

public class StringWrapper extends ElementWrapperBase<String> {

    public StringWrapper(@Nonnull String target) {
        super(target);
    }

    public String get() {
        return getValue();
    }

    @Override
    public JsonElement getJsonValue() {
        return new JsonPrimitive(getValue());
    }
}
