package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.text;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.BooleanWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.ResourceLocationWrapper;
import net.minecraft.util.text.Style;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class StyleWrapper extends ElementWrapperBase<Style> {
    public StyleWrapper(@Nonnull Style target) {
        super(target);
    }

    @Export
    @Nullable
    public TextColorWrapper color() {
        return optional(TextColorWrapper::new, getValue().getColor());
    }

    @Export
    @Nullable
    public BooleanWrapper isBold() {
        return optional(getValue().isBold(), false);
    }

    @Export
    @Nullable
    public BooleanWrapper isItalic() {
        return optional(getValue().isItalic(), false);
    }

    @Export
    @Nullable
    public BooleanWrapper isStrikeThrough() {
        return optional(getValue().isStrikethrough(), false);
    }

    @Export
    @Nullable
    public BooleanWrapper isUnderLined() {
        return optional(getValue().isUnderlined(), false);
    }

    @Export
    @Nullable
    public BooleanWrapper isObfuscated() {
        return optional(getValue().isObfuscated(), false);
    }

    @Export
    @Nullable
    public BooleanWrapper isEmpty() {
        return optional(getValue().isEmpty(), false);
    }

    @Export
    @Nullable
    public StringWrapper insertion() {
        return optional(getValue().getInsertion());
    }

    @Export
    @Nullable
    public ResourceLocationWrapper font() {
        return optional(ResourceLocationWrapper::new, getValue().getFont(), null);
    }

    @Export
    @Nullable
    public ClickEventWrapper clickEvent() {
        return optional(ClickEventWrapper::new, getValue().getClickEvent(), null);
    }

    @Export
    @Nullable
    public HoverEventWrapper hoverEvent() {
        return optional(HoverEventWrapper::new, getValue().getHoverEvent(), null);
    }
}
