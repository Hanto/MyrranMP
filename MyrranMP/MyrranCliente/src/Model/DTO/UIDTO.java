package Model.DTO;// Created by Hanto on 07/05/2014.

import Model.Classes.UIO.ConjuntoBarraAcciones.BarraAcciones;

public class UIDTO
{
    public static class AñadirBarraAccionesDTO
    {
        public BarraAcciones barraAcciones;
        public AñadirBarraAccionesDTO(BarraAcciones barraAcciones)
        {   this.barraAcciones = barraAcciones; }
    }
}
