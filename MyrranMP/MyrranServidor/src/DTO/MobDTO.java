package DTO;// Created by Hanto on 09/04/2014.

import Modelo.Mobiles.PCModel;

public class MobDTO
{
    public static class MundoAñadirPC
    {
        public PCModel pcModel;
        public float x;
        public float y;
        public MundoAñadirPC(PCModel pcModel, float x, float y)
        {   this.pcModel = pcModel; this.x = x; this.y = y; }
    }

    public static class MoverPC
    {
        public PCModel pcModel;
        public float x;
        public float y;
        public MoverPC(PCModel pcModel, float x, float y)
        {   this.pcModel = pcModel; this.x = x; this.y = y; }
    }

    public static class CambiarAnimacionPC
    {
        public int numAnimacion;
        public CambiarAnimacionPC(int numAnimacion)
        {   this.numAnimacion = numAnimacion; }
    }

    public static class EliminarPC
    {
        public PCModel pcModel;
        public EliminarPC(PCModel pcModel)
        {   this.pcModel = pcModel; }
    }
}
