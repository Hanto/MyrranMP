package Model.Classes.Geo;// Created by Hanto on 19/05/2014.

import Data.MiscData;

public class MapaSeamless
{
    private SubMapaCoordenadas[][] matrizSubMapas;

    private float xActual;
    private float yActual;

    private int mapTileBordeE;
    private int mapTileBordeO;
    private int mapTileBordeN;
    private int mapTileBordeS;

    private int numSubMapasX;
    private int numSubMapasY;

    private int numTilesX;
    private int numTilesY;

    private static class SubMapaCoordenadas
    {
        public int mapTileX;
        public int mapTileY;
        public SubMapa subMapa;
        public SubMapaCoordenadas(int numTilesX, int numTilesY)
        {   this.subMapa = new SubMapa(numTilesX, numTilesY); }
    }

    public MapaSeamless(int numSubMapasX, int numSubMapasY)
    {
        this.numSubMapasX = numSubMapasX;
        this.numSubMapasY = numSubMapasY;

        this.numTilesX = (int)Math.ceil((double)MiscData.GDX_Window_Horizontal_Resolution/(double)(numSubMapasX -1)/(double)MiscData.TILESIZE);
        this.numTilesY = (int)Math.ceil((double)MiscData.GDX_Window_Vertical_Resolution/(double)(numSubMapasY -1)/(double)MiscData.TILESIZE);

        matrizSubMapas = new SubMapaCoordenadas[numSubMapasX +1][numSubMapasY +1];
        for (SubMapaCoordenadas[] fila :matrizSubMapas)
        {   for (int i=0; i< fila.length; i++)
            { fila[i] = new SubMapaCoordenadas(numTilesX, numTilesY); }
        }

        setPosicionRelativa();

        for (SubMapaCoordenadas[] fila :matrizSubMapas)
        {   for (int i=0; i< fila.length; i++)
        { System.out.print("["+fila[i].mapTileX +" "+fila[i].mapTileY +"]"); }
            System.out.println("");
        }
    }

    public void setPosicionRelativa()
    {
        int xInicial, yInicial;

        //tamañoX Impar:
        if (numSubMapasX %2 > 0) xInicial = -(numSubMapasX -1)/2 -1;
        //tamañoX Par:
        else xInicial = -numSubMapasX /2;
        //TamañoY Impar:
        if (numSubMapasY %2 >0) yInicial = (numSubMapasY -1)/2;
        //TamañoY Par:
        else yInicial = numSubMapasY /2;

        for (int y = 0; y < numSubMapasX +1; y++)
        {
            for (int x = 0; x< numSubMapasY +1; x++)
            {   matrizSubMapas[y][x].mapTileX = x+xInicial;
                matrizSubMapas[y][x].mapTileY = yInicial-y;
            }
        }
    }

}
