package DB.Recursos.FuentesRecursos;// Created by Hanto on 02/05/2014.

import DB.Recursos.FuentesRecursos.DAO.FuentesRecursosDAO;
import DB.Recursos.FuentesRecursos.DAO.FuentesRecursosLocal;

public enum FuentesRecursosDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override
        public FuentesRecursosDAO getFuentesRecursosDAO()
        {   return new FuentesRecursosLocal(); }
    };

    public abstract FuentesRecursosDAO getFuentesRecursosDAO();
    private FuentesRecursosDAOFactory(String nombre) {}
}
