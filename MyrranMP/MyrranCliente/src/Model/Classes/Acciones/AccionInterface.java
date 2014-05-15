package Model.Classes.Acciones;// Created by Hanto on 05/05/2014.

import Controller.Controlador;
import Model.Classes.Mobiles.Player;
import Model.Classes.UI.Input.PlayerEstado;

public interface AccionInterface
{
    public void accionKeyDown(Player player, PlayerEstado playerE, Controlador controlador);
    public void accionKeyUp(Player player, PlayerEstado playerE, Controlador controlador);
}