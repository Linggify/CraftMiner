package com.github.linggify.minecraft.craftminer.javascript.wrappers.set;

import com.github.linggify.minecraft.craftminer.javascript.UnsafePredicate;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class ElementSetWrapperBase<T extends IElementWrapper<?>> implements IElementSetWrapper<T> {

    private final Function<Set<T>, ? extends IElementSetWrapper<T>> m_factory;
    private final Set<T> m_values;

    protected JsonObject m_dumpRoot;

    public ElementSetWrapperBase(Function<Set<T>, ? extends IElementSetWrapper<T>> factory, Set<T> values) {
        m_factory = factory;
        m_values = values;

        m_dumpRoot = null;
    }

    private JsonObject applyMap(T element, ScriptObjectMirror map) {
        JsonObject result = new JsonObject();
        for (String key : map.keySet()) {
            ScriptObjectMirror subMap = (ScriptObjectMirror) map.get(key);

            //use functions to obtain values for the output object
            if (subMap.isFunction()) {
                Object value = subMap.call(map, element);
                if (value instanceof IElementWrapper) {
                    result.add(key, ((IElementWrapper<?>) value).getJsonValue());
                } else if (value instanceof Number) {
                    result.add(key, new JsonPrimitive((Number) value));
                } else if (value instanceof Boolean) {
                    result.add(key, new JsonPrimitive((Boolean) value));
                } else if (value instanceof Character) {
                    result.add(key, new JsonPrimitive((Character) value));
                } else if (value instanceof String) {
                    result.add(key, new JsonPrimitive((String) value));
                } else {
                    throw new IllegalArgumentException("Incompatible type for dumping " + value.getClass().toString());
                }
            } else {
                result.add(key, applyMap(element, subMap));
            }
        }

        return result;
    }

    @Override
    public void setDumpOutput(JsonObject root) {
        m_dumpRoot = root;
    }

    @Override
    public void dump(String name, ScriptObjectMirror map) {
        //apply the map to each element in this wrapper
        JsonArray elements = new JsonArray();
        for (T element : m_values) {
            if (map != null) {
                elements.add(applyMap(element, map));
            } else {
                elements.add(element.getJsonValue());
            }
        }

        m_dumpRoot.add(name, elements);
    }

    @Override
    public Set<T> getValues() {
        return ImmutableSet.copyOf(m_values);
    }

    @Override
    public boolean contains(T element) {
        return m_values.contains(element);
    }

    @Override
    public IElementSetWrapper<T> filter(UnsafePredicate<T> condition) {
        Set<T> result = new HashSet<>();
        for (T element : m_values) {
            if (condition.test(element)) {
                result.add(element);
            }
        }

        IElementSetWrapper<T> resultWrapper =  m_factory.apply(result);
        resultWrapper.setDumpOutput(m_dumpRoot);

        return resultWrapper;
    }

    @Override
    public IElementSetWrapper<T> add(IElementSetWrapper<T> other) {
        Set<T> result = new HashSet<>(m_values);
        result.addAll(other.getValues());

        IElementSetWrapper<T> resultWrapper =  m_factory.apply(result);
        resultWrapper.setDumpOutput(m_dumpRoot);

        return resultWrapper;
    }

    @Override
    public IElementSetWrapper<T> minus(IElementSetWrapper<T> other) {
        return filter(e -> !other.contains(e));
    }

    @Override
    public IElementSetWrapper<T> intersect(IElementSetWrapper<T> other) {
        return filter(other::contains);
    }

    @Override
    public IElementSetWrapper<T> inverseIntersect(IElementSetWrapper<T> other) {
        Set<T> result = new HashSet<>();
        for (T element : m_values) {
            if (contains(element) != other.contains(element)) {
                result.add(element);
            }
        }

        for (T element : other.getValues()) {
            if (contains(element) != other.contains(element)) {
                result.add(element);
            }
        }

        IElementSetWrapper<T> resultWrapper =  m_factory.apply(result);
        resultWrapper.setDumpOutput(m_dumpRoot);

        return resultWrapper;
    }
}
