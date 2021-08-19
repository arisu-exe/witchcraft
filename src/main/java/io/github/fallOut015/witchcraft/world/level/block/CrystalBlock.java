package io.github.fallOut015.witchcraft.world.level.block;

import io.github.fallOut015.witchcraft.world.level.block.entity.CrystalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class CrystalBlock extends BaseEntityBlock {
    private static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

    public CrystalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVATED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }
    @Override
    public void onPlace(BlockState state1, Level level, BlockPos pos, BlockState state2, boolean b) {
        CrystalBlockEntity crystalTileEntity = (CrystalBlockEntity) this.newBlockEntity(pos, state1);
        crystalTileEntity.setColor(this.getColor());
        level.setBlockEntity(crystalTileEntity);
        crystalTileEntity.updateField(false);
    }
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state2, boolean b) {
        ((CrystalBlockEntity) level.getBlockEntity(pos)).updateField(true);
        super.onRemove(state, level, pos, state2, b);
    }
    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CrystalBlockEntity(blockPos, blockState);
    }
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, Random random) {
        // TODO sync between server and client
        if(isActivated(state)) {
            CrystalBlockEntity crystalBlockEntity = (CrystalBlockEntity) level.getBlockEntity(pos);
            for(BlockPos otherCrystalPos : crystalBlockEntity.getCrystalPositions()) {
                double magnitude = Math.pow((otherCrystalPos.getX() - crystalBlockEntity.getBlockPos().getX()), 2) + Math.pow((otherCrystalPos.getY() - crystalBlockEntity.getBlockPos().getY()), 2) + Math.pow((otherCrystalPos.getZ() - crystalBlockEntity.getBlockPos().getZ()), 2);
                for(double j = 0; j < Math.floor(magnitude); ++ j) {
                    double px = Mth.lerp((j / Math.floor(magnitude)), crystalBlockEntity.getBlockPos().getX() + 0.5d, otherCrystalPos.getX() + 0.5d);
                    double py = Mth.lerp((j / Math.floor(magnitude)), crystalBlockEntity.getBlockPos().getY() + 0.5d, otherCrystalPos.getY() + 0.5d);
                    double pz = Mth.lerp((j / Math.floor(magnitude)), crystalBlockEntity.getBlockPos().getZ() + 0.5d, otherCrystalPos.getZ() + 0.5d);
                    crystalBlockEntity.getLevel().addParticle(ParticleTypes.FLAME, px, py, pz, 0, 0, 0);
                    // color particles with getColor
                }
            }
            level.addParticle(ParticleTypes.FLAME, (double) pos.getX() + 0.5d, (double) pos.getY() + 1.0d, (double) pos.getZ() + 0.5d, 0, 0, 0);
        }
    }

    public static void setActivated(BlockState blockState, Level level, BlockPos blockPos, boolean activated) {
        level.setBlockAndUpdate(blockPos, blockState.setValue(ACTIVATED, activated));
    }
    public static boolean isActivated(BlockState blockState) {
        return blockState.getValue(ACTIVATED);
    }

    abstract int getColor();

    public static boolean ifActivated(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return isActivated(blockState);
    }
    public static int getLight(BlockState value) {
        return isActivated(value) ? 6 : 1;
    }
}