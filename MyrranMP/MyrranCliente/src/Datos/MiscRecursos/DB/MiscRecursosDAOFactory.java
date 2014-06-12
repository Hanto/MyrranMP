package Datos.MiscRecursos.DB;

import Datos.MiscRecursos.MiscRecursosDAO;
import Datos.MiscRecursos.MiscRecursosLocal;

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