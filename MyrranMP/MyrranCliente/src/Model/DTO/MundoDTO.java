package Model.DTO;// Created by Hanto on 14/04/2014.

import Model.Mobiles.PCModel;

public class MundoDTO
{
    public static class AñadirPC
    {
        public PCModel pcModel;
        public float x;
        public float y;
        public AñadirPC(PCModel pcModel, float x, float y)
        {   this.pcModel = pcModel; this.x = x; this.y = y; }
    }
}
