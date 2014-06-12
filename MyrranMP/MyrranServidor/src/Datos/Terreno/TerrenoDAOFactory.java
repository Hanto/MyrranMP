package Datos.Terreno;// Created by Hanto on 14/04/2014.

import DAO.Terreno.DB.TerrenoKryo;
import DAO.Terreno.TerrenoDAO;
import DAO.Terreno.TerrenoLocal;

public enum TerrenoDAOFactory
{
    KRYO("KRYO")
    {
        @Override public TerrenoDAO getTerrenoDAO()
        {   return new TerrenoKryo(); }
    },
    LOCAL("LOCAL")
    {
        @Override public TerrenoDAO getTerrenoDAO()
        {   return new TerrenoLocal(TerrenoLocalDB.get().listaDeTerrenos); }
    };

    public abstract TerrenoDAO getTerrenoDAO();
    private TerrenoDAOFactory(String nombre) {}
}
