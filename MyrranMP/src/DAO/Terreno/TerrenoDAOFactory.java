package DAO.Terreno;// Created by Hanto on 14/04/2014.

import DAO.Terreno.TerrenoDAO;
import DAO.Terreno.DB.TerrenoKryo;
import DAO.Terreno.DB.TerrenoLocal;

public enum TerrenoDAOFactory
{
    KRYO("KRYO")
    {
        @Override
        public TerrenoDAO getTerrenoDAO()
        {   return new TerrenoKryo(); }
    },
    LOCAL("LOCAL")
    {
        @Override
        public TerrenoDAO getTerrenoDAO()
        {   return new TerrenoLocal(); }
    };

    public abstract TerrenoDAO getTerrenoDAO();
    private TerrenoDAOFactory(String nombre)
    {}
}
