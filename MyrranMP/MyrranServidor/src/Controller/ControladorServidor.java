package Controller;// Created by Hanto on 10/04/2014.

import Controller.Network.NetServer;

public interface ControladorServidor extends ControladorI
{
    public NetServer getNetIO();
}
