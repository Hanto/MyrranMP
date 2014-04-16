package Model.Geo;// Created by Hanto on 14/04/2014.

import Model.DAO.Terreno.TerrenoDAO;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import zMain.MiscData;

public class CeldaModel implements KryoSerializable
{
    //private Integer[] terrenosID = new Integer[MiscData.MAPA_Max_Capas_Terreno];
    private TerrenoModel[] listaTerrenos = new TerrenoModel[MiscData.MAPA_Max_Capas_Terreno];


    //CONSTRUCTOR:
    public CeldaModel() {}

    //CONSTRUCTOR COPIA:
    public CeldaModel (CeldaModel celdaOrigen)
    {
        for (int i=0; i<MiscData.MAPA_Max_Capas_Terreno; i++)
        {   listaTerrenos[i] = celdaOrigen.getTerrenoID(i); }
    }

    public TerrenoModel getTerrenoID(int numCapa)
    {   //return terrenosID[numCapa];
        return listaTerrenos[numCapa];
    }

    public void setTerreno(int numCapa, TerrenoModel terreno)
    {
        listaTerrenos[numCapa] = terreno;
    }

    @Override public void write(Kryo kryo, Output output)
    {
        for (int i=0; i<MiscData.MAPA_Max_Capas_Terreno; i++)
            output.writeInt(listaTerrenos[i].getID());
    }

    @Override public void read(Kryo kryo, Input input)
    {
        TerrenoDAO terrenoDAO = MiscData.terrenoDAO.newInstance();

        for (int i=0; i<MiscData.MAPA_Max_Capas_Terreno; i++)
            setTerreno(i, terrenoDAO.getTerreno(input.readInt()));
    }
}
