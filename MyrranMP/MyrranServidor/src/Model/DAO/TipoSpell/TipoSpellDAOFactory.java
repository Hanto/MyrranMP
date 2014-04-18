package Model.DAO.TipoSpell;// Created by Hanto on 17/04/2014.

import Model.DAO.TipoSpell.DB.TipoSpellLocal;

public enum TipoSpellDAOFactory
{
    LOCAL("LOCAL")
            {
                @Override
                public TipoSpellDAO newInstance()
                {   return new TipoSpellLocal(); }
            };

    public abstract TipoSpellDAO newInstance();
    private TipoSpellDAOFactory(String nombre)
    {}
}