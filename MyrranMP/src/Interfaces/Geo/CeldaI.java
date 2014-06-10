package Interfaces.Geo;// Created by Hanto on 10/06/2014.

public interface CeldaI
{
    //GET:
    public short getTerrenoID(int numCapa);
    public TerrenoI getTerreno(int numCapa);

    //SET:
    public void setTerreno(int numCapa, TerrenoI terreno);
    public boolean setTerreno(int numCapa, short terrenoID);
}
