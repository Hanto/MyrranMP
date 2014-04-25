package Model.Geo;// Created by Hanto on 14/04/2014.

import Data.MiscData;
import Interfaces.MapaI;
import Model.AbstractModel;
import DAO.DAO;
import DAO.Terreno.TerrenoDAO;
import DTO.MapaDTO;

public class Mapa extends AbstractModel implements MapaI
{
    private Celda[][] matriz = new Celda[MiscData.MAPA_Max_TilesX][MiscData.MAPA_Max_TilesY];

    public Mapa()
    {
        for (Celda[] fila: matriz)
        {   for (int i=0; i<fila.length; i++)
            {   fila[i] = new Celda(); }
        }
    }

    public Terreno getTerreno (int x, int y, int numCapa)
    {
        TerrenoDAO terrenoDAO = DAO.terrenoDAOFactory.getTerrenoDAO();
        return terrenoDAO.getTerreno(matriz[x][y].getTerrenoID(numCapa));
    }

    public int getTerrenoID (int x, int y, int numCapa)
    {
        return matriz[x][y].getTerrenoID(numCapa);
    }

    public boolean setTerreno (int x, int y, int numCapa, Terreno terreno)
    {
        if (x<0 || y<0 || x> MiscData.MAPA_Max_TilesX || y> MiscData.MAPA_Max_TilesY) return false;
        else
        {
            matriz[x][y].setTerreno(numCapa, terreno);
            MapaDTO.SetTerreno setTerreno = new MapaDTO.SetTerreno(x,y,numCapa,terreno.getID());
            notificarActualizacion("setTerreno", null, setTerreno);
            return true;
        }
    }

    @Override public boolean setTerreno (int x, int y, int numCapa, int iDTerreno)
    {
        if (x<0 || y<0 || x> MiscData.MAPA_Max_TilesX || y> MiscData.MAPA_Max_TilesY) return false;
        else
        {
            if (matriz[x][y].setTerreno(numCapa, iDTerreno))
            {
                MapaDTO.SetTerreno setTerreno = new MapaDTO.SetTerreno(x,y,numCapa,iDTerreno);
                notificarActualizacion("setTerreno", null, setTerreno);
                return true;
            }
            else return false;
        }
    }
}
