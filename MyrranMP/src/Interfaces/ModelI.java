package Interfaces;// Created by Hanto on 22/05/2014.

import java.beans.PropertyChangeListener;

public interface ModelI
{
    public void notificarActualizacion(String nombreValor, Object viejoValor, Object nuevoValor);
    public void añadirObservador(PropertyChangeListener observador);
    public void eliminarObservador(PropertyChangeListener observador);
}
