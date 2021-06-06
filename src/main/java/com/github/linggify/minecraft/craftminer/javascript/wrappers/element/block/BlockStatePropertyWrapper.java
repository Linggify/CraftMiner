package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ListWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import net.minecraft.state.Property;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class BlockStatePropertyWrapper extends ElementWrapperBase<Property<?>> {

    private static final HashMap<Class<?>, Function<Object, IElementWrapper<?>>> VALUE_MAPPERS = new HashMap<>();

    public static void setMapper(Class<?> type, Function<Object, IElementWrapper<?>> map) {
        VALUE_MAPPERS.put(type, map);
    }

    public BlockStatePropertyWrapper(@Nonnull Property<?> target) {
        super(target);
    }

    @Export
    @Nonnull
    public StringWrapper name() {
        return new StringWrapper(getValue().getName());
    }

    @Export
    @Nonnull
    public StringWrapper valueClass() {
        return new StringWrapper(getValue().getValueClass().getCanonicalName());
    }

    @Export
    @Nonnull
    public ListWrapper<IElementWrapper<?>> values() {
        List<IElementWrapper<?>> values = new ArrayList<>();
        Function<Object, IElementWrapper<?>> mapper = VALUE_MAPPERS.containsKey(getValue().getValueClass()) ? VALUE_MAPPERS.get(getValue().getValueClass()) : value -> new StringWrapper(value.toString());

        for (Object o : getValue().getPossibleValues()) {
            values.add(mapper.apply(o));
        }

        return new ListWrapper<>(values);
    }
}
