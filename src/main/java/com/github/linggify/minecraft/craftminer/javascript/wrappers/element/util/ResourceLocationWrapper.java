package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class ResourceLocationWrapper extends ElementWrapperBase<ResourceLocation> {
    public ResourceLocationWrapper(@Nonnull ResourceLocation target) {
        super(target);
    }

    @Nonnull
    public StringWrapper path() {
        return new StringWrapper(getValue().getPath());
    }

    @Nonnull
    public StringWrapper namespace() {
        return new StringWrapper(getValue().getNamespace());
    }

    @Nonnull
    public StringWrapper full() {
        return new StringWrapper(getValue().toString());
    }

    @Override
    public JsonElement getJsonValue() {
        return new JsonPrimitive(getValue().toString());
    }
}
