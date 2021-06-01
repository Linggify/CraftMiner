package com.github.linggify.minecraft.craftminer.javascript.wrappers.element;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class MapWrapper extends ElementWrapperBase<Map<String, ? extends IElementWrapper<?>>> {

    public MapWrapper(Map<String, ? extends IElementWrapper<?>> target) {
        super(target);
    }

    public IElementWrapper<?> get(String key) {
        return getValue().get(key);
    }

    @Override
    public JsonElement getJsonValue() {
        JsonObject result = new JsonObject();

        for (Map.Entry<String, ? extends IElementWrapper<?>> entry : getValue().entrySet()) {
            result.add(entry.getKey(), entry.getValue().getJsonValue());
        }

        return result;
    }
}
