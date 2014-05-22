package Model.Classes.Geo;// Created by Hanto on 19/05/2014.

import Data.MiscData;
import Model.DAO.DAO;
import Model.DAO.Terreno.TerrenoDAO;

public class SubMapaBORRAME
{
    private Celda[][] matriz;

    //mapTile, cuadrante que se renderiza
    public int mapTileX;
    public int mapTileY;
    //tile origen y final que lo componen
    private int tileOrigenX;
    private int tileFinalX;
    private int tileOrigenY;
    private int tileFinalY;
    //numero de Tiles de ancho y alto
    private int numTilesX;
    private int numTilesY;


    public SubMapaBORRAME(int numTilesX, int numTilesY)
    {
        this.numTilesX = numTilesX;
        this.numTilesY = numTilesY;

        matriz = new Celda[numTilesX][numTilesY];
        for (Celda[] fila: matriz)
        {   for (int i=0; i<fila.length; i++)
            {   fila[i] = new Celda(); }
        }
    }

    public void ajustarCoordenadas(int mapTileOrigenX, int mapTileOrigenY)
    {
        this.mapTileX = mapTileOrigenX;
        this.mapTileY = mapTileOrigenY;

        this.tileOrigenX = mapTileOrigenX * numTilesX;
        this.tileOrigenY = mapTileOrigenY * numTilesY;
        this.tileFinalX = mapTileOrigenX * numTilesX + numTilesX;
        this.tileFinalY = mapTileOrigenY * numTilesY + numTilesY;
    }

    public Terreno getTerreno (int x, int y, int numCapa)
    {
        TerrenoDAO terrenoDAO = DAO.terrenoDAOFactory.getTerrenoDAO();
        return terrenoDAO.getTerreno(matriz[x-tileOrigenX][y-tileOrigenY].getTerrenoID(numCapa));
    }

    public int getTerrenoID (int x, int y, int numCapa)
    {   return matriz[x-tileOrigenX][y-tileOrigenY].getTerrenoID(numCapa); }

    public boolean setTerreno (int x, int y, int numCapa, Terreno terreno)
    {
        if (x<0 || y<0 || x>= MiscData.MAPA_Max_TilesX || y>= MiscData.MAPA_Max_TilesY) return false;
        else
        {
            matriz[x-tileOrigenX][y-tileOrigenY].setTerreno(numCapa, terreno);
            return true;
        }
    }

    public boolean setTerreno (int x, int y, int numCapa, int iDTerreno)
    {
        if (x<0 || y<0 || x>= MiscData.MAPA_Max_TilesX || y>= MiscData.MAPA_Max_TilesY) return false;
        else
        {
            if (matriz[x-tileOrigenX][y-tileOrigenY].setTerreno(numCapa, iDTerreno))
            {   return true; }
            else return false;
        }
    }

}
