package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.text;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.BooleanWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.text.event.HoverEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HoverEventActionWrapper extends ElementWrapperBase<HoverEvent.Action<?>> {
    public HoverEventActionWrapper(@Nonnull HoverEvent.Action<?> target) {
        super(target);
    }

    @Export
    @Nullable
    public StringWrapper name() {
        return optional(getValue().getName());
    }

    @Export
    @Nonnull
    public BooleanWrapper isAllowedFromServer() {
        return new BooleanWrapper(getValue().isAllowedFromServer());
    }
}
