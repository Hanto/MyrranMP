package Modelo.DTO;// Created by Hanto on 09/04/2014.

import Modelo.Mobiles.PCModel;

public class PcDTO
{
    public static class PositionPC
    {
        public PCModel pcModel;
        public float x;
        public float y;
        public PositionPC(PCModel pcModel, float x, float y)
        {   this.pcModel = pcModel; this.x = x; this.y = y; }
    }

    public static class AnimacionPC
    {
        public int numAnimacion;
        public AnimacionPC(int numAnimacion)
        {   this.numAnimacion = numAnimacion; }
    }

    public static class EliminarPC
    {
        public PCModel pcModel;
        public EliminarPC(PCModel pcModel)
        {   this.pcModel = pcModel; }
    }
}
