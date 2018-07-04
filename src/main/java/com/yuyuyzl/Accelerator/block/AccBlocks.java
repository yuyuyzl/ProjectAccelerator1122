package com.yuyuyzl.Accelerator.block;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by yuyuyzl on 2017/2/27.
 */
public class AccBlocks {


    public static AccMachineBlock accMachineBlock;


    public static void registerBlock(RegistryEvent.Register<Block> event){
        accMachineBlock=new AccMachineBlock();
        event.getRegistry().register(accMachineBlock);
    }

    public static void registerItem(RegistryEvent.Register<Item> event){
        event.getRegistry().register(new ItemBlock(AccBlocks.accMachineBlock).setRegistryName(AccBlocks.accMachineBlock.getRegistryName()));
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        accMachineBlock.initModel();
    }
}
