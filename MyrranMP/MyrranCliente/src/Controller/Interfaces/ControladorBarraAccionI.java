package Controller.Interfaces;// Created by Hanto on 14/05/2014.

import Model.Classes.UI.BarraAcciones.BarraAccionesI;
import Model.Classes.UI.BarraAcciones.ListaAccionesI;

public interface ControladorBarraAccionI
{
    public void añadirBarraAcciones(int filas, int columnas);
    public void eliminarBarraAcciones(BarraAccionesI barra);
    public void barraAccionMoverAccion(ListaAccionesI barraOrigen, int posXOrigen, int posYOrigen, ListaAccionesI barraDestino,int posXDestino, int posYDestino);
    public void barraAccionRebindear(BarraAccionesI barra, int posX, int posY, int keycode);

    public void barraAñadirColumna(BarraAccionesI barra, int numColumnas);
    public void barraAñadirFila (BarraAccionesI barra, int numFilas);
    public void barraEliminarColumna (BarraAccionesI barra, int numColumnas);
    public void barraEliminarFila (BarraAccionesI barra, int numFilas);
}
