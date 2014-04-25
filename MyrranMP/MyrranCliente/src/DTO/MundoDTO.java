package DTO;// Created by Hanto on 14/04/2014.

import Model.Mobiles.PC;

public class MundoDTO
{
    public static class AñadirPC
    {
        public PC pc;
        public float x;
        public float y;
        public AñadirPC(PC pc, float x, float y)
        {   this.pc = pc; this.x = x; this.y = y; }
    }
}
