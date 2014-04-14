package Modelo.Mobiles;// Created by Hanto on 07/04/2014.

import Modelo.AbstractModel;
import Modelo.DTO.MundoDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MundoModel extends AbstractModel
{
    public List<PcModel> listaPlayers = new ArrayList<>();
    public Map<Integer, PcModel> mapaPlayers = new HashMap<>();


    public List<? extends PcModel> listaPlayers()    { return listaPlayers; }

    public void añadirPC (int connectionID, float x, float y)
    {
        PcModel pcModel = new PcModel(connectionID);
        pcModel.setPosition(x, y);
        listaPlayers.add(pcModel);
        mapaPlayers.put(pcModel.getConnectionID(), pcModel);
        Object añadirPC = new MundoDTO.AñadirPC(pcModel, pcModel.getX(), pcModel.getY());
        notificarActualizacion("añadirPC", null, añadirPC);
    }

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

}
