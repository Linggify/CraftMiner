package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.potion;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.text.TextFormattingWrapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.potion.EffectType;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;

public class EffectTypeWrapper extends ElementWrapperBase<EffectType> {
    public EffectTypeWrapper(@Nonnull EffectType target) {
        super(target);
    }

    @Export
    @Nullable
    public TextFormattingWrapper tooltipFormatting() {
        return optional((Function<TextFormatting, TextFormattingWrapper>) TextFormattingWrapper::new, getValue().getTooltipFormatting());
    }

    @Export
    @Nonnull
    public StringWrapper name() {
        return new StringWrapper(getValue().name());
    }
}
