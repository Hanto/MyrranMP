package Interfaces.UI;// Created by Hanto on 05/05/2014.

import Interfaces.Entidades.MobPlayer;

public interface AccionI
{
    //GET:
    public String getID();

    //METODOS:
    public void accionKeyDown(MobPlayer player, PlayerEstadoI playerE, ControladorUI controlador);
    public void accionKeyUp(MobPlayer player, PlayerEstadoI playerE, ControladorUI controlador);
}