package DTO;// Created by Ladrim on 23/04/2014.

public class MapaDTO
{
    public static class SetTerreno
    {
        public int celdaX;
        public int celdaY;
        public int numCapa;
        public int iDTerreno;
        public SetTerreno() {}
        public SetTerreno(int celdaX, int celdaY, int numCapa, int iDTerreno)
        {   this.celdaX = celdaX; this.celdaY = celdaY; this.numCapa = numCapa; this.iDTerreno = iDTerreno; }
    }
}
