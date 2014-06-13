package DB.Recursos.PixiePCRecursos;// Created by Hanto on 01/05/2014.

import DB.Recursos.PixiePCRecursos.DAO.PixiePCRecursosLocal;
import DB.Recursos.PixiePCRecursos.DAO.PixiePCRecursosDAO;

public enum PixiePCRecursosDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override
        public PixiePCRecursosDAO getPixiePCRecursosDAO()
        {   return new PixiePCRecursosLocal(); }
    };

    public abstract PixiePCRecursosDAO getPixiePCRecursosDAO();
    private PixiePCRecursosDAOFactory(String nombre) {}
}
