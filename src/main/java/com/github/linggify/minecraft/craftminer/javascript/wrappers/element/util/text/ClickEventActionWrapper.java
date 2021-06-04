package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.text;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.BooleanWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import net.minecraft.util.text.event.ClickEvent;

import javax.annotation.Nonnull;

public class ClickEventActionWrapper extends ElementWrapperBase<ClickEvent.Action> {
    public ClickEventActionWrapper(@Nonnull ClickEvent.Action target) {
        super(target);
    }

    @Export
    @Nonnull
    public StringWrapper name() {
        return new StringWrapper(getValue().getName());
    }

    @Export
    @Nonnull
    public BooleanWrapper isAllowedFromServer() {
        return new BooleanWrapper(getValue().isAllowedFromServer());
    }
}
