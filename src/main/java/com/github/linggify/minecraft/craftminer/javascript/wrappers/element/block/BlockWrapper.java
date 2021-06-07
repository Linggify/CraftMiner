package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block;

import com.github.linggify.minecraft.craftminer.javascript.moc.MocWorldReader;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ExceptionWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.IElementWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ListWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.item.ItemWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.BooleanWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.NumberWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.ResourceLocationWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.ToolTypeWrapper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
        ITag<Block> realTag = BlockTags.getAllTags().getTag(tag);

        if (realTag == null) {
            throw new IllegalArgumentException(tag + " is not a valid tag");
        }

        return realTag.contains(getValue());
    }

    /**
     * Checks whether there is a property with exactly the given name in the default block state of the wrapped block
     * @param name
     * @return
     */
    public boolean hasProperty(String name) {
        return getValue().defaultBlockState().getProperties().stream().anyMatch(property -> property.getName().equals(name));
    }

    public ItemWrapper getItem() {
        return new ItemWrapper(getValue().asItem());
    }

    @Export
    @Nullable
    public ResourceLocationWrapper registryName() {
        return optional(ResourceLocationWrapper::new, getValue().getRegistryName());
    }

    @Export
    @Nonnull
    public IElementWrapper<?> harvestLevel() {
        return new NumberWrapper(getValue().defaultBlockState().getHarvestLevel());
    }

    @Export
    @Nullable
    public IElementWrapper<?> harvestTool() {
        return optional(ToolTypeWrapper::new, getValue().defaultBlockState().getHarvestTool());
    }

    @Export
    @Nonnull
    public IElementWrapper<?> isRandomlyTicking() {
        return new BooleanWrapper(getValue().defaultBlockState().isRandomlyTicking());
    }

    @Export
    @Nonnull
    public IElementWrapper<?> canOcclude() {
        return new BooleanWrapper(getValue().defaultBlockState().canOcclude());
    }

    @Export
    @Nonnull
    public IElementWrapper<?> requiresCorrectToolForDrops() {
        return new BooleanWrapper(getValue().defaultBlockState().requiresCorrectToolForDrops());
    }

    @Export
    @Nonnull
    public IElementWrapper<?> lootTable() {
        return new ResourceLocationWrapper(getValue().getLootTable());
    }

    @Export
    @Nonnull
    public IElementWrapper<?> hasDynamicShape() {
        return new BooleanWrapper(getValue().hasDynamicShape());
    }

    @Export
    @Nonnull
    public IElementWrapper<?> hasLargeShape() {
        return new BooleanWrapper(getValue().defaultBlockState().hasLargeCollisionShape());
    }

    @Export
    @Nonnull
    public IElementWrapper<?> isAir() {
        return new BooleanWrapper(getValue().defaultBlockState().isAir());
    }

    @Export
    @Nonnull
    public IElementWrapper<?> destroySpeed() {
        MocWorldReader world = new MocWorldReader();
        BlockPos pos = new BlockPos(0, 0, 0);
        world.putBlockState(pos, getValue().defaultBlockState());

        return new NumberWrapper(getValue().defaultBlockState().getDestroySpeed(world, pos));
    }

    @Export
    @Nonnull
    public IElementWrapper<?> explosionResistance() {
        MocWorldReader world = new MocWorldReader();
        BlockPos pos = new BlockPos(0, 0, 0);
        world.putBlockState(pos, getValue().defaultBlockState());

        Explosion explosion = Minecraft.getInstance().level.explode(null, 0, 0, 0, 1, Explosion.Mode.NONE);

        return new NumberWrapper(getValue().defaultBlockState().getExplosionResistance(world, pos, explosion));
    }

    @Export
    @Nonnull
    public IElementWrapper<?> friction() {
        return new NumberWrapper(getValue().getFriction());
    }

    @Export
    @Nonnull
    public IElementWrapper<?> speedFactor() {
        return new NumberWrapper(getValue().getSpeedFactor());
    }

    @Export
    @Nonnull
    public IElementWrapper<?> jumpFactor() {
        return new NumberWrapper(getValue().getJumpFactor());
    }

    @Export
    @Nonnull
    public IElementWrapper<?> material() {
        return new BlockMaterialWrapper(getValue().defaultBlockState().getMaterial());
    }

    @Export
    @Nullable
    public IElementWrapper<?> soundType() {
        return optional(BlockSoundTypeWrapper::new, getValue().defaultBlockState().getSoundType());
    }

    @SuppressWarnings("unchecked")
    @Export
    @Nonnull
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

    @Export
    @Nonnull
    public IElementWrapper<?> tags() {
        return new ListWrapper<>(getValue().getTags().stream().map(ResourceLocationWrapper::new).collect(Collectors.toList()));
    }

    @Export
    @Nonnull
    public IElementWrapper<?> blockstateProperties() {
        return new ListWrapper<>(getValue().defaultBlockState().getProperties().stream().map(BlockStatePropertyWrapper::new).collect(Collectors.toList()));
    }

    @Export
    @Nonnull
    public IElementWrapper<?> textures() {
        return new BlockModelSummaryWrapper(getValue().defaultBlockState());
    }
}
