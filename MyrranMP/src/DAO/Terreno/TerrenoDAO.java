package DAO.Terreno;// Created by Hanto on 14/04/2014.

import Interfaces.Geo.TerrenoI;

public interface TerrenoDAO
{   //C.R.U.D: Create Retrieve Update Delete
    public boolean añadirTerreno(TerrenoI terreno);
    public void salvarTerreno(TerrenoI terreno);
    public void eliminarTerreno (short terrenoID);
    public TerrenoI getTerreno(short terrenoID);
}
