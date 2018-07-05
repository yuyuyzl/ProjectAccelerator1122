package com.yuyuyzl.Accelerator.block;


import com.yuyuyzl.Accelerator.AcceleratorMod;
import com.yuyuyzl.Accelerator.tile.TileAccCore;
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
    public static AccHullBlock accHullBlock;
    public static AccTimeBlock accTimeBlock;
    public static AccTunnelBlock accTunnelBlock;

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
        accHullBlock=new AccHullBlock();
        accTimeBlock=new AccTimeBlock();
        accTunnelBlock=new AccTunnelBlock();
    }

    public static void registerBlock(RegistryEvent.Register<Block> event){

        initRegistry();

        for (Block b:toRegister) {
            event.getRegistry().register(b);
        }
        GameRegistry.registerTileEntity(TileAccCore.class, AcceleratorMod.MODID + "_tileacccore");
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
