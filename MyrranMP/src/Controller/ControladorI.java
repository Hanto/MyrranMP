package Controller;// Created by Hanto on 07/04/2014.

public interface ControladorI
{
    //SET
    public void añadirPC (int connectionID, float x, float y);
    public void eliminarPC (int connectionID);
    public void moverPC (int connectionID, float x, float y);
    public void cambiarAnimacionPC (int connectionID, int numAnimacion);

    public void netUpdater ();
}
