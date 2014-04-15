package Model.DAO.Terreno;// Created by Hanto on 14/04/2014.

import Model.Geo.TerrenoModel;

public interface TerrenoDAO
{   //C.R.U.D: Create Retrieve Update Delete
    public void añadirTerreno(TerrenoModel terreno);
    public void salvarTerreno(TerrenoModel terreno);
    public void eliminarTerreno(int terrenoID);
    public TerrenoModel getTerreno(int terrenoID);
}
