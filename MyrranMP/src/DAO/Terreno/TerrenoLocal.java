package DAO.Terreno;// Created by Hanto on 15/04/2014.

import Interfaces.Geo.TerrenoI;

import java.util.Iterator;
import java.util.Map;

public class TerrenoLocal implements TerrenoDAO
{
    private Map<Short, TerrenoI> listaDeTerrenos;

    //CONSTRUCTOR:
    public TerrenoLocal(Map<Short, TerrenoI> listaDeTerrenos)
    {   this.listaDeTerrenos = listaDeTerrenos; }




    @Override public boolean añadirTerreno(TerrenoI terreno)
    {
        if (listaDeTerrenos.containsKey(terreno.getID()))
        {   System.out.println("ERROR: ya existe un terreno con este ID["+terreno.getID()+"]");  return false; }
        else {  listaDeTerrenos.put(terreno.getID(), terreno); return true; }

    }

    @Override public void salvarTerreno(TerrenoI terreno)
    {
        if (listaDeTerrenos.containsKey(terreno.getID()))
        {   listaDeTerrenos.put(terreno.getID(), terreno); }

    }

    @Override public void eliminarTerreno(short terrenoID)
    {
        if (listaDeTerrenos.containsKey(terrenoID))
        {   listaDeTerrenos.remove(terrenoID); }
    }

    @Override public TerrenoI getTerreno(short terrenoID)
    {   return listaDeTerrenos.get(terrenoID); }

    @Override public Iterator<TerrenoI> getIterator()
    {   return listaDeTerrenos.values().iterator(); }
}
