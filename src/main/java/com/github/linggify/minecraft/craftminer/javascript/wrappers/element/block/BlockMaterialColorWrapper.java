package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.*;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.NumberWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.ColorWrapper;
import net.minecraft.block.material.MaterialColor;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class BlockMaterialColorWrapper extends ElementWrapperBase<MaterialColor> {

    public BlockMaterialColorWrapper(@Nonnull MaterialColor target) {
        super(target);
    }

    @Export
    @Nonnull
    public NumberWrapper id() {
        return new NumberWrapper(getValue().id);
    }

    @Export
    @Nonnull
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

    @Export
    @Nonnull
    public ColorWrapper color() {
        return new ColorWrapper(getValue().col);
    }
}
