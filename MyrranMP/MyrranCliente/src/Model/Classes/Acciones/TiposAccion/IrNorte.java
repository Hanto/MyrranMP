package Model.Classes.Acciones.TiposAccion;// Created by Hanto on 05/05/2014.

import Controller.Controlador;
import Model.Classes.Acciones.Accion;
import Model.Classes.Mobiles.Player;
import Model.Classes.UI.Input.PlayerEstado;

public class IrNorte extends Accion
{
    public IrNorte()
    {   iD = getClass().getSimpleName(); }

    @Override public void accionKeyDown(Player player, PlayerEstado playerE, Controlador controlador)
    {   playerE.getPlayerI().irArriba = true;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }

    @Override public void accionKeyUp(Player player, PlayerEstado playerE, Controlador controlador)
    {   playerE.getPlayerI().irArriba = false;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());

    }
}
