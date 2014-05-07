package Model.Classes.Acciones.TiposAccion;// Created by Hanto on 05/05/2014.

import Model.Classes.Acciones.Accion;
import Model.Classes.Mobiles.Player;
import Model.Classes.UIO.PlayerEstado;

public class IrNorte extends Accion
{
    public IrNorte()
    {   iD = getClass().getSimpleName(); }

    @Override public void accionKeyDown(Player player, PlayerEstado playerE)
    {   playerE.getPlayerI().irArriba = true;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }

    @Override public void accionKeyUp(Player player, PlayerEstado playerE)
    {   playerE.getPlayerI().irArriba = false;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());

    }
}
