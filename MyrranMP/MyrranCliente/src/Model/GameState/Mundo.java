package Model.GameState;// Created by Hanto on 08/04/2014.

import DTO.NetDTO;
import Data.MiscData;
import Interfaces.AbstractModel;
import Model.Classes.Geo.Mapa;
import Model.Classes.Mobiles.PC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mundo extends AbstractModel
{
    public List<PC> listaPlayers = new ArrayList<>();
    public Map<Integer,PC> mapaPlayers = new HashMap<>();

    //public Mapa mapa = new Mapa();
    public Mapa mapa;

    //Get:
    public List<? extends PC> listaPlayers()       { return listaPlayers; }
    public PC getPC (int connectionID)             { return mapaPlayers.get(connectionID); }

    public Mundo()
    {
        for (int x = 0; x< MiscData.MAPA_Max_TilesX; x++)
        {
            for (int y = 0; y< MiscData.MAPA_Max_TilesY; y++)
            {
                //mapa.setTerreno(x,y,0,0);
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
        Object añadirPC = new NetDTO.AñadirPC(pc.getConnectionID(), pc.getX(), pc.getY(), pc.getNumAnimacion());
        notificarActualizacion("añadirPC", null, añadirPC);
    }

    public void eliminarPC (int connectionID)
    {
        PC pc = mapaPlayers.get(connectionID);
        listaPlayers.remove(pc);
        mapaPlayers.remove(connectionID);
        pc.eliminar();
    }

    public void actualizarMapa (NetDTO.ActualizarMapa mapaServidor)
    {
        for (int y=0; y< mapaServidor.mapa[0].length; y++)
        {
            for (int x=0; x< mapaServidor.mapa.length; x++)
            {
                for (int i=0; i< MiscData.MAPA_Max_Capas_Terreno; i++)
                {
                    int idTerreno = mapaServidor.mapa[x][y].celda[i];
                    mapa.setTerreno(x+mapaServidor.esquinaInfIzdaX,y+mapaServidor.esquinaInfIzdaY,i,idTerreno);
                }
            }
        }
    }
}
