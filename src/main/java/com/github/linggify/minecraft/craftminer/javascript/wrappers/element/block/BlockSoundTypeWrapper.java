package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ExceptionWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.SoundEventWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.NumberWrapper;
import net.minecraft.block.SoundType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class BlockSoundTypeWrapper extends ElementWrapperBase<SoundType> {

    public BlockSoundTypeWrapper(@Nonnull SoundType target) {
        super(target);
    }

    private boolean soundTypesEqual(SoundType x, SoundType y) {
        return x.getPitch() == y.getPitch()
                && x.getVolume() == y.getVolume()
                && x.getBreakSound().getLocation().equals(y.getBreakSound().getLocation())
                && x.getHitSound().getLocation().equals(y.getHitSound().getLocation())
                && x.getFallSound().getLocation().equals(y.getFallSound().getLocation())
                && x.getStepSound().getLocation().equals(y.getStepSound().getLocation())
                && x.getPlaceSound().getLocation().equals(y.getPlaceSound().getLocation());
    }

    @Export
    @Nonnull
    public IElementWrapper<?> name() {
        try {
            //use the name of the fields as name
            for (Field f : SoundType.class.getDeclaredFields()) {
                if (f.getType() == SoundType.class && Modifier.isStatic(f.getModifiers())) {
                    SoundType other = (SoundType) f.get(null);

                    if (soundTypesEqual(getValue(), other)) {
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
    @Nullable
    public SoundEventWrapper breakSound() {
        return optional(SoundEventWrapper::new, getValue().getBreakSound());
    }

    @Export
    @Nullable
    public SoundEventWrapper fallSound() {
        return optional(SoundEventWrapper::new, getValue().getFallSound());
    }

    @Export
    @Nullable
    public SoundEventWrapper hitSound() {
        return optional(SoundEventWrapper::new, getValue().getHitSound());
    }

    @Export
    @Nullable
    public SoundEventWrapper placeSound() {
        return optional(SoundEventWrapper::new, getValue().getPlaceSound());
    }

    @Export
    @Nullable
    public SoundEventWrapper stepSound() {
        return optional(SoundEventWrapper::new, getValue().getStepSound());
    }

    @Export
    @Nonnull
    public NumberWrapper pitch() {
        return new NumberWrapper(getValue().getPitch());
    }

    @Export
    @Nonnull
    public NumberWrapper volume() {
        return new NumberWrapper(getValue().getVolume());
    }
}
