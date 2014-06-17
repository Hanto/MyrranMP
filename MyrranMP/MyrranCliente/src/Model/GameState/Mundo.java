package Model.GameState;// Created by Hanto on 08/04/2014.

import DTO.NetDTO;
import Data.MiscData;
import Interfaces.Model.AbstractModel;
import Model.Classes.Geo.Mapa;
import Model.Classes.Mobiles.PC;
import Model.Classes.Mobiles.Player;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

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
    private World world;

    public boolean[][] mapTilesCargados = new boolean[3][3];


    //Get:
    public List<? extends PC> listaPlayers()        { return listaPlayers; }
    public PC getPC (int connectionID)              { return mapaPlayers.get(connectionID); }
    public Player getPlayer()                       { return player; }
    public Mapa getMapa()                           { return mapa; }
    public World getWorld()                         { return world; }

    public Mundo()
    {
        player = new Player();
        mapa = new Mapa(player);
        world = new World(new Vector2(0, -9.8f), false);
    }

    //SE NOTIFICA:
    public void añadirPC (int connectionID, float x, float y)
    {
        PC pc = new PC(connectionID);
        pc.setPosition(x, y);
        listaPlayers.add(pc);
        mapaPlayers.put(pc.getConnectionID(), pc);
        Object añadirPC = new NetDTO.AñadirPPC(pc);
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
