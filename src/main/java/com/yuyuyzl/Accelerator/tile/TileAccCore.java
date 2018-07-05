package com.yuyuyzl.Accelerator.tile;

import com.yuyuyzl.Accelerator.Config;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

public class TileAccCore extends TileEntity {

    public int dir;

    public TileAccCore(int dir){
        super();
        this.dir=dir;
    }

    //private List<Integer> HullPosX=new ArrayList<Integer>(),HullPosY=new ArrayList<Integer>(),HullPosZ=new ArrayList<Integer>();
    private List<Integer> EnergyPosX=new ArrayList<Integer>(),EnergyPosY=new ArrayList<Integer>(),EnergyPosZ=new ArrayList<Integer>();
    private List<Integer> FluidPosX=new ArrayList<Integer>(),FluidPosY=new ArrayList<Integer>(),FluidPosZ=new ArrayList<Integer>();
    private List<Integer> TunnelPosX=new ArrayList<Integer>(),TunnelPosY=new ArrayList<Integer>(),TunnelPosZ=new ArrayList<Integer>();
    //private List<Integer> AdvTunnelPosX=new ArrayList<Integer>(),AdvTunnelPosY=new ArrayList<Integer>(),AdvTunnelPosZ=new ArrayList<Integer>();
    private List<Integer> CoolantPosX=new ArrayList<Integer>(),CoolantPosY=new ArrayList<Integer>(),CoolantPosZ=new ArrayList<Integer>();
    private List<Integer> TimePosX=new ArrayList<Integer>(),TimePosY=new ArrayList<Integer>(),TimePosZ=new ArrayList<Integer>();

    private int searchX,searchY,searchZ;
    private int psearchX,psearchY,psearchZ;

    public int stat=0;

    public double storedEnergy=0;
    public int guiField1 =0;//storedInt, FailX
    public int guiField3 =0;//lastConsumed, FailZ
    public int uuStored=0;
    public int guiField2 =0;//storedIntH, FailY
    public int guiField4 =0;//lastConsumedH
    public double drag=0;
    public double failrate=1;
    public double dragUI=0;
    public double failrateUI=0;
    public double accProgress=0;
    public int accProgressInt=0;
    public double EUperUU=1000000;

    //0=z+ , 2=z- , 3=x+ , 1=x-
    private final int dirDeltaX[]={0,-1,0,1};
    private final int dirDeltaZ[]={1,0,-1,0};
    public int posReset=0;
    public int waitT=0;

    private void doFail(int px,int py,int pz){
        posReset=10000;
        guiField1=px;
        guiField2=py;
        guiField3=pz;
    }


    public class AccProperty{
        public double drag;
        public double failrate;
        public AccProperty(double drag,double failrate){
            this.drag=drag;
            this.failrate=failrate;
        }
    }

    private AccProperty calculateProperty(List<Integer> posListX, List<Integer> posListY, List<Integer> posListZ){
        double avgX=0,avgY=0,avgZ=0,avgDis=0,deltaDis=0;
        for (int i=0;i<posListX.size();i++){
            avgX+=posListX.get(i);
            avgY+=posListY.get(i);
            avgZ+=posListZ.get(i);
            //System.out.println(String.format("x%d,y%d,z%d",posListX.get(i),posListY.get(i),posListZ.get(i)));
        }
        avgX/=posListX.size();
        avgY/=posListX.size();
        avgZ/=posListX.size();
        //for(BlockPos p:posList)avgDis+=Math.sqrt(p.distanceSq(avgX,avgY,avgZ));
        for (int i=0;i<posListX.size();i++){
            double dis=Math.sqrt(Math.pow(posListX.get(i)-avgX,2)+
                    Math.pow(posListY.get(i)-avgY,2)+
                    Math.pow(posListZ.get(i)-avgZ,2));
            avgDis+=dis;
        }

        avgDis/=posListX.size();
        for (int i=0;i<posListX.size();i++){
            double dis=Math.sqrt(Math.pow(posListX.get(i)-avgX,2)+
                    Math.pow(posListY.get(i)-avgY,2)+
                    Math.pow(posListZ.get(i)-avgZ,2));
            deltaDis+=Math.pow(dis-avgDis,2);
        }

        deltaDis=Math.sqrt(deltaDis);
        //System.out.println(String.valueOf(deltaDis*1000/avgDis/posListX.size()/posListX.size()));
        //System.out.println(Config.kOverall);
        drag=deltaDis*1000/avgDis/posListX.size()/posListX.size()+Config.kDrag;
        failrate=avgDis*avgDis*Config.kFail;
        return new AccProperty(drag,failrate);
    }
    private double calculateAcceleration(double drag,double eu,double kAcceleration,double kOverall,int numStabilizer,double failrate,double kStabilizer){
        double r=failrate;
        for (int i=0;i<numStabilizer;i++){
            r*=kStabilizer;
            r-=0.01;
        }
        //if(worldObj.getWorldTime()%20==0)System.out.println(String.valueOf(r));
        return kOverall*(kAcceleration*Math.sqrt(eu)*(Math.random()>r?1:0)-drag);
    }
    public int[] toIntArray(List<Integer> l){
        int[] a=new int[l.size()];
        for (int i=0;i<l.size();i++) {
            a[i]=l.get(i);
        }
        return a;
    }
    public List<Integer> toArrayListInt(int[] a){
        List<Integer> l=new ArrayList<Integer>();
        for(int i:a){
            l.add(i);
        }
        return l;
    }
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        dir=compound.getInteger("dirMachine");
        EnergyPosX=toArrayListInt(compound.getIntArray("energyx"));
        EnergyPosY=toArrayListInt(compound.getIntArray("energyy"));
        EnergyPosZ=toArrayListInt(compound.getIntArray("energyz"));
        /*HullPosX=toArrayListInt(compound.getIntArray("hullx"));
        HullPosY=toArrayListInt(compound.getIntArray("hully"));
        HullPosZ=toArrayListInt(compound.getIntArray("hullz"));*/
        TunnelPosX=toArrayListInt(compound.getIntArray("tunnelx"));
        TunnelPosY=toArrayListInt(compound.getIntArray("tunnely"));
        TunnelPosZ=toArrayListInt(compound.getIntArray("tunnelz"));
        FluidPosX=toArrayListInt(compound.getIntArray("fluidx"));
        FluidPosY=toArrayListInt(compound.getIntArray("fluidy"));
        FluidPosZ=toArrayListInt(compound.getIntArray("fluidz"));
        CoolantPosX=toArrayListInt(compound.getIntArray("coolantx"));
        CoolantPosY=toArrayListInt(compound.getIntArray("coolanty"));
        CoolantPosZ=toArrayListInt(compound.getIntArray("coolantz"));
        TimePosX=toArrayListInt(compound.getIntArray("timeposx"));
        TimePosY=toArrayListInt(compound.getIntArray("timeposy"));
        TimePosZ=toArrayListInt(compound.getIntArray("timeposz"));
        /*
        AdvTunnelPosX=toArrayListInt(compound.getIntArray("advtunnelx"));
        AdvTunnelPosY=toArrayListInt(compound.getIntArray("advtunnely"));
        AdvTunnelPosZ=toArrayListInt(compound.getIntArray("advtunnelz"));*/
        stat=compound.getInteger("stat");
        posReset=compound.getInteger("posreset");
        searchX=compound.getInteger("searchx");
        searchY=compound.getInteger("searchy");
        searchZ=compound.getInteger("searchz");
        psearchX=compound.getInteger("psearchx");
        psearchY=compound.getInteger("psearchy");
        psearchZ=compound.getInteger("psearchz");
        storedEnergy=compound.getDouble("storedenergy");
        EUperUU=compound.getDouble("euperuu");
        guiField1=compound.getInteger("gui1");
        guiField2=compound.getInteger("gui2");

        guiField3 =compound.getInteger("lastconsumedenergy");
        uuStored=compound.getInteger("uustored");
        drag=compound.getDouble("drag");
        failrate=compound.getDouble("failrate");
        accProgress=compound.getDouble("accprogress");

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("dirMachine",dir);
        /*compound.setIntArray("hullx",toIntArray(HullPosX));
        compound.setIntArray("hully",toIntArray(HullPosY));
        compound.setIntArray("hullz",toIntArray(HullPosZ));*/
        compound.setIntArray("energyx", toIntArray(EnergyPosX));
        compound.setIntArray("energyy", toIntArray(EnergyPosY));
        compound.setIntArray("energyz", toIntArray(EnergyPosZ));
        compound.setIntArray("tunnelx", toIntArray(TunnelPosX));
        compound.setIntArray("tunnely", toIntArray(TunnelPosY));
        compound.setIntArray("tunnelz", toIntArray(TunnelPosZ));
        compound.setIntArray("fluidx", toIntArray(FluidPosX));
        compound.setIntArray("fluidy", toIntArray(FluidPosY));
        compound.setIntArray("fluidz", toIntArray(FluidPosZ));
        compound.setIntArray("coolantx", toIntArray(CoolantPosX));
        compound.setIntArray("coolanty", toIntArray(CoolantPosY));
        compound.setIntArray("coolantz", toIntArray(CoolantPosZ));
        compound.setIntArray("timeposx", toIntArray(TimePosX));
        compound.setIntArray("timeposy", toIntArray(TimePosY));
        compound.setIntArray("timeposz", toIntArray(TimePosZ));
        /*
        compound.setIntArray("advtunnelx",toIntArray(AdvTunnelPosX));
        compound.setIntArray("advtunnely",toIntArray(AdvTunnelPosY));
        compound.setIntArray("advtunnelz",toIntArray(AdvTunnelPosZ));*/
        compound.setInteger("stat",stat);
        compound.setInteger("posreset",posReset);
        compound.setInteger("searchx",searchX);
        compound.setInteger("searchy",searchY);
        compound.setInteger("searchz",searchZ);
        compound.setInteger("psearchx",psearchX);
        compound.setInteger("psearchy",psearchY);
        compound.setInteger("psearchz",psearchZ);
        compound.setDouble("storedenergy",storedEnergy);
        compound.setDouble("euperuu",EUperUU);
        compound.setInteger("uustored",uuStored);
        compound.setInteger("lastconsumedenergy", guiField3);
        compound.setInteger("gui1", guiField1);
        compound.setInteger("gui2", guiField2);
        compound.setDouble("drag",drag);
        compound.setDouble("failrate",failrate);
        compound.setDouble("accprogress",accProgress);

        return compound;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);

        return new SPacketUpdateTileEntity(this.pos, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        readFromNBT(packet.getNbtCompound());
    }
}
