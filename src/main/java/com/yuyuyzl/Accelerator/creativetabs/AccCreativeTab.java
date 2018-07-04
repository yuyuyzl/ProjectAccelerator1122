package com.yuyuyzl.Accelerator.creativetabs;

import com.yuyuyzl.Accelerator.block.AccBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by yuyuyzl on 2017/2/27.
 */
public class AccCreativeTab extends CreativeTabs {
    public AccCreativeTab(String label){
        super(label);
    }

    public static AccCreativeTab accCreativeTab;
    public static void init(){
        accCreativeTab=new AccCreativeTab("accelerator");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Item.getItemFromBlock(AccBlocks.accMachineBlock));
    }
}
