package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.text;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.text.event.ClickEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;

public class ClickEventWrapper extends ElementWrapperBase<ClickEvent> {
    public ClickEventWrapper(@Nonnull ClickEvent target) {
        super(target);
    }

    @Export
    @Nullable
    public StringWrapper value() {
        return optional(getValue().getValue());
    }

    @Export
    @Nullable
    public ClickEventActionWrapper action() {
        return optional((Function<ClickEvent.Action, ClickEventActionWrapper>) ClickEventActionWrapper::new, getValue().getAction());
    }
}
