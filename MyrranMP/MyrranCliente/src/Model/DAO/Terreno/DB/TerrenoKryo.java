package Model.DAO.Terreno.DB;// Created by Hanto on 14/04/2014.

import Model.DAO.Terreno.TerrenoDAO;
import Model.Geo.TerrenoModel;

import java.util.Map;

public class TerrenoKryo implements TerrenoDAO
{
    private Map<Integer, TerrenoModel> listaDeTerrenos = TerrenoKryoDB.get().listaDeTerrenos;


    @Override public void añadirTerreno(TerrenoModel terreno)
    {
        int iDMenor;
        for (iDMenor=0; iDMenor< listaDeTerrenos.size(); iDMenor++)
        {   if (!listaDeTerrenos.containsKey(iDMenor)) break; }

        terreno.setId(iDMenor);
        listaDeTerrenos.put(terreno.getID(), terreno);
        TerrenoKryoDB.get().salvarTerrenoDB();
    }

    @Override public void salvarTerreno(TerrenoModel terreno)
    {
        if (listaDeTerrenos.containsKey(terreno.getID()))
        {
            listaDeTerrenos.put(terreno.getID(), terreno);
            TerrenoKryoDB.get().salvarTerrenoDB();
        }
    }

    @Override public void eliminarTerreno(int terrenoID)
    {
        if (listaDeTerrenos.containsKey(terrenoID))
        {
            listaDeTerrenos.remove(terrenoID);
            TerrenoKryoDB.get().salvarTerrenoDB();
        }
    }

    @Override public TerrenoModel getTerreno(int terrenoID)
    {   return listaDeTerrenos.get(terrenoID); }
}
