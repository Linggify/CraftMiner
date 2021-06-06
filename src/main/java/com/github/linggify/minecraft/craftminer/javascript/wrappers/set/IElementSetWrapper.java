package com.github.linggify.minecraft.craftminer.javascript.wrappers.set;

import com.github.linggify.minecraft.craftminer.javascript.UnsafePredicate;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.google.gson.JsonObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.util.Set;

/**
 * Wraps a registry of game elements
 * @param <T>
 */
public interface IElementSetWrapper<T extends IElementWrapper<?>> {

    /**
     * Sets the {@link JsonObject} to which to dump information
     * @param root
     */
    void setDumpOutput(JsonObject root);

    /**
     * Dumps the elements of this {@link IElementSetWrapper} to the {@link JsonObject} specified earlier
     * @param map a mapping which is used to convert the entries to a json value
     */
    void dump(String name, ScriptObjectMirror map);

    Set<T> getValues();

    boolean contains(T element);

    /**
     * Filters the entries of this registry and returns a new one only containing the entries satisfying the condition
     * @param condition the condition entries need to satisfy
     * @return a new {@link IElementSetWrapper} containing all entries which satisfy the condition
     */
    IElementSetWrapper<T> filter(UnsafePredicate<T> condition);

    /**
     *
     * @param other
     * @return a new {@link IElementSetWrapper} containing elements from both this and the other {@link IElementSetWrapper}
     */
    IElementSetWrapper<T> add(IElementSetWrapper<T> other);

    /**
     *
     * @param other
     * @return a new {@link IElementSetWrapper} containing only elements which are in this wrapper but not in the given one
     */
    IElementSetWrapper<T> minus(IElementSetWrapper<T> other);

    /**
     * Creates a new {@link IElementSetWrapper} containing only elements from this and the other wrapper
     * @param other
     * @return
     */
    IElementSetWrapper<T> intersect(IElementSetWrapper<T> other);

    /**
     * @param other
     * @return a new {@link IElementSetWrapper} containing only elements which are either in this wrapper or in the given one
     */
    IElementSetWrapper<T> inverseIntersect(IElementSetWrapper<T> other);

    /**
     *
     * @return true if this {@link IElementSetWrapper} contains no element
     */
    boolean isEmpty();

    /**
     *
     * @param condition
     * @return true if any element satisfies the condition (if the wrapper is empty should return false)
     */
    boolean any(UnsafePredicate<T> condition);

    /**
     *
     * @param condition
     * @return true if all elements satisfy the condition (if the wrapper is empty should return true)
     */
    boolean all(UnsafePredicate<T> condition);
}
