package Controller.Interfaces;// Created by Hanto on 14/05/2014.

import Model.Classes.UI.ConjuntoBarraAcciones.ListaAccionesBI;
import Model.Classes.UI.ConjuntoBarraAcciones.ListaAccionesI;

public interface ControladorBarraAccionI extends ControladorLisTaRedimensionableI
{
    public void añadirBarraAcciones(int filas, int columnas);
    public void eliminarBarraAcciones(ListaAccionesBI barra);
    public void barraAccionMoverAccion(ListaAccionesI barraOrigen, int posXOrigen, int posYOrigen, ListaAccionesI barraDestino,int posXDestino, int posYDestino);
    public void barraAccionRebindear(ListaAccionesBI barra, int posX, int posY, int keycode);
}
