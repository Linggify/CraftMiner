package com.github.linggify.minecraft.craftminer.javascript;

@FunctionalInterface
public interface UnsafePredicate<T> {
    boolean test(T value);
}
