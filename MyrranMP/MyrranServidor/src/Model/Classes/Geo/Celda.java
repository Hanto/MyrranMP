package Model.Classes.Geo;// Created by Hanto on 14/04/2014.

import Data.MiscData;
import Model.DAO.DAO;
import Model.DAO.Terreno.TerrenoDAO;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class Celda implements KryoSerializable
{
    //private Integer[] terrenosID = new Integer[MiscData.MAPA_Max_Capas_Terreno];
    private Integer[] listaTerrenos = new Integer[MiscData.MAPA_Max_Capas_Terreno];


    //CONSTRUCTOR:
    public Celda()
    {
        for (int i=0; i< MiscData.MAPA_Max_Capas_Terreno; i++)
        {   listaTerrenos[i] = -1;}
    }

    //CONSTRUCTOR COPIA:
    public Celda(Celda celdaOrigen)
    {
        for (int i=0; i<MiscData.MAPA_Max_Capas_Terreno; i++)
        {   listaTerrenos[i] = celdaOrigen.getTerrenoID(i); }
    }

    public int getTerrenoID(int numCapa)
    {
        return listaTerrenos[numCapa];
    }

    public Terreno getTerreno(int numCapa)
    {
        TerrenoDAO terrenoDAO = DAO.terrenoDAOFactory.getTerrenoDAO();
        return terrenoDAO.getTerreno(listaTerrenos[numCapa]);
    }

    public void setTerreno(int numCapa, Terreno terreno)
    {
        listaTerrenos[numCapa] = terreno.getID();
    }

    public boolean setTerreno(int numCapa, int terrenoID)
    {
        TerrenoDAO terrenoDAO = DAO.terrenoDAOFactory.getTerrenoDAO();
        if (terrenoDAO.getTerreno(terrenoID) == null && terrenoID != -1) { return false; }
        else { listaTerrenos[numCapa] = terrenoID; return true; }
    }

    //KryoSerializable:
    @Override public void write(Kryo kryo, Output output)
    {
        for (int i=0; i<MiscData.MAPA_Max_Capas_Terreno; i++)
            output.writeInt(listaTerrenos[i]);
    }

    @Override public void read(Kryo kryo, Input input)
    {
        for (int i=0; i<MiscData.MAPA_Max_Capas_Terreno; i++)
            setTerreno(i, input.readInt());
    }
}
