package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.NumberWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.StringWrapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Field;
import java.util.Objects;

public class BlockWrapper extends ElementWrapperBase<Block> {

    public BlockWrapper(Block target) {
        super(target);
    }

    /**
     * Checks whether the given tag contains the wrapped block
     * @param tag
     * @return
     */
    public boolean isTagged(String tag) {
        return Objects.requireNonNull(BlockTags.getAllTags().getTag(new ResourceLocation(tag.split(":")[0], tag.split(":")[1]))).contains(getValue());
    }

    public BlockPropertiesWrapper properties() throws NoSuchFieldException, IllegalAccessException {
        Field properties = AbstractBlock.class.getDeclaredField("properties");
        boolean isAccessible = properties.isAccessible();
        properties.setAccessible(true);

        AbstractBlock.Properties value = (AbstractBlock.Properties) properties.get(getValue());
        properties.setAccessible(isAccessible);

        return new BlockPropertiesWrapper(value);
    }

    public IElementWrapper<?> harvestLevel() {
        return new NumberWrapper(getValue().defaultBlockState().getHarvestLevel());
    }

    public IElementWrapper<?> harvestTool() {
        return new StringWrapper(getValue().defaultBlockState().getHarvestTool().getName());
    }

    public IElementWrapper<?> destroySpeed() {
        return new NumberWrapper(getValue().defaultBlockState().getDestroySpeed(null, null));
    }

    public IElementWrapper<?> explosionResistance() {
        return new NumberWrapper(getValue().defaultBlockState().getExplosionResistance(null, null, null));
    }

    public IElementWrapper<?> friction() {
        return new NumberWrapper(getValue().getFriction());
    }

    public IElementWrapper<?> speedFactor() {
        return new NumberWrapper(getValue().getSpeedFactor());
    }

    public IElementWrapper<?> jumpFactor() {
        return new NumberWrapper(getValue().getJumpFactor());
    }

    public IElementWrapper<?> material() {
        return new BlockMaterialWrapper(getValue().defaultBlockState().getMaterial());
    }

    @Override
    public JsonElement getJsonValue() {
        return new JsonPrimitive(getValue().getRegistryName().toString());
    }
}
