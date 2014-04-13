package Modelo.Mobiles;// Created by Hanto on 08/04/2014.

import DTO.PcDTO;
import DTO.PlayerDTO;
import Modelo.AbstractModel;

import java.util.ArrayList;
import java.util.HashMap;

public class MundoModel extends AbstractModel
{
    public PlayerModel playerModel;
    public ArrayList<PcModel> listaPlayers = new ArrayList<>();
    public HashMap<Integer,PcModel> mapaPlayers = new HashMap<>();
    //Get:
    public ArrayList<? extends PcModel> listaPlayers()    { return listaPlayers; }
    public PlayerModel getPlayerModel()                             { return playerModel; }




    //SE NOTIFICA:
    public void añadirPlayer (int connectionID)
    {
        playerModel = new PlayerModel(connectionID);
        Object añadirPlayer = new PlayerDTO.MundoAñadirPlayer(playerModel);
        notificarActualizacion("añadirPlayer", null, añadirPlayer);
    }
    //SE NOTIFICA:
    public void añadirPC (int connectionID, float x, float y)
    {
        PcModel pcModel = new PcModel(connectionID);
        pcModel.setPosition(x, y);
        listaPlayers.add(pcModel);
        mapaPlayers.put(pcModel.getConnectionID(), pcModel);
        Object añadirPC = new PcDTO.MundoAñadirPC(pcModel, pcModel.getX(), pcModel.getY());
        notificarActualizacion("añadirPC", null, añadirPC);
    }

    public void eliminarPlayer()                          { }
    public void eliminarPC (int connectionID)
    {
        PcModel pcModel = mapaPlayers.get(connectionID);
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
