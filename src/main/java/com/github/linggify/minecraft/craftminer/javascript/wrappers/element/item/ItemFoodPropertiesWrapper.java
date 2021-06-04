package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.item;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ListWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.MapWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.potion.EffectInstanceWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.BooleanWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.NumberWrapper;
import net.minecraft.item.Food;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemFoodPropertiesWrapper extends ElementWrapperBase<Food> {

    public ItemFoodPropertiesWrapper(@Nonnull Food target) {
        super(target);
    }

    @Export
    @Nonnull
    public BooleanWrapper canAlwaysEat() {
        return new BooleanWrapper(getValue().canAlwaysEat());
    }

    @Export
    @Nonnull
    public BooleanWrapper isFastFood() {
        return new BooleanWrapper(getValue().isFastFood());
    }

    @Export
    @Nonnull
    public BooleanWrapper isMeat() {
        return new BooleanWrapper(getValue().isMeat());
    }

    @Export
    @Nonnull
    public NumberWrapper nutrition() {
        return new NumberWrapper(getValue().getNutrition());
    }

    @Export
    @Nonnull
    public NumberWrapper saturationModifier() {
        return new NumberWrapper(getValue().getSaturationModifier());
    }

    @Export
    @Nonnull
    public ListWrapper<MapWrapper> effects() {
        return new ListWrapper<>(getValue().getEffects().stream().map(pair -> {
            Map<String, IElementWrapper<?>> map = new HashMap<>();
            map.put("effect", new EffectInstanceWrapper(pair.getFirst()));
            map.put("duration", new NumberWrapper(pair.getSecond()));
            return new MapWrapper(map);
        }).collect(Collectors.toList()));
    }
}
