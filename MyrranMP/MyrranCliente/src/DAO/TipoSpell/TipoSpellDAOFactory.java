package DAO.TipoSpell;// Created by Hanto on 17/04/2014.

import DAO.TipoSpell.DB.TipoSpellLocal;

public enum TipoSpellDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override
        public TipoSpellDAO getTipoSpellDAO()
        {   return new TipoSpellLocal(); }
    };

    public abstract TipoSpellDAO getTipoSpellDAO();
    private TipoSpellDAOFactory(String nombre)
    {}
}