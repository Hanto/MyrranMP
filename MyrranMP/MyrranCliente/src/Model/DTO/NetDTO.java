package Model.DTO;// Created by Hanto on 07/04/2014.

import Model.Mobiles.PC;
import Model.Mobiles.Player;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import Data.MiscData;

public class NetDTO
{
    public static final int puerto = MiscData.NETWORK_Puerto_Servidor;
    public static final int timeout = MiscData.NETWORK_Client_Timeout;

    public static void register (EndPoint endPoint)
    {
        Kryo kryo = endPoint.getKryo();
        kryo.register(ActualizarPlayer.class);
        kryo.register(MoverPC.class);
        kryo.register(CambiarAnimacionPC.class);
        kryo.register(AñadirPC.class);
        kryo.register(EliminarPC.class);
    }

    //Network DTOs:

    public static class ActualizarPlayer
    {
        public int connectionID;
        public String nombre;
        public Integer nivel;
        public Float actualHPs;
        public Float maxHPs;
        public float x;
        public float y;
        public ActualizarPlayer() {}
        public ActualizarPlayer(Player pcModel)
        {   connectionID = pcModel.getConnectionID();
            nombre = pcModel.getNombre();
            nivel = pcModel.getNivel();
            actualHPs = pcModel.getMaxHPs();
            maxHPs = pcModel.getMaxHPs();
            x = pcModel.getX();
            y = pcModel.getY();
        }
    }

    public static class AñadirPC
    {
        public int connectionID;
        public float x;
        public float y;

        public AñadirPC () {}
        public AñadirPC (PC pc)
        { connectionID = pc.getConnectionID(); x = pc.getX(); y = pc.getY(); }
        public AñadirPC (int connectionID)
        {   this.connectionID = connectionID; }
    }

    public static class MoverPC
    {
        public int connectionID;
        public float x;
        public float y;

        public MoverPC () {}
        public MoverPC(int connectionID, float x, float y)
        {   this.connectionID = connectionID; this.x = x; this.y = y; }
    }

    public static class CambiarAnimacionPC
    {
        public int connectionID;
        public int numAnimacion;
        public CambiarAnimacionPC() {}
        public CambiarAnimacionPC(int connectionID, int numAnimacion)
        {   this.connectionID = connectionID; this.numAnimacion = numAnimacion; }
    }

    public static class EliminarPC
    {
        public int connectionID;

        public EliminarPC () {}
        public EliminarPC (PC pc)
        {   connectionID = pc.getConnectionID(); }
        public EliminarPC (int connectionID)
        {   this.connectionID = connectionID; }
    }
}
