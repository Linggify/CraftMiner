package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.MapWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.NumberWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class ColorWrapper extends ElementWrapperBase<Integer> {
    public ColorWrapper(@Nonnull Integer target) {
        super(target);
    }

    @Export
    @Nonnull
    public NumberWrapper raw() {
        return new NumberWrapper(getValue());
    }

    @Export
    @Nonnull
    public StringWrapper hex() {
        return new StringWrapper("#" + Integer.toHexString(getValue()));
    }

    @Export
    @Nonnull
    public MapWrapper rgb() {
        int red = getValue() >> 16 & 0xff;
        int green = getValue() >> 8 & 0xff;
        int blue = getValue() & 0xff;

        Map<String, NumberWrapper> color = new HashMap<>();
        color.put("red", new NumberWrapper(red));
        color.put("green", new NumberWrapper(green));
        color.put("blue", new NumberWrapper(blue));

        return new MapWrapper(color);
    }
}
