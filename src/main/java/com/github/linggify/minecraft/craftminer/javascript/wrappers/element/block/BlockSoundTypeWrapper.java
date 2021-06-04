package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.SoundEventWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.NumberWrapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.SoundType;

public class BlockSoundTypeWrapper extends ElementWrapperBase<SoundType> {

    public BlockSoundTypeWrapper(SoundType target) {
        super(target);
    }

    public SoundEventWrapper breakSound() {
        return new SoundEventWrapper(getValue().getBreakSound());
    }

    public SoundEventWrapper fallSound() {
        return new SoundEventWrapper(getValue().getFallSound());
    }

    public SoundEventWrapper hitSound() {
        return new SoundEventWrapper(getValue().getHitSound());
    }

    public SoundEventWrapper placeSound() {
        return new SoundEventWrapper(getValue().getPlaceSound());
    }

    public SoundEventWrapper stepSound() {
        return new SoundEventWrapper(getValue().getStepSound());
    }

    public NumberWrapper pitch() {
        return new NumberWrapper(getValue().getPitch());
    }

    public NumberWrapper volume() {
        return new NumberWrapper(getValue().getVolume());
    }

    @Override
    public JsonElement getJsonValue() {
        JsonObject result = new JsonObject();

        result.add("breakSound", breakSound().getJsonValue());
        result.add("fallSound", fallSound().getJsonValue());
        result.add("hitSound", hitSound().getJsonValue());
        result.add("placeSound", placeSound().getJsonValue());
        result.add("stepSound", stepSound().getJsonValue());
        result.add("pitch", pitch().getJsonValue());
        result.add("volume", volume().getJsonValue());

        return result;
    }
}
