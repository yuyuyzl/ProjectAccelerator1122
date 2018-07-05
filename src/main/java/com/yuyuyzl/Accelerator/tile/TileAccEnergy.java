package com.yuyuyzl.Accelerator.tile;

import com.yuyuyzl.Accelerator.AcceleratorMod;
import ic2.api.energy.prefab.BasicSink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;


public class TileAccEnergy extends TileEntity implements ITickable {

    private BasicSink ic2EnergySink = new BasicSink(this, Integer.MAX_VALUE, 5);

    @Override
    public void invalidate() {
        ic2EnergySink.invalidate(); // notify the energy sink
        super.invalidate(); // this is important for mc!
    }

    @Override
    public void onChunkUnload() {
        ic2EnergySink.onChunkUnload(); // notify the energy sink

    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        ic2EnergySink.readFromNBT(tag);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        ic2EnergySink.writeToNBT(tag);

        return tag;
    }

    @Override
    public void update() {
        //ic2EnergySink.setEnergyStored(0);
        ic2EnergySink.update(); // notify the energy sink
        //if(!world.isRemote)AcceleratorMod.logger.info(ic2EnergySink.getEnergyStored()+"");

    }

    public double getAllEnergy(){
        double t=ic2EnergySink.getEnergyStored();
        ic2EnergySink.setEnergyStored(0);
        return t;
    }
}

