package DB.Recursos.TerrenoRecursos;// Created by Ladrim on 24/04/2014.

import DB.Recursos.TerrenoRecursos.DAO.TerrenoRecursosDAO;
import DB.Recursos.TerrenoRecursos.DAO.TerrenoRecursosLocal;

public enum TerrenoRecursosDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override
        public TerrenoRecursosDAO getTerrenoRecursosDAO()
        {   return new TerrenoRecursosLocal(); }
    };

    public abstract TerrenoRecursosDAO getTerrenoRecursosDAO();
    private TerrenoRecursosDAOFactory(String nombre) {}
}
