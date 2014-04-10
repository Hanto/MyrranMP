package Modelo.Mobiles;// Created by Hanto on 07/04/2014.

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface MundoModel
{
    //GET:
    public ArrayList<? extends PCModel> listaPlayers();
    public PlayerModel getPlayer();
    //SET(Controlador)
    public void añadirPlayer(int connectionID);
    public void eliminarPlayer();
    public void moverPlayer(float x, float y);
    public void añadirPC(int connectionID, float x, float y);
    public void eliminarPC(int connectionID);
    public void moverPC(int connectionID, float x, float y);
    //OBSERVADOR:
    public void añadirObservador(PropertyChangeListener observador);
    public void eliminarObservador(PropertyChangeListener observador);
}
