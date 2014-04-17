package Model.DAO.Terreno.DB;// Created by Hanto on 15/04/2014.

import Model.DAO.Terreno.TerrenoDAO;
import Model.Geo.TerrenoModel;

import java.util.Map;

public class TerrenoLocal implements TerrenoDAO
{
    private Map<Integer, TerrenoModel> listaDeTerrenos = TerrenoLocalDB.get().listaDeTerrenos;


    @Override public int añadirTerreno(TerrenoModel terreno)
    {
        int iDMenor;
        for (iDMenor=0; iDMenor< listaDeTerrenos.size(); iDMenor++)
        {   if (!listaDeTerrenos.containsKey(iDMenor)) break; }

        terreno.setId(iDMenor);
        listaDeTerrenos.put(terreno.getID(), terreno);
        return (iDMenor);
    }

    @Override public void salvarTerreno(TerrenoModel terreno)
    {
        if (listaDeTerrenos.containsKey(terreno.getID()))
        {   listaDeTerrenos.put(terreno.getID(), terreno); }

    }

    @Override public void eliminarTerreno(int terrenoID)
    {
        if (listaDeTerrenos.containsKey(terrenoID))
        {   listaDeTerrenos.remove(terrenoID); }
    }

    @Override public TerrenoModel getTerreno(int terrenoID)
    {   return listaDeTerrenos.get(terrenoID); }
}
