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
    public static final String VERSION = "0.02";
    public static final String NAME= "Project Accelerator";
    public static Logger logger;

    @SidedProxy(clientSide = "com.yuyuyzl.Accelerator.ClientProxy", serverSide = "com.yuyuyzl.Accelerator.CommonProxy")
    public static CommonProxy proxy;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
    @Mod.Instance(AcceleratorMod.MODID)
    public static AcceleratorMod instance;
}
