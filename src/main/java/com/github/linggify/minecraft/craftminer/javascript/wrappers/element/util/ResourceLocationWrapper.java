package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationWrapper extends ElementWrapperBase<ResourceLocation> {
    public ResourceLocationWrapper(ResourceLocation target) {
        super(target);
    }

    public StringWrapper path() {
        return new StringWrapper(getValue().getPath());
    }

    public StringWrapper namespace() {
        return new StringWrapper(getValue().getNamespace());
    }

    public StringWrapper full() {
        return new StringWrapper(getValue().getNamespace());
    }

    @Override
    public JsonElement getJsonValue() {
        return new JsonPrimitive(getValue().toString());
    }
}
