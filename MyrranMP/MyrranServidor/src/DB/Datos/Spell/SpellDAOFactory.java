package DB.Datos.Spell;// Created by Hanto on 17/04/2014.

import DAO.Spell.SpellLocal;
import DAO.Spell.SpellDAO;

public enum SpellDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override
        public SpellDAO getSpellDAO()
        {   return new SpellLocal(SpellLocalDB.get().listaDeSpells); }
    };

    public abstract SpellDAO getSpellDAO();
    private SpellDAOFactory(String nombre)
    {}
}
