package Controller.Input;// Created by Hanto on 10/04/2014.

import Controller.ControladorI;
import Controller.Network.NetClient;

public interface ControladorCliente extends ControladorI
{
    public NetClient getNetIO();

    public void añadirPlayer (int connectionID);
    public void eliminarPlayer ();
    public void moverPlayer (float x, float y);
}
