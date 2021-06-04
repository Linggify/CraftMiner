package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.SoundEvent;

public class SoundEventWrapper extends ElementWrapperBase<SoundEvent> {

    public SoundEventWrapper(SoundEvent target) {
        super(target);
    }

    public ResourceLocationWrapper registryName() {
        return new ResourceLocationWrapper(getValue().getRegistryName());
    }

    public ResourceLocationWrapper location() {
        return new ResourceLocationWrapper(getValue().getLocation());
    }

    @Override
    public JsonElement getJsonValue() {
        JsonObject result = new JsonObject();

        result.add("registryName", registryName().getJsonValue());
        result.add("location", location().getJsonValue());

        return result;
    }
}
