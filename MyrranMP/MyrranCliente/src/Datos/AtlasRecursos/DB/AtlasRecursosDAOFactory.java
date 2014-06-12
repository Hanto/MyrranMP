package Datos.AtlasRecursos.DB;

import Datos.AtlasRecursos.AtlasRecursosDAO;
import Datos.AtlasRecursos.AtlasRecursosLocal;

public enum AtlasRecursosDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override public AtlasRecursosDAO getAtlasRecursosDAO()
        {   return new AtlasRecursosLocal(); }
    };


    public abstract AtlasRecursosDAO getAtlasRecursosDAO();

    private AtlasRecursosDAOFactory(String nombre) {}
}