package Models;// Created by Hanto on 07/04/2014.

import java.beans.PropertyChangeListener;

public interface PCModel
{
    //GET:
    public int getConnectionID();
    public float getX();
    public float getY();
    //OBSERVADOR:
    public void añadirObservador(PropertyChangeListener observador);
    public void eliminarObservador(PropertyChangeListener observador);
}
