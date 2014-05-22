package Model.Classes.Geo;// Created by Hanto on 19/05/2014.

import Data.MiscData;
import Interfaces.Mob;
import Model.DTO.PlayerDTO;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MapaSeamless implements PropertyChangeListener
{
    private int[][] mapa = new int[10][10];

    private Mob mob;

    private int mapTileCentroX = 0;
    private int mapTileCentroY = 0;

    private int numTilesX;
    private int numTilesY;


    public MapaSeamless(Mob mob)
    {
        this.mob = mob;
        mob.añadirObservador(this);

        this.numTilesX = (int)Math.ceil((double)MiscData.GDX_Window_Horizontal_Resolution/(double)MiscData.TILESIZE);
        this.numTilesY = (int)Math.ceil((double)MiscData.GDX_Window_Vertical_Resolution/(double)MiscData.TILESIZE);

        for (int y=0; y<10; y++)
        {
            for (int x=0; x<10; x++)
            {
                mapa[x][y] = x*y;
            }
        }

        for (int y=0; y<10; y++)
        {
            for (int x=0; x<10; x++)
            {
                System.out.print("["+mapa[x][y]+"]");
            }
            System.out.println("");
        }
    }

    private int getMapTileX()                                       { return (int)((mob.getX() / (float)(numTilesX * MiscData.TILESIZE))); }
    private int getMapTileY()                                       { return (int)((mob.getY() / (float)(numTilesY * MiscData.TILESIZE))); }

    private void desplazarArray (int incX, int incY)
    {
        if (incX > 0)   //Desplazar IZDA:
        {
            int[][] temp = new int[mapa.length][mapa[0].length];

            System.arraycopy(mapa, incX,                temp, 0,                    mapa.length-incX);
            System.arraycopy(mapa, 0,                   temp, temp.length-incX,     incX);
            mapa = temp;
        }

        if (incX < 0)   //Desplazar DCHA:
        {
            incX = -incX;
            int[][] temp = new int[mapa.length][mapa[0].length];

            System.arraycopy(mapa,  0,                  temp, incX,                 mapa.length-incX);
            System.arraycopy(mapa, temp.length-incX,    temp, 0,                    incX);
            mapa = temp;
        }

        if (incY > 0)   //Desplazar ABAJO:
        {
            for (int i=0; i< mapa.length; i++)
            {
                int[] tempo = new int[mapa[i].length];
                System.arraycopy(mapa[i], 0,                    tempo, incY,                mapa[i].length-incY);
                System.arraycopy(mapa[i], tempo.length-incY,    tempo, 0,                   incY);
                mapa[i]= tempo;
            }
        }

        if (incY < 0)   //Desplazar ARRIBA:
        {
            incY = -incY;
            for (int i=0; i< mapa.length; i++)
            {
                int[] tempo = new int[mapa[i].length];
                System.arraycopy(mapa[i], incY,                 tempo, 0,                   mapa[i].length-incY);
                System.arraycopy(mapa[i], 0,                    tempo, tempo.length-incY,   incY);
                mapa[i] = tempo;
            }
        }

        for (int y=0; y<10; y++)
        {
            for (int x=0; x<10; x++)
            {
                System.out.print("["+mapa[x][y]+"]");
            }
            System.out.println("");
        }
    }

    public void moverTile()
    {
        if      (getMapTileX() > mapTileCentroX)    { desplazarArray( 2,     0); mapTileCentroX++; }
        else if (getMapTileX() < mapTileCentroX)    { desplazarArray(-2,     0); mapTileCentroX--; }
        else if (getMapTileY() > mapTileCentroY)    { desplazarArray( 0,             2); mapTileCentroY++; }
        else if (getMapTileY() < mapTileCentroY)    { desplazarArray( 0,            -2); mapTileCentroY--; }
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof PlayerDTO.PositionPlayer)
        {   moverTile(); }

    }
}
