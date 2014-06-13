package Model.Classes.Acciones.TiposAccion;// Created by Hanto on 05/05/2014.

import Interfaces.EntidadesTipos.MobPlayer;
import Interfaces.UI.Input.ControladorUI;
import Interfaces.UI.Input.PlayerEstadoI;
import Model.Classes.Acciones.Accion;

public class IrNorte extends Accion
{
    public IrNorte()
    {   iD = getClass().getSimpleName(); }

    @Override public void accionKeyDown(MobPlayer player, PlayerEstadoI playerE, ControladorUI controlador)
    {   playerE.getPlayerI().setIrArriba(true);;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }

    @Override public void accionKeyUp(MobPlayer player, PlayerEstadoI playerE, ControladorUI controlador)
    {   playerE.getPlayerI().setIrArriba(false);;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());

    }
}
