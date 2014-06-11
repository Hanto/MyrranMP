package Model.Classes.UI.Acciones.TiposAccion;// Created by Hanto on 07/05/2014.

import Interfaces.EntidadesTipos.MobPlayer;
import Interfaces.UI.Input.ControladorUI;
import Interfaces.UI.Input.PlayerEstadoI;
import Model.Classes.UI.Acciones.Accion;

public class IrOeste extends Accion
{
    public IrOeste()
    {   iD = getClass().getSimpleName(); }

    @Override public void accionKeyDown(MobPlayer player, PlayerEstadoI playerE, ControladorUI controlador)
    {   playerE.getPlayerI().setIrIzquierda(true);
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }

    @Override public void accionKeyUp(MobPlayer player, PlayerEstadoI playerE, ControladorUI controlador)
    {   playerE.getPlayerI().setIrIzquierda(false);
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());

    }
}