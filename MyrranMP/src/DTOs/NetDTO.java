package DTOs;// Created by Hanto on 07/04/2014.

import Models.PCModel;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import zMain.MiscData;

public class NetDTO
{
    public static final int puerto = MiscData.NETWORK_Puerto_Servidor;
    public static final int timeout = MiscData.NETWORK_Client_Timeout;

    public static void register (EndPoint endPoint)
    {
        Kryo kryo = endPoint.getKryo();
        kryo.register(MoverPC.class);
        kryo.register(CambiarAnimacionPC.class);
        kryo.register(AñadirPC.class);
        kryo.register(EliminarPC.class);
    }

    //Network DTOs:

    public static class AñadirPC
    {
        public int connectionID;
        public float x;
        public float y;

        public AñadirPC () {}
        public AñadirPC (PCModel pc)
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
        public EliminarPC (PCModel pc)
        {   connectionID = pc.getConnectionID(); }
        public EliminarPC (int connectionID)
        {   this.connectionID = connectionID; }
    }
}
