package Interfaces.UI;// Created by Hanto on 13/05/2014.

public interface BarraAccionesI extends ListaAccionesI
{
    public int getID();
    public void eliminar();

    public void setAccion(int posX, int posY, AccionI accion);
    public void eliminarAccion(int posX, int posY);

    public String getKeybind (int posX, int posY);
    public void setKeycode (int posX, int posY, int keycode);
    public void eliminarKeycode(int keycode);

    public void eliminarFila(int numFilas);
    public void añadirFila(int numFilas);
    public void eliminarColumna(int numColumnas);
    public void añadirColumna(int numColumnas);
}
