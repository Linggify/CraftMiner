package com.github.linggify.minecraft.craftminer.javascript.wrappers.element;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.NotImplementedException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class ElementWrapperBase<T> implements IElementWrapper<T> {

    public static final Supplier<? extends IElementWrapper<?>> nullSupplier = () -> null;

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Export {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Default {
        String[] target();
    }

    @Nonnull
    private final T m_value;

    public ElementWrapperBase(@Nonnull T target) {
        m_value = target;
    }

    @Nonnull
    protected T getValue() {
        return m_value;
    }

    @Nullable
    protected NumberWrapper optional(@Nullable Number value) {
        return optional(value, null);
    }

    @Nullable
    protected <S extends Enum<?>> EnumWrapper<S> optional(@Nullable S value) {
        return optional(EnumWrapper::new, value, null);
    }

    @Nullable
    protected <S extends Enum<?>> EnumWrapper<S> optional(@Nullable S value, @Nullable S default0) {
        return optional(EnumWrapper::new, value, default0);
    }

    @Nullable
    protected NumberWrapper optional(@Nullable Number value, @Nullable Number default0) {
        return optional(NumberWrapper::new, value, default0);
    }

    @Nullable
    protected CharacterWrapper optional(@Nullable Character value) {
        return optional(value, null);
    }

    @Nullable
    protected CharacterWrapper optional(@Nullable Character value, @Nullable Character default0) {
        return optional(CharacterWrapper::new, value, default0);
    }

    @Nullable
    protected StringWrapper optional(@Nullable String value) {
        return optional(value, null);
    }

    @Nullable
    protected StringWrapper optional(@Nullable String value, @Nullable String default0) {
        return optional(StringWrapper::new, value, default0);
    }

    @Nullable
    protected BooleanWrapper optional(@Nullable Boolean value) {
        return optional(value, null);
    }

    @Nullable
    protected BooleanWrapper optional(@Nullable Boolean value, @Nullable Boolean default0) {
        return optional(BooleanWrapper::new, value, default0);
    }

    @Nullable
    protected <S, U extends IElementWrapper<? super S>> U optional(@Nonnull Function<S, U> factory, @Nullable S value) {
        return optional(factory, value, null);
    }

    protected <S, U extends IElementWrapper<? super S>> U optional(@Nonnull Function<S, U> factory, @Nullable S value, @Nullable S default0) {
        return value != null ? factory.apply(value) : (default0 != null ? factory.apply(default0) : null);
    }

    @Nullable
    protected JsonElement optionalMember(@Nonnull Supplier<IElementWrapper<?>> supplier) {
        return optionalMember(supplier, null);
    }

    @Nullable
    protected JsonElement optionalMember(@Nonnull Supplier<IElementWrapper<?>> supplier, @Nullable JsonElement default0) {
        IElementWrapper<?> wrapper = supplier.get();
        return wrapper != null ? wrapper.getJsonValue() : default0;
    }

    @Override
    public JsonElement getJsonValue() {
        Class<?> wrapperType = this.getClass();

        //collect members fit for exporting
        List<Method> methods = new ArrayList<>();
        Map<String, Method> defaults = new HashMap<>();

        for (Method method : wrapperType.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Export.class)) {

                if (method.isAnnotationPresent(Default.class)) {
                    throw new IllegalArgumentException("Methods marked with @Export may not be marked as @Default");
                }

                if (method.getParameterTypes().length > 0) {
                    throw new NotImplementedException("Cannot infer parameters for json values");
                }

                if (!IElementWrapper.class.isAssignableFrom(method.getReturnType())) {
                    throw new NotImplementedException("Can only convert IElementWrapper<?> to json values");
                }

                methods.add(method);
            } else if (method.isAnnotationPresent(Default.class)) {

                if (!method.isAnnotationPresent(Nonnull.class)) {
                    throw new NotImplementedException("Only @Nonnull marked methods are permitted as defaults");
                }

                if (method.getParameterTypes().length > 0) {
                    throw new NotImplementedException("Cannot infer parameters for defaults");
                }

                if (!JsonElement.class.isAssignableFrom(method.getReturnType())) {
                    throw new NotImplementedException("Only JsonElements are allowed as defaults");
                }

                Default annotation = method.getAnnotation(Default.class);
                for (String target : annotation.target()) {
                    defaults.put(target, method);
                }
            }
        }

        //create JsonObject
        JsonObject result = new JsonObject();

        for (Method method : methods) {
            JsonElement element;

            if (method.isAnnotationPresent(Nonnull.class)) {
                element = optionalMember(() -> {
                    try {
                        return (IElementWrapper<?>) method.invoke(this);
                    } catch (Exception e) {
                        return new ExceptionWrapper(e);
                    }
                });
            } else  {
                JsonElement default0 = null;

                if (defaults.containsKey(method.getName())) {
                    try {
                        defaults.get(method.getName()).invoke(this);
                    } catch (Exception e) {
                        default0 = new ExceptionWrapper(e).getJsonValue();
                    }
                }

                element = optionalMember(() -> {
                    try {
                        return (IElementWrapper<?>) method.invoke(this);
                    } catch (Exception e) {
                        return new ExceptionWrapper(e);
                    }
                }, default0);
            }

            result.add(method.getName(), element);
        }

        return result;
    }

    @Override
    public int hashCode() {
        //use the values hashcode for more predictable behaviour
        return m_value.hashCode();
    }
}
