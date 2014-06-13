package DB.Datos.TipoSpell;// Created by Hanto on 13/05/2014.

import DAO.TipoSpell.TipoSpellDAO;
import DAO.TipoSpell.TipoSpellLocal;

public enum TipoSpellDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override
        public TipoSpellDAO getTipoSpellDAO()
        {   return new TipoSpellLocal(TipoSpellLocalDB.get().listaDeTipoSpells); }
    };

    public abstract TipoSpellDAO getTipoSpellDAO();
    private TipoSpellDAOFactory (String nombre) {}
}
