package com.github.linggify.minecraft.craftminer.javascript.wrappers.element;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.List;
import java.util.Objects;

public class ListWrapper<T extends IElementWrapper<?>> extends ElementWrapperBase<List<T>> {

    public ListWrapper(List<T> target) {
        super(target);
    }

    public IElementWrapper<?> get(int index) {
        return getValue().get(index);
    }

    public boolean contains(T element) {
        return getValue().contains(element);
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
