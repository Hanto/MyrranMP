package Recursos.DAO.MiscRecursos;

import Recursos.DAO.MiscRecursos.DB.MiscRecursosLocal;

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