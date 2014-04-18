package Model.DAO.Terreno.DB;// Created by Hanto on 15/04/2014.

import Model.DAO.Terreno.TerrenoDAO;
import Model.Geo.Terreno;

import java.util.Map;

public class TerrenoLocal implements TerrenoDAO
{
    private Map<Integer, Terreno> listaDeTerrenos = TerrenoLocalDB.get().listaDeTerrenos;


    @Override public int añadirTerreno(Terreno terreno)
    {
        int iDMenor;
        for (iDMenor=0; iDMenor< listaDeTerrenos.size(); iDMenor++)
        {   if (!listaDeTerrenos.containsKey(iDMenor)) break; }

        terreno.setId(iDMenor);
        listaDeTerrenos.put(terreno.getID(), terreno);
        return (iDMenor);
    }

    @Override public void salvarTerreno(Terreno terreno)
    {
        if (listaDeTerrenos.containsKey(terreno.getID()))
        {   listaDeTerrenos.put(terreno.getID(), terreno); }

    }

    @Override public void eliminarTerreno(int terrenoID)
    {
        if (listaDeTerrenos.containsKey(terrenoID))
        {   listaDeTerrenos.remove(terrenoID); }
    }

    @Override public Terreno getTerreno(int terrenoID)
    {   return listaDeTerrenos.get(terrenoID); }
}
