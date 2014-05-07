package Model.Classes.Acciones;// Created by Hanto on 05/05/2014.

import Model.Classes.UIO.PlayerEstado;
import Model.Classes.Mobiles.Player;

public interface AccionInterface
{
    public void accionKeyDown(Player player, PlayerEstado playerE);
    public void accionKeyUp(Player player, PlayerEstado playerE);
}