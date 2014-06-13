package DB.Recursos.AccionRecursos;

import DB.Recursos.AccionRecursos.DAO.AccionRecursosDAO;
import DB.Recursos.AccionRecursos.DAO.AccionRecursosLocal;

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