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

    private int numTilesX;
    private int numTilesY;

    private int mapTileCentroX = -20;
    private int mapTileCentroY = -20;

    private int posicionHoritontal = 0;
    private int posicionVertical = 0;

    private int franjaHorizontal;
    private int franjaVertical;

    private SubMapaCoordenadas[][] mapa = new SubMapaCoordenadas[3][3];

    private static class SubMapaCoordenadas
    {
        public int offsetMapTileX;
        public int offsetMapTileY;
        public boolean enviado;
        public void setCoordenadas(int x, int y)
        {   this.offsetMapTileX = x; this.offsetMapTileY = y; }
    }

    public MapaView (PC pc, Mundo mundo, Controlador controlador)
    {
        this.PC = pc;
        this.mundo = mundo;
        this.controlador = controlador;

        this.numTilesX = (int)Math.ceil((double)MiscData.GDX_Window_Horizontal_Resolution/(double)MiscData.TILESIZE);
        this.numTilesY = (int)Math.ceil((double)MiscData.GDX_Window_Vertical_Resolution/(double)MiscData.TILESIZE);


        for (SubMapaCoordenadas[] fila :mapa)
        {   for (int i=0; i< fila.length; i++)
            { fila[i] = new SubMapaCoordenadas(); }
        }

        setOffsets();
    }

    public void setOffsets()
    {
        mapa[0][0].setCoordenadas(-1, +1);
        mapa[1][0].setCoordenadas( 0, +1);
        mapa[2][0].setCoordenadas(+1, +1);

        mapa[0][1].setCoordenadas(-1,  0);
        mapa[1][1].setCoordenadas( 0,  0);
        mapa[2][1].setCoordenadas(+1,  0);

        mapa[0][2].setCoordenadas(-1, -1);
        mapa[1][2].setCoordenadas( 0, -1);
        mapa[2][2].setCoordenadas(+1, -1);

        SubMapaCoordenadas[] temp;


            temp = mapa[0];
            System.arraycopy(mapa, 1, mapa, 0, mapa.length-1);
            mapa[mapa.length-1] = temp;

        for (int x = 0; x < mapa.length; x++)
        {
            for (int y = 0; y < mapa[x].length; y++)
            { System.out.print("["+mapa[y][x].offsetMapTileX +" "+mapa[y][x].offsetMapTileY+"]"); }
            System.out.println("");
        }
    }


    public void init ()
    {
        mapTileCentroX = getTileX();
        mapTileCentroY = getTileY();
    }

    private int getTileX()  { return (int)((PC.getX() / (float)(numTilesX * MiscData.TILESIZE))); }
    private int getTileY()  { return (int)((PC.getY() / (float)(numTilesY * MiscData.TILESIZE))); }

    public void comprobarVistaMapa ()
    {
        if (Math.abs(getTileX()-mapTileCentroX) >1 || Math.abs(getTileY()-mapTileCentroY) > 1)  { init(); return; }

        int distX = (int)PC.getX()-mapTileCentroX*numTilesX*MiscData.TILESIZE;
        int distY = (int)PC.getY()-mapTileCentroY*numTilesY*MiscData.TILESIZE;

        if (distX < 800/2)              { posicionHoritontal = -1; }
        else if (distX > 1600-800/2)    { posicionHoritontal = +1; }
        else                            { posicionHoritontal = 0; }

        if (distY < 450/2)              { posicionVertical = -1; }
        else if (distY > 900-450/2)     { posicionVertical = +1; }
        else                            { posicionVertical = 0; }

        if (posicionVertical != 0 && posicionHoritontal != 0)
        {
            actualizarMap(0, 0);
            actualizarMap(posicionHoritontal, 0);
            actualizarMap(0, posicionVertical);
            actualizarMap(posicionHoritontal, posicionVertical);
        }
        if (posicionVertical == 0 && posicionHoritontal != 0)
        {
            actualizarMap(0, 0);
            actualizarMap(posicionHoritontal, 0);
            actualizarMap(0, +1);
            actualizarMap(0, -1);
            actualizarMap(posicionHoritontal, +1);
            actualizarMap(posicionHoritontal, -1);
        }
        if (posicionHoritontal == 0 && posicionVertical != 0)
        {
            actualizarMap(0, 0);
            actualizarMap(+1, 0);
            actualizarMap(-1, 0);
            actualizarMap(0, posicionVertical);
            actualizarMap(+1, posicionVertical);
            actualizarMap(-1, posicionVertical);
        }
        if (posicionHoritontal == 0 && posicionVertical == 0)
        {
            actualizarMap(+1,+1);
            actualizarMap(+1, 0);
            actualizarMap(+1,-1);
            actualizarMap(0, +1);
            actualizarMap(0,  0);
            actualizarMap(0, -1);
            actualizarMap(-1,+1);
            actualizarMap(-1, 0);
            actualizarMap(-1,-1);
        }


        if      (getTileX() > mapTileCentroX)   { incrementarMapTile(1, 0); }
        else if (getTileX() < mapTileCentroX)   { incrementarMapTile(-1, 0); }
        else if (getTileY() > mapTileCentroY)   { incrementarMapTile(0, 1);  }
        else if (getTileY() < mapTileCentroY)   { incrementarMapTile(0, -1); }
    }

    public void actualizarMap (int x, int y)
    {
        int mX = x+1;
        int mY = -1*y+1;
        if (!mapa[mX][mY].enviado)
        {
            mapa[mX][mY].enviado = true;
            actualizarMapa(getTileX()+x, getTileY()+y);
        }
    }

    public void incrementarMapTile (int incX, int incY)
    {
        desplazarArray(incX, incY);

        if (incX >0)
        {
            mapa[2][0].enviado = false;
            mapa[2][1].enviado = false;
            mapa[2][2].enviado = false;
        }
        if (incX <0)
        {
            mapa[0][0].enviado = false;
            mapa[0][1].enviado = false;
            mapa[0][2].enviado = false;
        }
        if (incY >0)
        {
            mapa[0][0].enviado = false;
            mapa[1][0].enviado = false;
            mapa[2][0].enviado = false;
        }
        if (incY <0)
        {
            mapa[0][2].enviado = false;
            mapa[1][2].enviado = false;
            mapa[2][2].enviado = false;
        }

        mapTileCentroX += incX;
        mapTileCentroY += incY;

        for (int x = 0; x < mapa.length; x++)
        {
            for (int y = 0; y < mapa[x].length; y++)
            {
                if (x == 1 && y == 1) System.out.print("  "+mapa[y][x].enviado+"  ");
                else System.out.print("["+mapa[y][x].enviado+"]");
            }
            System.out.println("");
        }
        System.out.println("MaptileCentroX: "+mapTileCentroX+" MapTileCentroY: "+mapTileCentroY);
    }


    public void desplazarArray (int incX, int incY)
    {
        SubMapaCoordenadas[] temp;
        SubMapaCoordenadas tempo;

        if (incX > 0)
        {
            temp = mapa[0];
            System.arraycopy(mapa, 1, mapa, 0, mapa.length-1);
            mapa[mapa.length-1] = temp;

        }
        if (incX < 0)
        {
            temp = mapa[mapa.length-1];
            System.arraycopy(mapa, 0, mapa, 1, mapa.length-1);
            mapa[0] = temp;
        }
        if (incY < 0)
        {
            for (int i=0; i<mapa.length; i++)
            {
                tempo = mapa[i][0];
                System.arraycopy(mapa[i], 1, mapa[i], 0, mapa[i].length - 1);
                mapa[i][mapa.length-1] = tempo;
            }
        }
        if (incY > 0)
        {
            for (int i=0; i<mapa.length; i++)
            {
                tempo = mapa[i][mapa.length-1];
                System.arraycopy(mapa[i], 0, mapa[i], 1, mapa[i].length - 1);
                mapa[i][0] = tempo;
            }
        }
    }

    public void actualizarMapa (int mapTileInicialX, int mapTileInicialY)
    {
        System.out.println("actualizarMapa: ["+mapTileInicialX+" "+mapTileInicialY+"]");
        if (mapTileInicialX <0 || mapTileInicialY < 0) { return; }

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
    }
}
