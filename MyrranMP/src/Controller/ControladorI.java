package Controller;// Created by Hanto on 07/04/2014.

import Models.MundoModel;

public interface ControladorI
{
    //GET
    public MundoModel getMundo();
    //SET
    public void añadirPC (int connectionID, float x, float y);
    public void eliminarPC (int connectionID);
    public void moverPC (int connectionID, float x, float y);
    public void cambiarAnimacionPC (int connectionID, int numAnimacion);

    public void netUpdater ();
}
