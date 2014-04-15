package Model.Mobiles;// Created by Hanto on 08/04/2014.

import Model.AbstractModel;
import Model.DTO.MundoDTO;
import Model.DTO.NetDTO;
import Model.Geo.MapaModel;

import java.util.ArrayList;
import java.util.HashMap;

public class MundoModel extends AbstractModel
{
    public PlayerModel playerModel = new PlayerModel();
    public ArrayList<PCModel> listaPlayers = new ArrayList<>();
    public HashMap<Integer,PCModel> mapaPlayers = new HashMap<>();
    public MapaModel mapa = new MapaModel();

    //Get:
    public ArrayList<? extends PCModel> listaPlayers()  { return listaPlayers; }
    public PlayerModel getPlayerModel()                 { return playerModel; }

    public MundoModel()
    {
        mapa.setTerreno(0, 0, 0, 0);
        mapa.setTerreno(18,11, 0, 1);
        mapa.setTerreno(10,12, 0, 2);
        mapa.setTerreno(22,13, 0, 3);


    }

    //SE NOTIFICA:
    public void añadirPC (int connectionID, float x, float y)
    {
        PCModel pcModel = new PCModel(connectionID);
        pcModel.setPosition(x, y);
        listaPlayers.add(pcModel);
        mapaPlayers.put(pcModel.getConnectionID(), pcModel);
        Object añadirPC = new MundoDTO.AñadirPC(pcModel, pcModel.getX(), pcModel.getY());
        notificarActualizacion("añadirPC", null, añadirPC);
    }

    public void añadirPlayer (int connectionID)         { playerModel.setConnectionID(connectionID); }
    public void eliminarPlayer()                        { }
    public void eliminarPC (int connectionID)
    {
        PCModel pcModel = mapaPlayers.get(connectionID);
        listaPlayers.remove(pcModel);
        mapaPlayers.remove(connectionID);
        pcModel.eliminar();
    }

    public void actualizarPlayer (NetDTO.ActualizarPlayer updatePlayer)
    {
        playerModel.setNombre(updatePlayer.nombre);
        playerModel.setNivel(updatePlayer.nivel);
        playerModel.setActualHPs(updatePlayer.actualHPs);
        playerModel.setMaxHPs(updatePlayer.maxHPs);
        playerModel.setPosition(updatePlayer.x, updatePlayer.y);
    }

    public void moverPC (int connectionID, float x, float y)
    {   mapaPlayers.get(connectionID).setPosition(x, y); }

    public void cambiarAnimacionPC(int connectionID, int numAnimacion)
    {   mapaPlayers.get(connectionID).setAnimacion(numAnimacion); }

    public void moverPlayer(float x, float y)
    {   playerModel.setPosition(x, y); }

}
