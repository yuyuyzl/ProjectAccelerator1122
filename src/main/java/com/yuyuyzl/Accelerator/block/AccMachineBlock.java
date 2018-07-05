package com.yuyuyzl.Accelerator.block;

import com.yuyuyzl.Accelerator.AcceleratorMod;
import com.yuyuyzl.Accelerator.creativetabs.AccCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.yuyuyzl.Accelerator.AcceleratorMod.MODID;

/**
 * Created by yuyuyzl on 2017/2/26.
 */
public class AccMachineBlock extends Block{



    public AccMachineBlock() {
        super(Material.IRON);
        String regName=this.getClass().getSimpleName().toLowerCase();
        setUnlocalizedName(AcceleratorMod.MODID+"."+regName);
        setRegistryName(regName);
        AccBlocks.toRegister.add(this);
        setHardness(2.0F);
        setCreativeTab(AccCreativeTab.accCreativeTab);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }



}
