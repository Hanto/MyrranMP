package Models;// Created by Hanto on 07/04/2014.

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface MundoModel
{   //GET:
    public ArrayList<? extends PCModel> listaPlayers();
    //SET(Controlador)
    public void añadirPC(int connectionID, float x, float y);
    public void eliminarPC(int connectionID);
    public void moverPC(int connectionID, float x, float y);
    public void cambiarAnimacionPC(int connectionID, int numAnimacion);
    //OBSERVADOR:
    public void añadirObservador(PropertyChangeListener observador);
    public void eliminarObservador(PropertyChangeListener observador);
}
