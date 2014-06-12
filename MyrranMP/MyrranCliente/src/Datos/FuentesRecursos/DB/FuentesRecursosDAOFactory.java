package Datos.FuentesRecursos.DB;// Created by Hanto on 02/05/2014.

import Datos.FuentesRecursos.FuentesRecursosDAO;
import Datos.FuentesRecursos.FuentesRecursosLocal;

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
