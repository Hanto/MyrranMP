package Model.Geo;// Created by Hanto on 14/04/2014.

import zMain.MiscData;

import java.util.Arrays;

public class MapaModel
{
    private CeldaModel[][] matriz = new CeldaModel[MiscData.MAPA_Max_X][MiscData.MAPA_Max_Y];

    public MapaModel()
    {
        for (CeldaModel[] fila: matriz)
        {   Arrays.fill(fila, new CeldaModel());}
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
        else
        {
            TerrenoModel terreno = MiscData.terrenoDAO.newInstance().getTerreno(iDTerreno);
            if (terreno == null) return false;
            else
            {
                matriz[x][y].setTerreno(numCapa, terreno);
                return true;
            }
        }
    }
}
