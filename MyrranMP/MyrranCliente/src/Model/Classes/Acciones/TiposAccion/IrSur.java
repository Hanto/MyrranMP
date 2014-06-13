package Model.Classes.Acciones.TiposAccion;// Created by Hanto on 13/05/2014.

import Interfaces.EntidadesTipos.MobPlayer;
import Interfaces.UI.Input.ControladorUI;
import Interfaces.UI.Input.PlayerEstadoI;
import Model.Classes.Acciones.Accion;

public class IrSur extends Accion
{
    public IrSur()
    {   iD = getClass().getSimpleName(); }

    @Override public void accionKeyDown(MobPlayer player, PlayerEstadoI playerE, ControladorUI controlador)
    {   playerE.getPlayerI().setIrAbajo(true);
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }

    @Override public void accionKeyUp(MobPlayer player, PlayerEstadoI playerE, ControladorUI controlador)
    {   playerE.getPlayerI().setIrAbajo(false);
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }
}
