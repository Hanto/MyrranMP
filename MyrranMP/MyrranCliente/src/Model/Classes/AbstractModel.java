package Model.Classes;// Created by Hanto on 09/04/2014.

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AbstractModel
{
    protected PropertyChangeSupport observado;

    public AbstractModel()
    {   observado = new PropertyChangeSupport(this); }

    public void notificarActualizacion(String nombreValor, Object viejoValor, Object nuevoValor)
    {   observado.firePropertyChange(nombreValor, viejoValor, nuevoValor); }

    public void añadirObservador(PropertyChangeListener observador)
    {   observado.addPropertyChangeListener(observador); }

    public void eliminarObservador(PropertyChangeListener observador)
    {   observado.removePropertyChangeListener(observador); }

}
