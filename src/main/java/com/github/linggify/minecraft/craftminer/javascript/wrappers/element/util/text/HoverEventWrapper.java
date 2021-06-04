package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.text;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import net.minecraft.util.text.event.HoverEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HoverEventWrapper extends ElementWrapperBase<HoverEvent> {
    public HoverEventWrapper(@Nonnull HoverEvent target) {
        super(target);
    }

    @Export
    @Nonnull
    public StringWrapper value() {
        //TODO: implement wrappers for values
        return new StringWrapper("Not implemented yet");
    }

    @Export
    @Nullable
    public HoverEventActionWrapper action() {
        return optional(HoverEventActionWrapper::new, getValue().getAction());
    }
}
