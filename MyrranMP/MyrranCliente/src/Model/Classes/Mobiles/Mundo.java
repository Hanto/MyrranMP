package Model.Classes.Mobiles;// Created by Hanto on 08/04/2014.

import Model.Classes.AbstractModel;
import Model.DTO.MundoDTO;
import Model.DTO.NetDTO;
import Model.Classes.Geo.Mapa;
import Data.MiscData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mundo extends AbstractModel
{
    public Player player = new Player();

    public List<PC> listaPlayers = new ArrayList<>();
    public Map<Integer,PC> mapaPlayers = new HashMap<>();

    public Mapa mapa = new Mapa();

    //Get:
    public List<? extends PC> listaPlayers()       { return listaPlayers; }
    public Player getPlayer()                      { return player; }
    public PC getPC (int connectionID)             { return mapaPlayers.get(connectionID); }

    public Mundo()
    {
        for (int x = 0; x< MiscData.MAPA_Max_TilesX; x++)
        {
            for (int y = 0; y< MiscData.MAPA_Max_TilesY; y++)
            {
                mapa.setTerreno(x,y,0,0);
            }
        }
    }

    //SE NOTIFICA:
    public void añadirPC (int connectionID, float x, float y)
    {
        PC pc = new PC(connectionID);
        pc.setPosition(x, y);
        listaPlayers.add(pc);
        mapaPlayers.put(pc.getConnectionID(), pc);
        Object añadirPC = new MundoDTO.AñadirPC(pc, pc.getX(), pc.getY());
        notificarActualizacion("añadirPC", null, añadirPC);
    }

    public void añadirPlayer (int connectionID)         { player.setConnectionID(connectionID); }
    public void eliminarPlayer()                        { }
    public void eliminarPC (int connectionID)
    {
        PC pc = mapaPlayers.get(connectionID);
        listaPlayers.remove(pc);
        mapaPlayers.remove(connectionID);
        pc.eliminar();
    }

    public void actualizarPlayer (NetDTO.ActualizarPlayer updatePlayer)
    {
        player.setNombre(updatePlayer.nombre);
        player.setNivel(updatePlayer.nivel);
        player.setActualHPs(updatePlayer.actualHPs);
        player.setMaxHPs(updatePlayer.maxHPs);
        player.setPosition(updatePlayer.x, updatePlayer.y);
    }
}
