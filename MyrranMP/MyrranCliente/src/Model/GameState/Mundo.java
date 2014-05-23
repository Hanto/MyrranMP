package Model.GameState;// Created by Hanto on 08/04/2014.

import DTO.NetDTO;
import Data.MiscData;
import Interfaces.AbstractModel;
import Model.Classes.Geo.Mapa;
import Model.Classes.Mobiles.PC;
import Model.Classes.Mobiles.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mundo extends AbstractModel
{
    private List<PC> listaPlayers = new ArrayList<>();
    private Map<Integer,PC> mapaPlayers = new HashMap<>();

    private Player player;
    private Mapa mapa;

    public boolean[][] mapTilesCargados = new boolean[3][3];


    //Get:
    public List<? extends PC> listaPlayers()        { return listaPlayers; }
    public PC getPC (int connectionID)              { return mapaPlayers.get(connectionID); }
    public Player getPlayer()                       { return player; }
    public Mapa getMapa()                           { return mapa; }

    public Mundo()
    {
        player = new Player();
        mapa = new Mapa(player);
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
                    short idTerreno = mapaServidor.mapa[x][y].celda[i];
                    mapa.setTerreno(x+mapaServidor.esquinaInfIzdaX,y+mapaServidor.esquinaInfIzdaY,i,idTerreno);
                }
            }
        }
    }
}
