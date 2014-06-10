package DAO.Terreno.DB;// Created by Hanto on 14/04/2014.

import DAO.Terreno.TerrenoDAO;
import Interfaces.Geo.TerrenoI;

import java.util.Map;

public class TerrenoKryo implements TerrenoDAO
{
    private Map<Short, TerrenoI> listaDeTerrenos = TerrenoKryoDB.get().listaDeTerrenos;


    @Override public boolean añadirTerreno(TerrenoI terreno)
    {
        if (listaDeTerrenos.containsKey(terreno.getID()))
        {   System.out.println("ERROR: ya existe un terreno con este ID["+terreno.getID()+"]");  return false; }
        else {  listaDeTerrenos.put(terreno.getID(), terreno); return true; }
    }

    @Override public void salvarTerreno(TerrenoI terreno)
    {
        if (listaDeTerrenos.containsKey(terreno.getID()))
        {
            listaDeTerrenos.put(terreno.getID(), terreno);
            TerrenoKryoDB.get().salvarTerrenoDB();
        }
    }

    @Override public void eliminarTerreno(short terrenoID)
    {
        if (listaDeTerrenos.containsKey(terrenoID))
        {
            listaDeTerrenos.remove(terrenoID);
            TerrenoKryoDB.get().salvarTerrenoDB();
        }
    }

    @Override public TerrenoI getTerreno(short terrenoID)
    {   return listaDeTerrenos.get(terrenoID); }
}
