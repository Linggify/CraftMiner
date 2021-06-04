package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.EnumWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.NumberWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import net.minecraft.entity.ai.attributes.AttributeModifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AttributeModifierWrapper extends ElementWrapperBase<AttributeModifier> {
    public AttributeModifierWrapper(@Nonnull AttributeModifier target) {
        super(target);
    }

    @Export
    @Nullable
    public UUIDWrapper id() {
        return optional(UUIDWrapper::new, getValue().getId());
    }

    @Export
    @Nonnull
    public StringWrapper name() {
        return new StringWrapper(getValue().getName());
    }

    @Export
    @Nullable
    public EnumWrapper<AttributeModifier.Operation> operation() {
        return optional(getValue().getOperation());
    }

    @Export
    @Nonnull
    public NumberWrapper amount() {
        return new NumberWrapper(getValue().getAmount());
    }
}
