package com.yuyuyzl.Accelerator;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = AcceleratorMod.MODID, version = AcceleratorMod.VERSION,name = AcceleratorMod.NAME)
public class AcceleratorMod
{
    public static final String MODID = "acceleratormod";
    public static final String VERSION = "0.01";
    public static final String NAME= "Project Accelerator";
    private static Logger logger;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
    @Mod.Instance(AcceleratorMod.MODID)
    public static AcceleratorMod instance;
}
