package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.BooleanWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.NumberWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import net.minecraft.entity.ai.attributes.Attribute;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AttributeWrapper extends ElementWrapperBase<Attribute> {
    public AttributeWrapper(@Nonnull Attribute target) {
        super(target);
    }

    @Export
    @Nullable
    public ResourceLocationWrapper registryName() {
        return optional(ResourceLocationWrapper::new, getValue().getRegistryName());
    }

    @Export
    @Nullable
    public StringWrapper descriptionId() {
        return optional(getValue().getDescriptionId());
    }

    @Export
    @Nonnull
    public NumberWrapper defaultValue() {
        return new NumberWrapper(getValue().getDefaultValue());
    }

    @Export
    @Nonnull
    public BooleanWrapper isClientSyncable() {
        return new BooleanWrapper(getValue().isClientSyncable());
    }
}
