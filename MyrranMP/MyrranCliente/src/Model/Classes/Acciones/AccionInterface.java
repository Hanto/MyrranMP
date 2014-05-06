package Model.Classes.Acciones;// Created by Hanto on 05/05/2014.

import Controller.Input.PlayerEstado;
import Model.Classes.Mobiles.Player;

public interface AccionInterface
{
    public void ejecutarAccion(Player player, PlayerEstado playerE);
}