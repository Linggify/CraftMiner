package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.NumberWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;

import javax.annotation.Nonnull;
import java.util.UUID;

public class UUIDWrapper extends ElementWrapperBase<UUID> {
    public UUIDWrapper(@Nonnull UUID target) {
        super(target);
    }

    @Export
    @Nonnull
    public StringWrapper string() {
        return new StringWrapper(getValue().toString());
    }

    @Export
    @Nonnull
    public NumberWrapper variant() {
        return new NumberWrapper(getValue().variant());
    }

    @Export
    @Nonnull
    public NumberWrapper version() {
        return new NumberWrapper(getValue().version());
    }
}
