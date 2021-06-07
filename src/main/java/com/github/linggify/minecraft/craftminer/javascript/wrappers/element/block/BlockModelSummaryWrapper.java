package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block;

import com.github.linggify.minecraft.craftminer.javascript.BlockStateUtil;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ListWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.ResourceLocationWrapper;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.data.EmptyModelData;

import javax.annotation.Nonnull;
import java.util.*;

public class BlockModelSummaryWrapper extends ElementWrapperBase<Map<Direction, Set<ResourceLocationWrapper>>> {

    @Nonnull
    private static Map<Direction, Set<ResourceLocationWrapper>> readTextures(BlockState state) {
        Map<Direction, Set<ResourceLocationWrapper>> result = Maps.newHashMap();
        for (Direction direction : Direction.values()) {
            result.put(direction, Sets.newHashSet());
        }

        //collect textures with every substate
        BlockStateUtil.withGenSubStates(state, blockState -> {
            IBakedModel model = Minecraft.getInstance().getBlockRenderer().getBlockModel(blockState);

            for (Direction direction : Direction.values()) {
                Random random = new Random();
                random.setSeed(42L);

                //collect textures for the current model
                for (BakedQuad quad : model.getQuads(blockState, direction, random, EmptyModelData.INSTANCE)) {
                    result.get(direction).add(new ResourceLocationWrapper(quad.getSprite().getName()));
                }
            }
        });

        return result;
    }

    public BlockModelSummaryWrapper(@Nonnull BlockState target) {
        super(readTextures(target));
    }

    @Export
    @Nonnull
    public ListWrapper<ResourceLocationWrapper> north() {
        return new ListWrapper<>(new ArrayList<>(getValue().get(Direction.NORTH)));
    }

    @Export
    @Nonnull
    public ListWrapper<ResourceLocationWrapper> south() {
        return new ListWrapper<>(new ArrayList<>(getValue().get(Direction.SOUTH)));
    }

    @Export
    @Nonnull
    public ListWrapper<ResourceLocationWrapper> east() {
        return new ListWrapper<>(new ArrayList<>(getValue().get(Direction.EAST)));
    }

    @Export
    @Nonnull
    public ListWrapper<ResourceLocationWrapper> west() {
        return new ListWrapper<>(new ArrayList<>(getValue().get(Direction.WEST)));
    }
}
