package Interfaces.UI.Acciones;// Created by Hanto on 05/05/2014.

import Interfaces.EntidadesTipos.MobPlayer;
import Interfaces.UI.Input.ControladorUI;
import Interfaces.UI.Input.PlayerEstadoI;

public interface AccionI
{
    //GET:
    public String getID();

    //METODOS:
    public void accionKeyDown(MobPlayer player, PlayerEstadoI playerE, ControladorUI controlador);
    public void accionKeyUp(MobPlayer player, PlayerEstadoI playerE, ControladorUI controlador);
}