package Model.Geo;// Created by Hanto on 14/04/2014.

import Data.MiscData;
import Interfaces.MapaI;
import Model.DAO.DAO;
import Model.DAO.Terreno.TerrenoDAO;

public class Mapa implements MapaI
{
    private Celda[][] matriz = new Celda[MiscData.MAPA_Max_X][MiscData.MAPA_Max_Y];

    public Mapa()
    {
        for (Celda[] fila: matriz)
        {   for (int i=0; i<fila.length; i++)
            {   fila[i] = new Celda(); }
        }
    }

    public Terreno getTerreno (int x, int y, int numCapa)
    {
        TerrenoDAO terrenoDAO = DAO.terrenoDAO.newInstance();
        return terrenoDAO.getTerreno(matriz[x][y].getTerrenoID(numCapa));
    }

    public int getTerrenoID (int x, int y, int numCapa)
    {
        return matriz[x][y].getTerrenoID(numCapa);
    }

    public boolean setTerreno (int x, int y, int numCapa, Terreno terreno)
    {
        if (x<0 || y<0 || x> MiscData.MAPA_Max_X || y> MiscData.MAPA_Max_Y) return false;
        else
        {
            matriz[x][y].setTerreno(numCapa, terreno);
            return true;
        }
    }

    @Override public boolean setTerreno (int x, int y, int numCapa, int iDTerreno)
    {
        if (x<0 || y<0 || x> MiscData.MAPA_Max_X || y> MiscData.MAPA_Max_Y) return false;
        else { return matriz[x][y].setTerreno(numCapa, iDTerreno); }
    }
}
