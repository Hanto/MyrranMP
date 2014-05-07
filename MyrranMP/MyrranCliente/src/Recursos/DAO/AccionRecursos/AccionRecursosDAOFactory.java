package Recursos.DAO.AccionRecursos;

import Recursos.DAO.AccionRecursos.DB.AccionRecursosLocal;

public enum AccionRecursosDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override
        public AccionRecursosDAO getAccionRecursosDAO()
        {   return new AccionRecursosLocal(); }
    };

    public abstract AccionRecursosDAO getAccionRecursosDAO();
    private AccionRecursosDAOFactory(String nombre) { }
}