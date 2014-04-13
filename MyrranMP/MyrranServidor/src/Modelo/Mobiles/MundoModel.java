package Modelo.Mobiles;// Created by Hanto on 07/04/2014.

import Modelo.AbstractModel;
import DTO.MobDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class MundoModel extends AbstractModel
{
    public ArrayList<PCModel> listaPlayers = new ArrayList<>();
    public HashMap<Integer, PCModel> mapaPlayers = new HashMap<>();


    public ArrayList<? extends PCModel> listaPlayers()    { return listaPlayers; }

    public void añadirPC (int connectionID, float x, float y)
    {
        PCModel pcModel = new PCModel(connectionID);
        pcModel.setPosition(x, y);
        listaPlayers.add(pcModel);
        mapaPlayers.put(pcModel.getConnectionID(), pcModel);
        Object añadirPC = new MobDTO.MundoAñadirPC(pcModel, pcModel.getX(), pcModel.getY());
        notificarActualizacion("añadirPC", null, añadirPC);
    }

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

}
