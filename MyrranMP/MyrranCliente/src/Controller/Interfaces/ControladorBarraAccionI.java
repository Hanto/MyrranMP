package Controller.Interfaces;// Created by Hanto on 14/05/2014.

import Model.Classes.UIO.ConjuntoBarraAcciones.ListaAccionesBI;
import Model.Classes.UIO.ConjuntoBarraAcciones.ListaAccionesI;

public interface ControladorBarraAccionI
{
    public void añadirBarraAcciones(int filas, int columnas);
    public void barraAccionMoverAccion(ListaAccionesI barraOrigen, int posXOrigen, int posYOrigen, ListaAccionesI barraDestino,int posXDestino, int posYDestino);
    public void barraAccionRebindear(ListaAccionesBI barra, int posX, int posY, int keycode);
    public void barraAñadirColumna(ListaAccionesBI barra);
    public void barraAñadirFila (ListaAccionesBI barra);
    public void barraEliminarColumna (ListaAccionesBI barra);
    public void barraEliminarFila (ListaAccionesBI barra);
}
