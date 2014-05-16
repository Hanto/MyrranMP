package DTO;// Created by Hanto on 07/04/2014.

import Data.MiscData;
import Interfaces.MobPC;
import Interfaces.Vulnerable;
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
        kryo.register(SetSpellIDSeleccionado.class);
        kryo.register(SetParametrosSpell.class);


        kryo.register(ParametrosSpellDTO.ParametrosEditarTerreno.class);
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
        public ActualizarPlayer(MobPC pc, Vulnerable vulnerable)
        {
            connectionID = pc.getConnectionID();
            nombre = pc.getNombre();
            nivel = pc.getNivel();
            actualHPs = vulnerable.getActualHPs();
            maxHPs = vulnerable.getMaxHPs();
            x = pc.getX();
            y = pc.getY();
        }
    }

    public static class AñadirPC
    {
        public int connectionID;
        public float x;
        public float y;
        public int numAnimacion;

        public AñadirPC () {}
        public AñadirPC (MobPC pc)
        {
            connectionID = pc.getConnectionID();
            x = pc.getX();
            y = pc.getY();
            numAnimacion = pc.getNumAnimacion();
        }
        public AñadirPC (int connectionID, float x, float y, int numAnimacion)
        {   this.connectionID = connectionID; this.x = x; this.y = y; this.numAnimacion = numAnimacion; }
    }

    public static class MoverPC
    {
        public int connectionID;
        public float x;
        public float y;

        public MoverPC () {}
        public MoverPC (MobPC pc)
        {
            connectionID = pc.getConnectionID();
            x = pc.getX();
            y = pc.getY();
        }
        public MoverPC(int connectionID, float x, float y)
        {   this.connectionID = connectionID; this.x = x; this.y = y; }
    }

    public static class CambiarAnimacionPC
    {
        public int connectionID;
        public int numAnimacion;
        public CambiarAnimacionPC() {}
        public CambiarAnimacionPC(MobPC pc)
        {
            connectionID = pc.getConnectionID();
            numAnimacion = pc.getNumAnimacion();
        }
        public CambiarAnimacionPC(int connectionID, int numAnimacion)
        {   this.connectionID = connectionID; this.numAnimacion = numAnimacion; }
    }

    public static class EliminarPC
    {
        public int connectionID;

        public EliminarPC () {}
        public EliminarPC (MobPC pc)
        {   this.connectionID = pc.getConnectionID(); }
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

    public static class SetSpellIDSeleccionado
    {
        public String spellID;
        public Object parametrosSpell;
        public SetSpellIDSeleccionado() {}
        public SetSpellIDSeleccionado(String spellID, Object parametrosSpell)
        {   this.spellID = spellID; this.parametrosSpell = parametrosSpell; }
    }

    public static class SetParametrosSpell
    {
        public Object parametrosSpell;
        public SetParametrosSpell() {}
        public SetParametrosSpell(Object parametrosSpell)
        {   this.parametrosSpell = parametrosSpell; }
    }
}
