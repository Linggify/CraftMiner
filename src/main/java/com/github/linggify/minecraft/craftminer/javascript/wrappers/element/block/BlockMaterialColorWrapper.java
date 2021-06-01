package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.material.MaterialColor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class BlockMaterialColorWrapper extends ElementWrapperBase<MaterialColor> {

    public BlockMaterialColorWrapper(MaterialColor target) {
        super(target);
    }

    public NumberWrapper color() {
        return new NumberWrapper(getValue().col);
    }

    public NumberWrapper id() {
        return new NumberWrapper(getValue().id);
    }

    public IElementWrapper<?> name() {
        try {
            //use the name of the fields as name
            for (Field f : MaterialColor.class.getDeclaredFields()) {
                if (f.getType() == MaterialColor.class && Modifier.isStatic(f.getModifiers())) {
                    MaterialColor other = (MaterialColor) f.get(null);

                    if (other.col == getValue().col && other.id == getValue().id) {
                        return new StringWrapper(f.getName());
                    }
                }
            }

            return new StringWrapper("unknown");
        } catch (IllegalAccessException e) {
            return new ExceptionWrapper(e);
        }
    }

    public StringWrapper hexColor() {
        return new StringWrapper("#" + Integer.toHexString(getValue().col));
    }

    public MapWrapper rgbColor() {
        int red = getValue().col >> 16 & 0xff;
        int green = getValue().col >> 8 & 0xff;
        int blue = getValue().col & 0xff;

        Map<String, NumberWrapper> color = new HashMap<>();
        color.put("red", new NumberWrapper(red));
        color.put("green", new NumberWrapper(green));
        color.put("blue", new NumberWrapper(blue));

        return new MapWrapper(color);
    }

    @Override
    public JsonElement getJsonValue() {
        JsonObject result = new JsonObject();

        result.add("name", name().getJsonValue());
        result.add("id", id().getJsonValue());
        result.add("color", color().getJsonValue());
        result.add("hexColor", hexColor().getJsonValue());
        result.add("rgbColor", rgbColor().getJsonValue());

        return result;
    }
}
