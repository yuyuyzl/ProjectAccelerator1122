package com.yuyuyzl.Accelerator.tile;

import com.yuyuyzl.Accelerator.AcceleratorMod;
import ic2.core.IC2;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fluids.capability.TileFluidHandler;

public class TileAccFluid extends TileFluidHandler implements ITickable {

    public TileAccFluid(){
        super();
        tank=new FluidTank(1);
        tank.setCanFill(false);
    }

    @Override
    public void update() {

        //tank.fillInternal(FluidRegistry.getFluidStack("ic2uu_matter", 1),true);
        //AcceleratorMod.logger.info(tank.getFluidAmount()+"");
    }

    public int fillUUMInternal(boolean doFill){
        return tank.fillInternal(FluidRegistry.getFluidStack("ic2uu_matter", 1),doFill);
    }


}