package Model.DTO;// Created by Hanto on 07/05/2014.

import Model.Classes.Acciones.Accion;

public class BarraAccionesDTO
{
    public static class SetAccionDTO
    {
        public int posicion;
        public Accion accion;
        public SetAccionDTO(int posicion)
        {   this.posicion = posicion;}
    }

    public static class RemoveAccionDTO
    {
        public int posicion;
        public RemoveAccionDTO(int posicion)
        {   this.posicion = posicion; }
    }
}
