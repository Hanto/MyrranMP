package Model.Classes.UIO.EntornoAcciones;// Created by Hanto on 13/05/2014.

import Model.Classes.Acciones.Accion;

public interface ListaAccionesBI extends ListaAccionesI
{
    public void setAccion(int posX, int posY, Accion accion);
    public void eliminarAccion(int posX, int posY);

    public String getKeybind (int posX, int posY);
    public void setKeycode (int posX, int posY, int keycode);
    public void eliminarKeycode(int keycode);

    public void eliminarFila();
    public void añadirFila();
    public void eliminarColumna();
    public void añadirColumna();
}
