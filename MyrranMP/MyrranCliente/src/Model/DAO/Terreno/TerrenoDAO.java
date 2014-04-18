package Model.DAO.Terreno;// Created by Hanto on 14/04/2014.

import Model.Geo.Terreno;

public interface TerrenoDAO
{   //C.R.U.D: Create Retrieve Update Delete
    public int añadirTerreno(Terreno terreno);
    public void salvarTerreno(Terreno terreno);
    public void eliminarTerreno(int terrenoID);
    public Terreno getTerreno(int terrenoID);
}
