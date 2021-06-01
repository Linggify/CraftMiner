package com.github.linggify.minecraft.craftminer.javascript.wrappers.element;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;

public abstract class ElementWrapperBase<T> implements IElementWrapper<T> {

    private final T m_value;

    public ElementWrapperBase(T target) {
        m_value = target;
    }

    protected T getValue() {
        return m_value;
    }

    @Override
    public int hashCode() {
        //use the values hashcode for more predictable behaviour
        return m_value.hashCode();
    }
}
