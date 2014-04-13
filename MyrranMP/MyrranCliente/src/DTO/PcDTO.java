package DTO;// Created by Hanto on 09/04/2014.

import Modelo.Mobiles.PcModel;
import Modelo.Mobiles.PlayerModel;

public class PcDTO
{
    public static class MundoAñadirPC
    {
        public PcModel pcModel;
        public float x;
        public float y;
        public MundoAñadirPC(PcModel pcModel, float x, float y)
        {   this.pcModel = pcModel; this.x = x; this.y = y; }
    }

    public static class MoverPC
    {
        public PcModel pcModel;
        public float x;
        public float y;
        public MoverPC(PcModel pcModel, float x, float y)
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
        public PcModel pcModel;
        public EliminarPC(PcModel pcModel)
        {   this.pcModel = pcModel; }
    }
}
