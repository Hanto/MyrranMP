package Model.DAO.Terreno;// Created by Hanto on 14/04/2014.

import Model.Classes.Geo.Terreno;

import java.util.Iterator;

public interface TerrenoDAO
{   //C.R.U.D: Create Retrieve Update Delete
    public boolean añadirTerreno(Terreno terreno);
    public void salvarTerreno(Terreno terreno);
    public void eliminarTerreno(short terrenoID);
    public Terreno getTerreno(short terrenoID);

    public Iterator<Terreno> getIterator();
}
