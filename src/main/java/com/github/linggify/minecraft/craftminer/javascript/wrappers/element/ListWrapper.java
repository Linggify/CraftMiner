package com.github.linggify.minecraft.craftminer.javascript.wrappers.element;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.List;

public class ListWrapper extends ElementWrapperBase<List<? extends IElementWrapper<?>>> {

    public ListWrapper(List<? extends IElementWrapper<?>> target) {
        super(target);
    }

    public IElementWrapper<?> get(int index) {
        return getValue().get(index);
    }

    @Override
    public JsonElement getJsonValue() {
        JsonArray result = new JsonArray();

        for (IElementWrapper<?> element : getValue()) {
            result.add(element.getJsonValue());
        }

        return result;
    }
}
