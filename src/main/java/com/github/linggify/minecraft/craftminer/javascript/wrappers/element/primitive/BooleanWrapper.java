package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class BooleanWrapper extends ElementWrapperBase<Boolean> {

    public BooleanWrapper(Boolean target) {
        super(target);
    }

    public boolean get() {
        return getValue();
    }

    @Override
    public JsonElement getJsonValue() {
        return new JsonPrimitive(getValue());
    }
}
