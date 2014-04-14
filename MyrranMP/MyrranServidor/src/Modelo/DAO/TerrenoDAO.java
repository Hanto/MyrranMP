package Modelo.DAO;// Created by Hanto on 14/04/2014.

import Modelo.Geo.Terreno;

public interface TerrenoDAO
{   //C.R.U.D: Create Retrieve Update Delete
    public void añadirTerreno(Terreno terreno);
    public Terreno getTerreno(int terrenoID);
    public void salvarTerreno(Terreno terreno);
    public void eliminarTerreno (int terrenoID);
}
