package Interfaces;// Created by Hanto on 22/05/2014.

import java.beans.PropertyChangeListener;

public interface Model
{
    public void notificarActualizacion(String nombreValor, Object viejoValor, Object nuevoValor);
    public void añadirObservador(PropertyChangeListener observador);
    public void eliminarObservador(PropertyChangeListener observador);
}
