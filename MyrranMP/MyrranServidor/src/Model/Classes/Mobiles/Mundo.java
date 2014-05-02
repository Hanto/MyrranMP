package Model.Classes.Mobiles;// Created by Hanto on 07/04/2014.

import Model.AbstractModel;
import Model.DTO.MundoDTO;
import Model.Classes.Geo.Mapa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mundo extends AbstractModel
{
    public List<PC> listaPlayers = new ArrayList<>();
    public Map<Integer, PC> mapaPlayers = new HashMap<>();

    public Mapa mapa = new Mapa();


    public List<? extends PC> listaPlayers()    { return listaPlayers; }

    public void añadirPC (int connectionID, float x, float y)
    {
        PC pc = new PC(connectionID, mapa);
        pc.setPosition(x, y);
        listaPlayers.add(pc);
        mapaPlayers.put(pc.getConnectionID(), pc);
        Object añadirPC = new MundoDTO.AñadirPC(pc, pc.getX(), pc.getY());
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
