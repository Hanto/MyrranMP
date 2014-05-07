package Model.Classes.Acciones.TiposAccion;// Created by Hanto on 07/05/2014.

import Model.Classes.Acciones.Accion;
import Model.Classes.Mobiles.Player;
import Model.Classes.UIO.PlayerEstado;

public class IrOeste extends Accion
{
    public IrOeste()
    {   iD = getClass().getSimpleName(); }

    @Override public void accionKeyDown(Player player, PlayerEstado playerE)
    {   playerE.getPlayerI().irIzquierda = true;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }

    @Override public void accionKeyUp(Player player, PlayerEstado playerE)
    {   playerE.getPlayerI().irIzquierda = false;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());

    }
}
