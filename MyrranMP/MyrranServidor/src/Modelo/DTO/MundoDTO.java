package Modelo.DTO;// Created by Hanto on 14/04/2014.

import Modelo.Mobiles.PcModel;

public class MundoDTO
{
    public static class AñadirPC
    {
        public PcModel pcModel;
        public float x;
        public float y;
        public AñadirPC(PcModel pcModel, float x, float y)
        {   this.pcModel = pcModel; this.x = x; this.y = y; }
    }
}
