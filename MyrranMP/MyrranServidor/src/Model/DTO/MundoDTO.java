package Model.DTO;// Created by Hanto on 14/04/2014.

import Model.Classes.Mobiles.PC;

public class MundoDTO
{
    public static class AñadirPC
    {
        public PC PC;
        public float x;
        public float y;
        public AñadirPC(PC PC, float x, float y)
        {   this.PC = PC; this.x = x; this.y = y; }
    }
}
