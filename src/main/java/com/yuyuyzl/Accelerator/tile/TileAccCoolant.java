package com.yuyuyzl.Accelerator.tile;

import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.TileFluidHandler;

public class TileAccCoolant extends TileFluidHandler implements ITickable {

    public TileAccCoolant(){
        super();
        tank=new FluidTank(8000);
        tank.setCanDrain(false);

    }

    public FluidStack drain(int amount){
        return tank.drainInternal(amount,true);
    }

    @Override
    public void update() {

        //tank.fillInternal(FluidRegistry.getFluidStack("ic2uu_matter", 1),true);
        //AcceleratorMod.logger.info(tank.getFluidAmount()+"");
    }


}
