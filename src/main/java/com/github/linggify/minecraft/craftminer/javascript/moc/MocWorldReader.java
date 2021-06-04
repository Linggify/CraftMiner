package com.github.linggify.minecraft.craftminer.javascript.moc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FluidState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.DimensionType;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.lighting.WorldLightManager;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class MocWorldReader implements IWorldReader {

    private HashMap<BlockPos, BlockState> m_states;

    public MocWorldReader() {
        m_states = new HashMap<>();
    }

    public void putBlockState(BlockPos pos, BlockState state) {
        m_states.put(pos, state);
    }

    @Nullable
    @Override
    public IChunk getChunk(int p_217353_1_, int p_217353_2_, ChunkStatus p_217353_3_, boolean p_217353_4_) {
        //TODO: moc chunk
        return null;
    }

    @Override
    public boolean hasChunk(int p_217354_1_, int p_217354_2_) {
        return false;
    }

    @Override
    public int getHeight(Heightmap.Type p_201676_1_, int p_201676_2_, int p_201676_3_) {
        return 256;
    }

    @Override
    public int getSkyDarken() {
        return 0;
    }

    @Override
    public BiomeManager getBiomeManager() {
        //TODO: moc biome manager
        return null;
    }

    @Override
    public Biome getUncachedNoiseBiome(int p_225604_1_, int p_225604_2_, int p_225604_3_) {
        //TODO moc biome
        return null;
    }

    @Override
    public boolean isClientSide() {
        //this is a client mod first and foremost.
        return true;
    }

    @Override
    public int getSeaLevel() {
        return 70;
    }

    @Override
    public DimensionType dimensionType() {
        //TODO moc default dimension
        return null;
    }

    @Override
    public float getShade(Direction p_230487_1_, boolean p_230487_2_) {
        return 0;
    }

    @Override
    public WorldLightManager getLightEngine() {
        return new WorldLightManager(null, false, false);
    }

    @Override
    public WorldBorder getWorldBorder() {
        return new WorldBorder();
    }

    @Override
    public Stream<VoxelShape> getEntityCollisions(@Nullable Entity p_230318_1_, AxisAlignedBB p_230318_2_, Predicate<Entity> p_230318_3_) {
        return Arrays.stream(new VoxelShape[]{VoxelShapes.empty()});
    }

    @Nullable
    @Override
    public TileEntity getBlockEntity(BlockPos p_175625_1_) {
        return null;
    }

    @Override
    public BlockState getBlockState(BlockPos p_180495_1_) {
        BlockState state = Blocks.AIR.defaultBlockState();
        if (m_states.containsKey(p_180495_1_)) {
            state = m_states.get(p_180495_1_);
        }
        return state;
    }

    @Override
    public FluidState getFluidState(BlockPos p_204610_1_) {
        return getBlockState(p_204610_1_).getFluidState();
    }
}
