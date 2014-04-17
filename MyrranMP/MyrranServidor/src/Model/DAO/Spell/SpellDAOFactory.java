package Model.DAO.Spell;// Created by Hanto on 17/04/2014.

import Model.DAO.Spell.DB.SpellLocal;

public enum SpellDAOFactory
{
    LOCAL("LOCAL")
            {
                @Override
                public SpellDAO newInstance()
                {   return new SpellLocal(); }
            };

    public abstract SpellDAO newInstance();
    private SpellDAOFactory(String nombre)
    {}
}
