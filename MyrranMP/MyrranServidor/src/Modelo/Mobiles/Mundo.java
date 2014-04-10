package Modelo.Mobiles;// Created by Hanto on 07/04/2014.

import Modelo.Mobiles.DTO.MundoAñadirPC;

import java.util.ArrayList;
import java.util.HashMap;

public class Mundo extends AbstractModel implements MundoModel
{
    public ArrayList<PC> listaPlayers = new ArrayList<>();
    public HashMap<Integer, PC> mapaPlayers = new HashMap<>();


    @Override public ArrayList<? extends PCModel> listaPlayers()    { return listaPlayers; }
    @Override public PlayerModel getPlayer()                        { return null; }

    @Override public void añadirPlayer(int connectionID) {}
    @Override public void eliminarPlayer() {}
    @Override public void moverPlayer(float x, float y) {}

    @Override public void añadirPC (int connectionID, float x, float y)
    {
        PC pc = new PC(connectionID);
        pc.setPosition(x, y);
        listaPlayers.add(pc);
        mapaPlayers.put(pc.getConnectionID(), pc);
        Object añadirPC = new MundoAñadirPC(pc, pc.getX(), pc.getY());
        notificarActualizacion("añadirPC", null, añadirPC);
    }

    @Override public void eliminarPC (int connectionID)
    {
        PC pc = mapaPlayers.get(connectionID);
        listaPlayers.remove(pc);
        mapaPlayers.remove(connectionID);
        //Object eliminarPC = new Net.MundoEliminarPC(pc);
        //notificarActualizacion("eliminarPC", null, eliminarPC);
        pc.eliminar();
    }

    @Override public void moverPC (int connectionID, float x, float y)
    {   mapaPlayers.get(connectionID).setPosition(x, y); }
}
