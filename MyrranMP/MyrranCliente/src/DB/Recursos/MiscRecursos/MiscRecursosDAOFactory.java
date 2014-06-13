package DB.Recursos.MiscRecursos;

import DB.Recursos.MiscRecursos.DAO.MiscRecursosDAO;
import DB.Recursos.MiscRecursos.DAO.MiscRecursosLocal;

public enum MiscRecursosDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override
        public MiscRecursosDAO getMiscRecursosDAO()
        {   return new MiscRecursosLocal(); }
    };

    public abstract MiscRecursosDAO getMiscRecursosDAO();
    private MiscRecursosDAOFactory(String nombre) {}

}