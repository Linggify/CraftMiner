package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ExceptionWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.BooleanWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.EnumWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class BlockMaterialWrapper extends ElementWrapperBase<Material> {
    public BlockMaterialWrapper(Material target) {
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

    public BooleanWrapper blocksMotion() {
        return new BooleanWrapper(getValue().blocksMotion());
    }

    public BooleanWrapper isReplaceable() {
        return new BooleanWrapper(getValue().isReplaceable());
    }

    public BooleanWrapper isLiquid() {
        return new BooleanWrapper(getValue().isLiquid());
    }

    public BooleanWrapper isFlammable() {
        return new BooleanWrapper(getValue().isFlammable());
    }

    public BooleanWrapper isSolid() {
        return new BooleanWrapper(getValue().isSolid());
    }

    public BooleanWrapper isSolidBlocking() {
        return new BooleanWrapper(getValue().isSolidBlocking());
    }

    public BlockMaterialColorWrapper color() {
        return new BlockMaterialColorWrapper(getValue().getColor());
    }

    public EnumWrapper<PushReaction> pushReaction() {
        return new EnumWrapper<>(getValue().getPushReaction());
    }

    @Override
    public JsonElement getJsonValue() {
        JsonObject result = new JsonObject();

        result.add("name", name().getJsonValue());
        result.add("blocksMotion", blocksMotion().getJsonValue());
        result.add("isReplaceable", isReplaceable().getJsonValue());
        result.add("isLiquid", isLiquid().getJsonValue());
        result.add("isFlammable", isFlammable().getJsonValue());
        result.add("isSolid", isSolid().getJsonValue());
        result.add("isSolidBlocking", isSolidBlocking().getJsonValue());
        result.add("color", color().getJsonValue());
        result.add("pushReaction", pushReaction().getJsonValue());

        return result;
    }
}
