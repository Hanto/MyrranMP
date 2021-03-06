package Model.Classes.Acciones.TiposAccion;// Created by Hanto on 07/05/2014.

import Interfaces.EntidadesTipos.MobPlayer;
import Interfaces.UI.ControladorUI;
import Interfaces.Input.PlayerEstadoI;
import Model.Classes.Acciones.Accion;

public class IrEste extends Accion
{
    public IrEste()
    {   iD = getClass().getSimpleName(); }

    @Override public void accionKeyDown(MobPlayer player, PlayerEstadoI playerE, ControladorUI controlador)
    {   playerE.getPlayerI().setirDerecha(true);;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }

    @Override public void accionKeyUp(MobPlayer player, PlayerEstadoI playerE, ControladorUI controlador)
    {   playerE.getPlayerI().setirDerecha(false);
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());

    }
}
