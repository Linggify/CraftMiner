package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ExceptionWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.BooleanWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.EnumWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class BlockMaterialWrapper extends ElementWrapperBase<Material> {
    public BlockMaterialWrapper(@Nonnull Material target) {
        super(target);
    }

    private boolean materialsEqual(Material x, Material y) {
        return (x.blocksMotion() == y.blocksMotion())
                && (x.isFlammable() == y.isFlammable())
                && (x.isLiquid() == y.isLiquid())
                && (x.isReplaceable() == y.isReplaceable())
                && (x.isSolid() == y.isSolid())
                && (x.isSolidBlocking() == y.isSolidBlocking())
                && (x.getColor().id == y.getColor().id)
                && (x.getColor().col == y.getColor().col)
                && (x.getPushReaction() == y.getPushReaction());
    }

    @Export
    @Nonnull
    public IElementWrapper<?> name() {
        try {
            //use the name of the fields as name
            for (Field f : Material.class.getDeclaredFields()) {
                if (f.getType() == Material.class && Modifier.isStatic(f.getModifiers())) {
                    Material other = (Material) f.get(null);

                    if (materialsEqual(getValue(), other)) {
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
    public BooleanWrapper blocksMotion() {
        return new BooleanWrapper(getValue().blocksMotion());
    }

    @Export
    @Nonnull
    public BooleanWrapper isReplaceable() {
        return new BooleanWrapper(getValue().isReplaceable());
    }

    @Export
    @Nonnull
    public BooleanWrapper isLiquid() {
        return new BooleanWrapper(getValue().isLiquid());
    }

    @Export
    @Nonnull
    public BooleanWrapper isFlammable() {
        return new BooleanWrapper(getValue().isFlammable());
    }

    @Export
    @Nonnull
    public BooleanWrapper isSolid() {
        return new BooleanWrapper(getValue().isSolid());
    }

    @Export
    @Nonnull
    public BooleanWrapper isSolidBlocking() {
        return new BooleanWrapper(getValue().isSolidBlocking());
    }

    @Export
    @Nullable
    public BlockMaterialColorWrapper color() {
        return optional(BlockMaterialColorWrapper::new, getValue().getColor());
    }

    @Export
    @Nullable
    public EnumWrapper<PushReaction> pushReaction() {
        return optional(getValue().getPushReaction());
    }
}
