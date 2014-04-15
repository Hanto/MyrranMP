package Model.DAO.Terreno;// Created by Hanto on 14/04/2014.

import Model.DAO.Terreno.DB.TerrenoKryo;
import Model.DAO.Terreno.DB.TerrenoLocal;

public enum TerrenoDAOFactory
{
    KRYO("KRYO")
    {
        @Override
        public TerrenoDAO newInstance()
        {   return new TerrenoKryo(); }
    },
    LOCAL("LOCAL")
    {
        @Override
        public TerrenoDAO newInstance()
        {   return new TerrenoLocal(); }
    };

    public abstract TerrenoDAO newInstance();
    private TerrenoDAOFactory(String nombre)
    {}
}
