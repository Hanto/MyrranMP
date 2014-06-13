package DB.Recursos.AtlasRecursos;

import DB.Recursos.AtlasRecursos.DAO.AtlasRecursosLocal;
import DB.Recursos.AtlasRecursos.DAO.AtlasRecursosDAO;

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