package Model.Mobiles;// Created by Hanto on 08/04/2014.

import Model.AbstractModel;
import Model.DTO.MundoDTO;
import Model.DTO.NetDTO;
import Model.Geo.MapaModel;
import zMain.MiscData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MundoModel extends AbstractModel
{
    public PlayerModel player = new PlayerModel();

    public List<PCModel> listaPlayers = new ArrayList<>();
    public Map<Integer,PCModel> mapaPlayers = new HashMap<>();

    public MapaModel mapa = new MapaModel();

    //Get:
    public List<? extends PCModel> listaPlayers()       { return listaPlayers; }
    public PlayerModel getPlayer()                      { return player; }

    public MundoModel()
    {
        for (int x = 0; x< MiscData.MAPA_Max_X; x++)
        {
            for (int y = 0; y< MiscData.MAPA_Max_Y; y++)
            {
                mapa.setTerreno(x,y,0,0);
            }
        }
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

    public void añadirPlayer (int connectionID)         { player.setConnectionID(connectionID); }
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
        player.setNombre(updatePlayer.nombre);
        player.setNivel(updatePlayer.nivel);
        player.setActualHPs(updatePlayer.actualHPs);
        player.setMaxHPs(updatePlayer.maxHPs);
        player.setPosition(updatePlayer.x, updatePlayer.y);
    }

    public void moverPC (int connectionID, float x, float y)
    {   mapaPlayers.get(connectionID).setPosition(x, y); }

    public void cambiarAnimacionPC(int connectionID, int numAnimacion)
    {   mapaPlayers.get(connectionID).setAnimacion(numAnimacion); }

    public void moverPlayer(float x, float y)
    {   player.setPosition(x, y); }

}
