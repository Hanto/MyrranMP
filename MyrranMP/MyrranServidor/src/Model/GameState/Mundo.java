package Model.GameState;// Created by Hanto on 07/04/2014.

import DTO.NetDTO;
import Model.AbstractModel;
import Model.Classes.Geo.Mapa;
import Model.Classes.Mobiles.PC;

import java.util.*;

public class Mundo extends AbstractModel
{
    private List<PC> listaPlayers = new ArrayList<>();
    private Map<Integer, PC> mapaPlayers = new HashMap<>();

    private Mapa mapa = new Mapa();

    public Mapa getMapa()                           { return mapa; }
    public Iterator<PC> getIteratorListaPlayers()   { return listaPlayers.iterator(); }

    //public List<? extends PC> listaPlayers()    { return listaPlayers; }

    public void añadirPC (int connectionID, float x, float y)
    {
        PC pc = new PC(connectionID, mapa);
        pc.setPosition(x, y);
        listaPlayers.add(pc);
        mapaPlayers.put(pc.getConnectionID(), pc);
        Object añadirPC = new NetDTO.AñadirPC(pc);
        notificarActualizacion("añadirPC", null, añadirPC);
    }

    public void eliminarPC (int connectionID)
    {
        PC PC = mapaPlayers.get(connectionID);
        listaPlayers.remove(PC);
        mapaPlayers.remove(connectionID);
        PC.eliminar();
    }

    public PC getPC (int connectionID)
    {   return mapaPlayers.get(connectionID); }
}
