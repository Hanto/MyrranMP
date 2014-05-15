package Controller.Interfaces;// Created by Hanto on 14/05/2014.

import Model.Classes.UI.ConjuntoBarraAcciones.ListaRedimensionableI;

public interface ControladorLisTaRedimensionableI
{
    public void barraAñadirColumna(ListaRedimensionableI barra, int numColumnas);
    public void barraAñadirFila (ListaRedimensionableI barra, int numFilas);
    public void barraEliminarColumna (ListaRedimensionableI barra, int numColumnas);
    public void barraEliminarFila (ListaRedimensionableI barra, int numFilas);
}
