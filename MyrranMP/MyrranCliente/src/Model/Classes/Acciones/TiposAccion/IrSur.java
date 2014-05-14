package Model.Classes.Acciones.TiposAccion;// Created by Hanto on 13/05/2014.

import Controller.Controlador;
import Model.Classes.Acciones.Accion;
import Model.Classes.Mobiles.Player;
import Model.Classes.UIO.Input.PlayerEstado;

public class IrSur extends Accion
{
    public IrSur()
    {   iD = getClass().getSimpleName(); }

    @Override public void accionKeyDown(Player player, PlayerEstado playerE, Controlador controlador)
    {   playerE.getPlayerI().irAbajo = true;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }

    @Override public void accionKeyUp(Player player, PlayerEstado playerE, Controlador controlador)
    {   playerE.getPlayerI().irAbajo = false;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }
}
