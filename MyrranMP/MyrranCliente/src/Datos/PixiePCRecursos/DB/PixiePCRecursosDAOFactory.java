package Datos.PixiePCRecursos.DB;// Created by Hanto on 01/05/2014.

import Datos.PixiePCRecursos.PixiePCRecursosDAO;
import Datos.PixiePCRecursos.PixiePCRecursosLocal;

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
