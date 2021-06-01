package com.github.linggify.minecraft.craftminer.javascript.wrappers.element;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class BooleanWrapper extends ElementWrapperBase<Boolean> {

    public BooleanWrapper(Boolean target) {
        super(target);
    }

    @Override
    public JsonElement getJsonValue() {
        return new JsonPrimitive(getValue());
    }
}
