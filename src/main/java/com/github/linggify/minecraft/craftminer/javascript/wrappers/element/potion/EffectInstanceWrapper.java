package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.potion;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.BooleanWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.NumberWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import net.minecraft.potion.EffectInstance;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EffectInstanceWrapper extends ElementWrapperBase<EffectInstance> {
    public EffectInstanceWrapper(@Nonnull EffectInstance target) {
        super(target);
    }

    @Export
    @Nullable
    public StringWrapper descriptionId() {
        return optional(getValue().getDescriptionId());
    }

    @Export
    @Nonnull
    public NumberWrapper duration() {
        return new NumberWrapper(getValue().getDuration());
    }

    @Export
    @Nonnull
    public NumberWrapper amplifier() {
        return new NumberWrapper(getValue().getAmplifier());
    }

    @Export
    @Nonnull
    public BooleanWrapper isAmbient() {
        return new BooleanWrapper(getValue().isAmbient());
    }

    @Export
    @Nonnull
    public BooleanWrapper isVisible() {
        return new BooleanWrapper(getValue().isVisible());
    }

    @Export
    @Nonnull
    public BooleanWrapper isNoCounter() {
        return new BooleanWrapper(getValue().isNoCounter());
    }

    @Export
    @Nonnull
    public BooleanWrapper showIcon() {
        return new BooleanWrapper(getValue().showIcon());
    }

    @Export
    @Nullable
    public EffectWrapper effect() {
        return optional(EffectWrapper::new, getValue().getEffect());
    }
}
