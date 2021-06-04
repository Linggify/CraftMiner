package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block;

import com.github.linggify.minecraft.craftminer.javascript.moc.MocWorldReader;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ExceptionWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ListWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.BooleanWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.NumberWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.ResourceLocationWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.ToolTypeWrapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraftforge.common.ToolType;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class BlockWrapper extends ElementWrapperBase<Block> {

    public BlockWrapper(Block target) {
        super(target);
    }

    /**
     * Checks whether the given tag contains the wrapped block
     * @param tag
     * @return
     */
    public boolean isTagged(ResourceLocation tag) {
        return Objects.requireNonNull(BlockTags.getAllTags().getTag(tag)).contains(getValue());
    }

    public ResourceLocationWrapper registryName() {
        return new ResourceLocationWrapper(getValue().getRegistryName());
    }

    public IElementWrapper<?> harvestLevel() {
        return new NumberWrapper(getValue().defaultBlockState().getHarvestLevel());
    }

    public IElementWrapper<?> harvestTool() {
        ToolType tool = getValue().defaultBlockState().getHarvestTool();

        return new StringWrapper(tool != null ? tool.getName() : "null");
    }

    public IElementWrapper<?> isRandomlyTicking() {
        return new BooleanWrapper(getValue().defaultBlockState().isRandomlyTicking());
    }

    public IElementWrapper<?> canOcclude() {
        return new BooleanWrapper(getValue().defaultBlockState().canOcclude());
    }

    public IElementWrapper<?> requiresCorrectToolForDrops() {
        return new BooleanWrapper(getValue().defaultBlockState().requiresCorrectToolForDrops());
    }

    public IElementWrapper<?> lootTable() {
        return new ResourceLocationWrapper(getValue().getLootTable());
    }

    public IElementWrapper<?> hasDynamicShape() {
        return new BooleanWrapper(getValue().hasDynamicShape());
    }

    public IElementWrapper<?> hasLargeShape() {
        return new BooleanWrapper(getValue().defaultBlockState().hasLargeCollisionShape());
    }

    public IElementWrapper<?> isAir() {
        return new BooleanWrapper(getValue().defaultBlockState().isAir());
    }

    public IElementWrapper<?> destroySpeed() {
        MocWorldReader world = new MocWorldReader();
        BlockPos pos = new BlockPos(0, 0, 0);
        world.putBlockState(pos, getValue().defaultBlockState());

        return new NumberWrapper(getValue().defaultBlockState().getDestroySpeed(world, pos));
    }

    public IElementWrapper<?> explosionResistance() {
        MocWorldReader world = new MocWorldReader();
        BlockPos pos = new BlockPos(0, 0, 0);
        world.putBlockState(pos, getValue().defaultBlockState());

        Explosion explosion = Minecraft.getInstance().level.explode(null, 0, 0, 0, 1, Explosion.Mode.NONE);

        return new NumberWrapper(getValue().defaultBlockState().getExplosionResistance(world, pos, explosion));
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

    public IElementWrapper<?> soundType() {
        return new BlockSoundTypeWrapper(getValue().defaultBlockState().getSoundType());
    }

    @SuppressWarnings("unchecked")
    public IElementWrapper<?> effectiveTools() {
        Map<String, ToolType> toolTypes = null;

        try {
            Field typesField = ToolType.class.getDeclaredField("VALUES");
            boolean accessible = typesField.isAccessible();
            typesField.setAccessible(true);
            toolTypes = (Map<String, ToolType>) typesField.get(null);
            typesField.setAccessible(accessible);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return new ExceptionWrapper(e);
        }

        List<ToolType> effectiveTools = new ArrayList<>();
        for (ToolType tool : toolTypes.values()) {
            if (getValue().defaultBlockState().isToolEffective(tool)) {
                effectiveTools.add(tool);
            }
        }

        return new ListWrapper<>(effectiveTools.stream().map(ToolTypeWrapper::new).collect(Collectors.toList()));
    }

    @Override
    public JsonElement getJsonValue() {
        JsonObject result = new JsonObject();

        result.add("registryName", registryName().getJsonValue());
        result.add("harvestLevel", harvestLevel().getJsonValue());
        result.add("harvestTool", harvestTool().getJsonValue());
        result.add("isRandomlyTicking", isRandomlyTicking().getJsonValue());
        result.add("canOcclude", canOcclude().getJsonValue());
        result.add("requiresCorrectToolForDrops", requiresCorrectToolForDrops().getJsonValue());
        result.add("lootTable", lootTable().getJsonValue());
        result.add("hasDynamicShape", hasDynamicShape().getJsonValue());
        result.add("hasLargeShape", hasLargeShape().getJsonValue());
        result.add("isAir", isAir().getJsonValue());
        result.add("destroySpeed", destroySpeed().getJsonValue());
        result.add("explosionResistance", explosionResistance().getJsonValue());
        result.add("friction", friction().getJsonValue());
        result.add("speedFactor", speedFactor().getJsonValue());
        result.add("jumpFactor", jumpFactor().getJsonValue());
        result.add("material", material().getJsonValue());
        result.add("soundType", soundType().getJsonValue());
        result.add("effectiveTools", effectiveTools().getJsonValue());

        return result;
    }
}
