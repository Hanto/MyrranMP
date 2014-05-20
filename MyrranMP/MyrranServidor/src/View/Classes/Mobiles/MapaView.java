package View.Classes.Mobiles;// Created by Hanto on 20/05/2014.

import Controller.Controlador;
import DTO.NetDTO;
import Data.MiscData;
import Model.Classes.Mobiles.PC;
import Model.GameState.Mundo;

public class MapaView
{
    private PC PC;
    private Mundo mundo;
    private Controlador controlador;

    private int numSubMapasX;
    private int numSubMapasY;
    private int numTilesX;
    private int numTilesY;

    private int mapTileCentroX;
    private int mapTileCentroY;

    private int datosHorizontales = 0;
    private int datosVerticales = 0;

    private SubMapaCoordenadas[][] matrizSubMapas;

    private static class SubMapaCoordenadas
    {
        public int offsetMapTileX;
        public int offsetMapTileY;
    }

    public MapaView (PC pc, Mundo mundo, Controlador controlador, int numSubMapasX, int numSubMapasY)
    {
        this.PC = pc;
        this.mundo = mundo;
        this.controlador = controlador;

        this.numSubMapasX = numSubMapasX+2;
        this.numSubMapasY = numSubMapasY+2;

        this.numTilesX = (int)Math.ceil((double)MiscData.GDX_Window_Horizontal_Resolution/(double)(numSubMapasX -1)/(double)MiscData.TILESIZE);
        this.numTilesY = (int)Math.ceil((double)MiscData.GDX_Window_Vertical_Resolution/(double)(numSubMapasY -1)/(double)MiscData.TILESIZE);

        matrizSubMapas = new SubMapaCoordenadas[this.numSubMapasY][this.numSubMapasX];
        for (SubMapaCoordenadas[] fila :matrizSubMapas)
        {   for (int i=0; i< fila.length; i++)
            { fila[i] = new SubMapaCoordenadas(); }
        }

        setPosicionRelativa();
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
        else yInicial = (numSubMapasY-1) /2;


        for (int y = 0; y < numSubMapasY; y++)
        {
            for (int x = 0; x< numSubMapasX; x++)
            {   matrizSubMapas[y][x].offsetMapTileX = x+xInicial;
                matrizSubMapas[y][x].offsetMapTileY = yInicial-y;
            }
        }

        for (SubMapaCoordenadas[] fila :matrizSubMapas)
        {   for (int i=0; i< fila.length; i++)
            { System.out.print("["+fila[i].offsetMapTileX +" "+fila[i].offsetMapTileY+"]"); }
            System.out.println("");
        }

    }


    public void init ()
    {
        for (int y = 1; y < numSubMapasY-1; y++)
        {
            for (int x = 1; x< numSubMapasX-1; x++)
            {
                System.out.println("Init: ["+matrizSubMapas[y][x].offsetMapTileX+" "+matrizSubMapas[y][x].offsetMapTileY+"]");
                actualizarMapa(matrizSubMapas[y][x]);
            }
        }
        mapTileCentroX = 0;
        mapTileCentroY = 0;
    }

    private int getTileX()  { return (int)((float)PC.getX() / (float)(numTilesX * MiscData.TILESIZE)); }
    private int getTileY()  { return (int)((PC.getY() / (float)(numTilesY * MiscData.TILESIZE))); }

    public void comprobarVistaMapa ()
    {
        if (PC.getX()-mapTileCentroX*numTilesX*MiscData.TILESIZE >= 800/2)
        {
            if (datosHorizontales != 1)
            {
                System.out.println("getTileX: "+getTileX());
                actualizarMapa(matrizSubMapas[1][3]);
                actualizarMapa(matrizSubMapas[2][3]);
                datosHorizontales = 1;
            }
            if (PC.getX()-mapTileCentroX*numTilesX*MiscData.TILESIZE >= 1600)
            {
                mapTileCentroX++;
                datosHorizontales = 0;
            }
        }

        if (PC.getX()-mapTileCentroX*numTilesX*MiscData.TILESIZE <= -800/2)
        {

            if (datosHorizontales != -1)
            {
                System.out.println("getTileX: "+getTileX());
                actualizarMapa(matrizSubMapas[1][2]);
                actualizarMapa(matrizSubMapas[2][0]);
                datosHorizontales = -1;
            }
            if (PC.getX()-mapTileCentroX*numTilesX*MiscData.TILESIZE <= -1600)
            {
                mapTileCentroX--;
                datosHorizontales = 0;
            }
        }
    }

    public void actualizarMapa (SubMapaCoordenadas submapaCoorddenadas)
    {   actualizarMapa(getTileX()+submapaCoorddenadas.offsetMapTileX, getTileY()+submapaCoorddenadas.offsetMapTileY); }


    public void actualizarMapa (int mapTileInicialX, int mapTileInicialY)
    {
        if (mapTileInicialX <0 || mapTileInicialY < 0) return;

        NetDTO.ActualizarMapa actualizarMapa = new NetDTO.ActualizarMapa(mapTileInicialX*numTilesX, mapTileInicialY*numTilesY, numTilesX, numTilesY);
        for (int x=0; x< numTilesX; x++)
        {
            for (int y = 0; y< numTilesY; y++)
            {
                for (int i=0; i<MiscData.MAPA_Max_Capas_Terreno; i++)
                {   actualizarMapa.mapa[x][y].celda[i] = (short)mundo.getMapa().getTerrenoID(x+mapTileInicialX*numTilesX, y+mapTileInicialY*numTilesY, i); }
            }
        }
        controlador.enviarACliente(PC.getConnectionID(), actualizarMapa);
        System.out.println("actualizarMapa: "+actualizarMapa.esquinaInfIzdaX+" "+actualizarMapa.esquinaInfIzdaY);
    }
}
