package Model.Classes.Geo;// Created by Hanto on 14/04/2014.

import DTO.NetDTO;
import Data.MiscData;
import Interfaces.MapaI;
import Interfaces.AbstractModel;
import Model.DAO.DAO;
import Model.DAO.Terreno.TerrenoDAO;

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
        if (x<0 || y<0 || x>= MiscData.MAPA_Max_TilesX || y>= MiscData.MAPA_Max_TilesY) return null;
        TerrenoDAO terrenoDAO = DAO.terrenoDAOFactory.getTerrenoDAO();
        return terrenoDAO.getTerreno(matriz[x][y].getTerrenoID(numCapa));
    }

    public short getTerrenoID (int x, int y, int numCapa)
    {
        if (x<0 || y<0 || x>= MiscData.MAPA_Max_TilesX || y>= MiscData.MAPA_Max_TilesY) return -1;
        else return matriz[x][y].getTerrenoID(numCapa);
    }

    public boolean setTerreno (int x, int y, int numCapa, Terreno terreno)
    {
        if (x<0 || y<0 || x>= MiscData.MAPA_Max_TilesX || y>= MiscData.MAPA_Max_TilesY) return false;
        else if (matriz[x][y].getTerreno(numCapa) != terreno)
        {
            matriz[x][y].setTerreno(numCapa, terreno);
            NetDTO.SetTerreno setTerreno = new NetDTO.SetTerreno(x,y,numCapa,terreno.getID());
            notificarActualizacion("setTerreno", null, setTerreno);
            return true;
        }
        else return true;
    }

    @Override public boolean setTerreno (int x, int y, int numCapa, short iDTerreno)
    {
        if (x<0 || y<0 || x>= MiscData.MAPA_Max_TilesX || y>= MiscData.MAPA_Max_TilesY) return false;
        else if (matriz[x][y].getTerrenoID(numCapa) != iDTerreno)
        {
            if (matriz[x][y].setTerreno(numCapa, iDTerreno))
            {
                NetDTO.SetTerreno setTerreno = new NetDTO.SetTerreno(x,y,numCapa,iDTerreno);
                notificarActualizacion("setTerreno", null, setTerreno);
                return true;
            }
            else return false;
        }
        else return true;
    }
}
