package Datos.AccionRecursos.DB;

import Datos.AccionRecursos.AccionRecursosDAO;
import Datos.AccionRecursos.AccionRecursosLocal;

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