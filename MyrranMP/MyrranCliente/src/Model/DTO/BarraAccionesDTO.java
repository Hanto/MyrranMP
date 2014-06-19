package Model.DTO;// Created by Hanto on 07/05/2014.

import Model.Classes.Acciones.Accion;
import Model.Classes.UI.BarraAcciones;

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

    public static class EliminarFilaDTO
    {
        public int numFilas;
        public EliminarFilaDTO(int numFilas)
        {   this.numFilas = numFilas;}
    }

    public static class AñadirFilaDTO
    {
        public int numFilas;
        public AñadirFilaDTO(int numFilas)
        {   this.numFilas = numFilas;}
    }

    public static class EliminarColumnaDTO
    {
        public int numColumnas;
        public EliminarColumnaDTO(int numColumnas)
        {   this.numColumnas = numColumnas; }
    }

    public static class AñadirColumnaDTO
    {
        public int numColumnas;
        public AñadirColumnaDTO(int numColumnas)
        {   this.numColumnas = numColumnas; }
    }

    public static class AñadirBarraAcciones
    {
        public BarraAcciones barraAcciones;
        public AñadirBarraAcciones(BarraAcciones barraAcciones)
        {   this.barraAcciones = barraAcciones; }
    }
}
