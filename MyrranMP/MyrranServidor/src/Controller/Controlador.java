package Controller;// Created by Hanto on 07/04/2014.

import Controller.Network.NetServer;
import Controller.Network.NetUpdater;
import Modelo.Mobiles.MundoModel;
import View.Vista;

public class Controlador implements ControladorServidor
{
    protected NetServer servidor = new Servidor(this);            //Input principal de la simulacion
    protected NetUpdater netUpdater;
    protected MundoModel mundo;
    protected Vista vista;

    public Controlador (MundoModel mundo)
    {
        this.mundo = mundo;
        vista = new Vista(this, mundo);
        netUpdater = new NetUpdater(this);
    }

    @Override public MundoModel getMundo()                              { return mundo; }
    @Override public NetServer getNetIO()                               { return servidor; }

    @Override public void añadirPC (int connectionID, float x, float y) { mundo.añadirPC(connectionID, x, y); }
    @Override public void moverPC (int connectionID, float x, float y)  { mundo.moverPC(connectionID, x, y); }
    @Override public void eliminarPC (int connectionID)                 { mundo.eliminarPC(connectionID); }
    @Override public void cambiarAnimacionPC(int connectionID, int numAnimacion)
    {   mundo.cambiarAnimacionPC(connectionID, numAnimacion);}

    @Override public void netUpdater ()                                 { vista.netUpdate(); }
}
