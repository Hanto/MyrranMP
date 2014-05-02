package Model.DTO;// Created by Hanto on 09/04/2014.

import Model.Classes.Mobiles.PC;

public class PcDTO
{
    public static class PositionPC
    {
        public PC pc;
        public float x;
        public float y;
        public PositionPC(PC pc, float x, float y)
        {   this.pc = pc; this.x = x; this.y = y; }
    }

    public static class AnimacionPC
    {
        public int numAnimacion;
        public AnimacionPC(int numAnimacion)
        {   this.numAnimacion = numAnimacion; }
    }

    public static class EliminarPC
    {
        public PC pc;
        public EliminarPC(PC pc)
        {   this.pc = pc; }
    }
}
