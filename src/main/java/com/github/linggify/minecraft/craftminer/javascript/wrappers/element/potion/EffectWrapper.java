package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.potion;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ListWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.MapWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.BooleanWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.AttributeModifierWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.AttributeWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.ColorWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.ResourceLocationWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.text.TextComponentWrapper;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EffectWrapper extends ElementWrapperBase<Effect> {
    public EffectWrapper(@Nonnull Effect target) {
        super(target);
    }

    @Export
    @Nullable
    public ResourceLocationWrapper registryName() {
        return optional(ResourceLocationWrapper::new, getValue().getRegistryName());
    }

    @Export
    @Nullable
    public TextComponentWrapper displayName() {
        return optional(TextComponentWrapper::new, getValue().getDisplayName());
    }

    @Export
    @Nullable
    public StringWrapper descriptionId() {
        return optional(getValue().getDescriptionId());
    }

    @Export
    @Nonnull
    public BooleanWrapper isBeneficial() {
        return new BooleanWrapper(getValue().isBeneficial());
    }

    public BooleanWrapper isInstantenous() {
        return new BooleanWrapper(getValue().isInstantenous());
    }

    @Export
    @Nullable
    public EffectTypeWrapper category() {
        return optional((Function<EffectType, EffectTypeWrapper>) EffectTypeWrapper::new, getValue().getCategory());
    }

    @Export
    @Nonnull
    public ColorWrapper color() {
        return new ColorWrapper(getValue().getColor());
    }

    @Export
    @Nonnull
    public ListWrapper<MapWrapper> attributeModifiers() {
        Map<Attribute, AttributeModifier> modifiers = getValue().getAttributeModifiers();

        return new ListWrapper<>(modifiers.entrySet().stream().map(entry -> {
                    Map<String, IElementWrapper<?>> map = new HashMap<>();
                    map.put("attribute", new AttributeWrapper(entry.getKey()));
                    map.put("modifier", new AttributeModifierWrapper(entry.getValue()));
                    return new MapWrapper(map);
                }).collect(Collectors.toList()));
    }
}
