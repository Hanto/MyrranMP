package Model.Geo;// Created by Hanto on 14/04/2014.

import Model.DAO.Terreno.TerrenoDAO;
import zMain.MiscData;

public class MapaModel
{
    private CeldaModel[][] matriz = new CeldaModel[MiscData.MAPA_Max_X][MiscData.MAPA_Max_Y];

    public MapaModel()
    {
        for (CeldaModel[] fila: matriz)
        {   for (int i=0; i<fila.length; i++)
            {   fila[i] = new CeldaModel(); }
        }
    }

    public TerrenoModel getTerreno (int x, int y, int numCapa)
    {
        TerrenoDAO terrenoDAO = MiscData.terrenoDAO.newInstance();
        return terrenoDAO.getTerreno(matriz[x][y].getTerrenoID(numCapa));
    }

    public int getTerrenoID (int x, int y, int numCapa)
    {
        return matriz[x][y].getTerrenoID(numCapa);
    }

    public boolean setTerreno (int x, int y, int numCapa, TerrenoModel terreno)
    {
        if (x<0 || y<0 || x> MiscData.MAPA_Max_X || y> MiscData.MAPA_Max_Y) return false;
        else
        {
            matriz[x][y].setTerreno(numCapa, terreno);
            return true;
        }
    }

    public boolean setTerreno (int x, int y, int numCapa, int iDTerreno)
    {
        if (x<0 || y<0 || x> MiscData.MAPA_Max_X || y> MiscData.MAPA_Max_Y) return false;
        else { return matriz[x][y].setTerreno(numCapa, iDTerreno); }
    }
}