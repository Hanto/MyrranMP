package Model.Mobiles;// Created by Hanto on 07/04/2014.

import Interfaces.Caster;
import Model.AbstractModel;
import Model.DTO.MundoDTO;
import Model.Geo.Mapa;
import zMain.MiscData;

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

    public void moverPC (int connectionID, float x, float y)
    {   mapaPlayers.get(connectionID).setPosition(x, y); }

    public void cambiarAnimacionPC(int connectionID, int numAnimacion)
    {   mapaPlayers.get(connectionID).setAnimacion(numAnimacion); }





    //AI:

    public void mundoUpdate()
    {
        for (PC players: listaPlayers)
        {   if (players instanceof Caster)
            {
                actualizarCastingTime(players);
                castear(players);
            }
        }
    }

    public void actualizarCastingTime(Caster caster)
    {
        if (caster.isCasteando())
        {
            caster.setActualCastingTime(caster.getActualCastingTime() + MiscData.NETWORK_Update_Time);
            if (caster.getActualCastingTime() >= caster.getTotalCastingTime())
            {
                caster.setTotalCastingTime(0f);
                caster.setIsCasteando(false);
            }
        }
    }

    public void castear(PC caster)
    {
        if (!caster.isCasteando() )
        {

        }
    }
}
