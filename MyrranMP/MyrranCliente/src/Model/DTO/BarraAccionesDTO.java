package Model.DTO;// Created by Hanto on 07/05/2014.

import Model.Classes.Acciones.Accion;

public class BarraAccionesDTO
{
    public static class SetAccionDTO
    {
        public int posX;
        public int posY;
        public Accion accion;
        public SetAccionDTO(int posX, int posY)
        {   this.posX = posX; this.posY = posY;}
    }

    public static class EliminarAccionDTO
    {
        public int posX;
        public int posY;
        public EliminarAccionDTO(int posX, int posY)
        {   this.posX = posX; this.posY = posY;}
    }

    public static class EliminarCasillaDTO
    {
        public int posX;
        public int posY;
        public EliminarCasillaDTO(int posX, int posY)
        { this.posX = posX; this.posY = posY;}
    }
}
