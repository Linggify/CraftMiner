package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.BooleanWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.EnumWrapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;

public class BlockMaterialWrapper extends ElementWrapperBase<Material> {
    public BlockMaterialWrapper(Material target) {
        super(target);
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
