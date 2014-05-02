package Recursos.DAO.PixiePCRecursos;// Created by Hanto on 01/05/2014.

import Recursos.DAO.PixiePCRecursos.DB.PixiePCRecursosLocal;

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
