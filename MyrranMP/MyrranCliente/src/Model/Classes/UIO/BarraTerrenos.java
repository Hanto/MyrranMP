package Model.Classes.UIO;// Created by Hanto on 14/05/2014.

import Model.Classes.Geo.Terreno;
import Model.DAO.DAO;
import Model.DAO.Terreno.TerrenoDAO;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class BarraTerrenos
{
    private Array<Integer>barraTerrenos = new Array<>();

    public BarraTerrenos ()
    {
        crearBarraTerrenos();
    }

    public void crearBarraTerrenos()
    {
        barraTerrenos.clear();

        TerrenoDAO terrenoDAO = DAO.terrenoDAOFactory.getTerrenoDAO();

        Iterator<Terreno> iterator = terrenoDAO.getIterator();
        while (iterator.hasNext())
        {   barraTerrenos.add(iterator.next().getID()); }
    }


}
