package Modelo.Mobiles;// Created by Hanto on 08/04/2014.

import java.util.ArrayList;
import java.util.HashMap;

public class Mundo extends AbstractModel implements MundoModel
{
    public Player player;
    public ArrayList<PC> listaPlayers = new ArrayList<>();
    public HashMap<Integer,PC> mapaPlayers = new HashMap<>();

    //Get:
    @Override public ArrayList<? extends PCModel> listaPlayers()    { return listaPlayers; }
    @Override public Player getPlayer()                             { return player; }


    //SE NOTIFICA:
    @Override public void añadirPlayer (int connectionID)
    {
        player = new Player(connectionID);
        Object añadirPlayer = new DTO.MundoAñadirPlayer(player);
        notificarActualizacion("añadirPlayer", null, añadirPlayer);
    }

    //SE NOTIFICA:
    @Override public void añadirPC (int connectionID, float x, float y)
    {
        PC pc = new PC(connectionID);
        pc.setPosition(x, y);
        listaPlayers.add(pc);
        mapaPlayers.put(pc.getConnectionID(), pc);
        Object añadirPC = new DTO.MundoAñadirPC(pc, pc.getX(), pc.getY());
        notificarActualizacion("añadirPC", null, añadirPC);
    }

    @Override public void eliminarPlayer()                          {}
    @Override public void eliminarPC (int connectionID)
    {
        PC pc = mapaPlayers.get(connectionID);
        listaPlayers.remove(pc);
        mapaPlayers.remove(connectionID);
        pc.eliminar();
    }

    @Override public void moverPC (int connectionID, float x, float y)
    {   mapaPlayers.get(connectionID).setPosition(x, y); }

    @Override public void moverPlayer(float x, float y)
    {   player.setPosition(x, y); }

}
