package Model.DAO.Terreno;// Created by Hanto on 14/04/2014.

import Model.Classes.Geo.Terreno;

import java.util.Iterator;

public interface TerrenoDAO
{   //C.R.U.D: Create Retrieve Update Delete
    public boolean añadirTerreno(Terreno terreno);
    public void salvarTerreno(Terreno terreno);
    public void eliminarTerreno(int terrenoID);
    public Terreno getTerreno(int terrenoID);

    public Iterator<Terreno> getIterator();
}
