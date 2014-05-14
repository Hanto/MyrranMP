package Model.Classes.Acciones.TiposAccion;// Created by Hanto on 07/05/2014.

import Controller.Controlador;
import Model.Classes.Acciones.Accion;
import Model.Classes.Mobiles.Player;
import Model.Classes.UIO.PlayerEstado;

public class IrEste extends Accion
{
    public IrEste()
    {   iD = getClass().getSimpleName(); }

    @Override public void accionKeyDown(Player player, PlayerEstado playerE, Controlador controlador)
    {   playerE.getPlayerI().irDerecha = true;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }

    @Override public void accionKeyUp(Player player, PlayerEstado playerE, Controlador controlador)
    {   playerE.getPlayerI().irDerecha = false;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());

    }
}
