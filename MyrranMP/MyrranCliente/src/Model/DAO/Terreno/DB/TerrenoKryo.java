package Model.DAO.Terreno.DB;// Created by Hanto on 14/04/2014.

import Model.DAO.Terreno.TerrenoDAO;
import Model.Classes.Geo.Terreno;

import java.util.Iterator;
import java.util.Map;

public class TerrenoKryo implements TerrenoDAO
{
    private Map<Integer, Terreno> listaDeTerrenos = TerrenoKryoDB.get().listaDeTerrenos;


    @Override public boolean añadirTerreno(Terreno terreno)
    {
        if (listaDeTerrenos.containsKey(terreno.getID()))
        {   System.out.println("ERROR: ya existe un terreno con este ID["+terreno.getID()+"]");  return false; }
        else {  listaDeTerrenos.put(terreno.getID(), terreno); return true; }
    }

    @Override public void salvarTerreno(Terreno terreno)
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

    @Override public Terreno getTerreno(int terrenoID)
    {   return listaDeTerrenos.get(terrenoID); }

    @Override public Iterator<Terreno> getIterator()
    {   return listaDeTerrenos.values().iterator(); }
}
