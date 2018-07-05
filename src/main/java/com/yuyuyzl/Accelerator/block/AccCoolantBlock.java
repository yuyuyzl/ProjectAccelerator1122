package com.yuyuyzl.Accelerator.block;

import com.yuyuyzl.Accelerator.tile.TileAccCoolant;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AccCoolantBlock extends AccMachineBlock implements ITileEntityProvider {
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileAccCoolant();
    }
}
