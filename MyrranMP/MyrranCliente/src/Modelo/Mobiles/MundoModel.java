package Modelo.Mobiles;// Created by Hanto on 08/04/2014.

import Modelo.AbstractModel;
import DTO.MobDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class MundoModel extends AbstractModel
{
    public PlayerModel playerModel;
    public ArrayList<PCModel> listaPlayers = new ArrayList<>();
    public HashMap<Integer,PCModel> mapaPlayers = new HashMap<>();
    //Get:
    public ArrayList<? extends PCModel> listaPlayers()    { return listaPlayers; }
    public PlayerModel getPlayerModel()                             { return playerModel; }




    //SE NOTIFICA:
    public void añadirPlayer (int connectionID)
    {
        playerModel = new PlayerModel(connectionID);
        Object añadirPlayer = new MobDTO.MundoAñadirPlayer(playerModel);
        notificarActualizacion("añadirPlayer", null, añadirPlayer);
    }
    //SE NOTIFICA:
    public void añadirPC (int connectionID, float x, float y)
    {
        PCModel pcModel = new PCModel(connectionID);
        pcModel.setPosition(x, y);
        listaPlayers.add(pcModel);
        mapaPlayers.put(pcModel.getConnectionID(), pcModel);
        Object añadirPC = new MobDTO.MundoAñadirPC(pcModel, pcModel.getX(), pcModel.getY());
        notificarActualizacion("añadirPC", null, añadirPC);
    }

    public void eliminarPlayer()                          { }
    public void eliminarPC (int connectionID)
    {
        PCModel pcModel = mapaPlayers.get(connectionID);
        listaPlayers.remove(pcModel);
        mapaPlayers.remove(connectionID);
        pcModel.eliminar();
    }

    public void moverPC (int connectionID, float x, float y)
    {   mapaPlayers.get(connectionID).setPosition(x, y); }

    public void cambiarAnimacionPC(int connectionID, int numAnimacion)
    {   mapaPlayers.get(connectionID).setAnimacion(numAnimacion); }

    public void moverPlayer(float x, float y)
    {   playerModel.setPosition(x, y); }

}
