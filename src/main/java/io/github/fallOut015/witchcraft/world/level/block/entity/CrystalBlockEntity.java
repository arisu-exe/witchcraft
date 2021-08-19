package io.github.fallOut015.witchcraft.world.level.block.entity;

import io.github.fallOut015.witchcraft.world.level.block.BlocksWitchcraft;
import io.github.fallOut015.witchcraft.world.level.block.CrystalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.LinkedList;
import java.util.List;

public class CrystalBlockEntity extends BlockEntity {
    List<BlockPos> crystalPositions;
    int color;
    boolean main;

    public CrystalBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntitiesWitchcraft.CRYSTAL.get(), pos, state);

        this.crystalPositions = new LinkedList<>();
        this.color = 0;
        this.main = false;
    }

    static int MAX_DISTANCE;

    static {
        MAX_DISTANCE = 16;
    }

    public void updateField(boolean destroyed) {
        BlockPos pos;

        for(int x = -MAX_DISTANCE; x < MAX_DISTANCE; ++ x) {
            for(int y = -MAX_DISTANCE; y < MAX_DISTANCE; ++ y) {
                for(int z = -MAX_DISTANCE; z < MAX_DISTANCE; ++ z) {
                    pos = this.worldPosition.offset(x, y, z);
                    if(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= (double) MAX_DISTANCE && this.level.getBlockState(pos).is(BlocksWitchcraft.TURQUOISE.get())) {
                        if(!(destroyed && pos.equals(this.getBlockPos()))) {
                            this.crystalPositions.add(new BlockPos(pos.getX(), pos.getY(), pos.getZ()));
                        }
                    }
                }
            }
        }

        boolean activate = this.crystalPositions.size() > 2;
        if(activate && this.crystalPositions.stream().noneMatch(crystalPosition -> ((CrystalBlockEntity) this.level.getBlockEntity(crystalPosition)).isActivated())) {
            this.main = true; // update on client
        }
        this.crystalPositions.forEach(crystalPosition -> {
            CrystalBlockEntity crystalBlockEntity = (CrystalBlockEntity) this.level.getBlockEntity(crystalPosition);
            crystalBlockEntity.setActivated(activate);
            this.level.setBlockEntity(crystalBlockEntity);
        });
    }

    public boolean isActivated() {
        return CrystalBlock.isActivated(this.getBlockState());
    }
    public void setActivated(boolean activated) {
        CrystalBlock.setActivated(this.getBlockState(), this.level, this.worldPosition, activated);
    }
    public List<BlockPos> getCrystalPositions() {
        return this.crystalPositions;
    }
    public int getColor() {
        return this.color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public boolean isMain() {
        return this.main;
    }
}