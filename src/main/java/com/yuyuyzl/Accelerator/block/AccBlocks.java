package com.yuyuyzl.Accelerator.block;


import com.yuyuyzl.Accelerator.AcceleratorMod;
import com.yuyuyzl.Accelerator.tile.TileAccCoolant;
import com.yuyuyzl.Accelerator.tile.TileAccCore;
import com.yuyuyzl.Accelerator.tile.TileAccEnergy;
import com.yuyuyzl.Accelerator.tile.TileAccFluid;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

/**
 * Created by yuyuyzl on 2017/2/27.
 */
public class AccBlocks {

    public static ArrayList<Block> toRegister=new ArrayList<>();
    public static AccMachineBlock accMachineBlock;
    public static AccCoreBlock accCoreBlock;
    public static AccAdvMachineBlock accAdvMachineBlock;
    public static AccAdvMachineHull accAdvMachineHull;
    public static AccAdvTunnel accAdvTunnel;
    public static AccBuildGuide accBuildGuide;
    public static AccCoolantBlock accCoolantBlock;
    public static AccEnergyBlock accEnergyBlock;
    public static AccFluidBlock accFluidBlock;
    public static AccTimeBlock accTimeBlock;
    public static AccTunnelBlock accTunnelBlock;
    public static AccMachineHull accMachineHull;

    public static void initRegistry(){
        accMachineBlock=new AccMachineBlock();
        accCoreBlock=new AccCoreBlock();
        accAdvMachineBlock=new AccAdvMachineBlock();
        accAdvMachineHull=new AccAdvMachineHull();
        accAdvTunnel=new AccAdvTunnel();
        accBuildGuide=new AccBuildGuide();
        accCoolantBlock=new AccCoolantBlock();
        accEnergyBlock=new AccEnergyBlock();
        accFluidBlock=new AccFluidBlock();
        accTimeBlock=new AccTimeBlock();
        accTunnelBlock=new AccTunnelBlock();
        accMachineHull=new AccMachineHull();
    }

    public static void registerBlock(RegistryEvent.Register<Block> event){



        for (Block b:toRegister) {
            event.getRegistry().register(b);
        }
        GameRegistry.registerTileEntity(TileAccCore.class, AcceleratorMod.MODID + "_tileacccore");
        GameRegistry.registerTileEntity(TileAccEnergy.class, AcceleratorMod.MODID + "_tileaccenergy");
        GameRegistry.registerTileEntity(TileAccFluid.class, AcceleratorMod.MODID + "_tileaccfluid");
        GameRegistry.registerTileEntity(TileAccCoolant.class, AcceleratorMod.MODID + "_tileacccoolant");
    }

    public static void registerItem(RegistryEvent.Register<Item> event){
        for (Block b:toRegister) {
            event.getRegistry().register(new ItemBlock(b).setRegistryName(b.getRegistryName()));
        }

    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        for (Block b:toRegister) {
            if(b instanceof AccMachineBlock){
                ((AccMachineBlock)b).initModel();
            }
        }
    }
}
