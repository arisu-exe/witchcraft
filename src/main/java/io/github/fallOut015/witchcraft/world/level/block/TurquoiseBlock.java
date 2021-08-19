package io.github.fallOut015.witchcraft.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TurquoiseBlock extends CrystalBlock {
    private static final VoxelShape SHAPE = Block.box(1d, 0, 1d, 15d, 15d, 15d);

    public TurquoiseBlock(Properties properties) {
        super(properties);
    }

    @Override
    int getColor() {
        return DyeColor.CYAN.getTextColor();
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }
}