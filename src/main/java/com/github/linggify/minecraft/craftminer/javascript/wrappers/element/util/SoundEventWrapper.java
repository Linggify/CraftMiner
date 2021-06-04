package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.SoundEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SoundEventWrapper extends ElementWrapperBase<SoundEvent> {

    public SoundEventWrapper(@Nonnull SoundEvent target) {
        super(target);
    }

    @Export
    @Nullable
    public ResourceLocationWrapper registryName() {
        return optional(ResourceLocationWrapper::new, getValue().getRegistryName());
    }

    @Export
    @Nullable
    public ResourceLocationWrapper location() {
        return optional(ResourceLocationWrapper::new, getValue().getLocation());
    }
}
