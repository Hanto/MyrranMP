package Model.DAO.Terreno.DB;// Created by Hanto on 15/04/2014.

import Model.DAO.Terreno.TerrenoDAO;
import Model.Classes.Geo.Terreno;

import java.util.Map;

public class TerrenoLocal implements TerrenoDAO
{
    private Map<Short, Terreno> listaDeTerrenos = TerrenoLocalDB.get().listaDeTerrenos;


    @Override public boolean añadirTerreno(Terreno terreno)
    {
        if (listaDeTerrenos.containsKey(terreno.getID()))
        {   System.out.println("ERROR: ya existe un terreno con este ID["+terreno.getID()+"]");  return false; }
        else {  listaDeTerrenos.put(terreno.getID(), terreno); return true; }

    }

    @Override public void salvarTerreno(Terreno terreno)
    {
        if (listaDeTerrenos.containsKey(terreno.getID()))
        {   listaDeTerrenos.put(terreno.getID(), terreno); }

    }

    @Override public void eliminarTerreno(short terrenoID)
    {
        if (listaDeTerrenos.containsKey(terrenoID))
        {   listaDeTerrenos.remove(terrenoID); }
    }

    @Override public Terreno getTerreno(short terrenoID)
    {   return listaDeTerrenos.get(terrenoID); }
}
