package Modelo.DTO;// Created by Hanto on 09/04/2014.


import Modelo.Mobiles.PcModel;

public class PcDTO
{
    public static class PositionPC
    {
        public float x;
        public float y;
        public PositionPC(float x, float y)
        {   this.x = x; this.y = y; }
    }

    public static class AnimacionPC
    {
        public int numAnimacion;
        public AnimacionPC(int numAnimacion)
        {   this.numAnimacion = numAnimacion; }
    }

    public static class EliminarPC
    {
        public PcModel pcModel;
        public EliminarPC(PcModel pcModel)
        {   this.pcModel = pcModel; }
    }
}
