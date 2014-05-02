package Recursos.DAO.FuentesRecursos;// Created by Hanto on 02/05/2014.

import Recursos.DAO.FuentesRecursos.DB.FuentesRecursosLocal;

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
