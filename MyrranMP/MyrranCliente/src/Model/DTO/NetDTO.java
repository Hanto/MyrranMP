package Model.DTO;// Created by Hanto on 07/04/2014.

import Data.MiscData;
import Model.Classes.Mobiles.PC;
import Model.Classes.Mobiles.Player;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

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
        kryo.register(CastearPC.class);
        kryo.register(SetTerreno.class);
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
        public int numAnimacion;

        public AñadirPC () {}
        public AñadirPC (PC pc, int numAnimacion)
        { connectionID = pc.getConnectionID(); x = pc.getX(); y = pc.getY(); this.numAnimacion = numAnimacion; }
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

    public static class CastearPC
    {
        public Boolean castear;
        public int targetX;
        public int targetY;
        public CastearPC () {}
        public CastearPC(Boolean castear, int x, int y)
        {   this.castear = castear; targetX = x; targetY = y; }
    }

    public static class SetTerreno
    {
        public int celdaX;
        public int celdaY;
        public int numCapa;
        public int iDTerreno;
        public SetTerreno() {}
        public SetTerreno(int celdaX, int celdaY, int numCapa, int iDTerreno)
        {   this.celdaX = celdaX; this.celdaY = celdaY; this.numCapa = numCapa; this.iDTerreno = iDTerreno; }
    }
}
