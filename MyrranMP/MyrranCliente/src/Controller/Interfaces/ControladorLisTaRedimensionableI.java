package Controller.Interfaces;// Created by Hanto on 14/05/2014.

import Model.Classes.UIO.ConjuntoBarraAcciones.ListaRedimensionableI;

public interface ControladorLisTaRedimensionableI
{
    public void barraAñadirColumna(ListaRedimensionableI barra);
    public void barraAñadirFila (ListaRedimensionableI barra);
    public void barraEliminarColumna (ListaRedimensionableI barra);
    public void barraEliminarFila (ListaRedimensionableI barra);
}
