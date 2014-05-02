package Recursos.DAO.TerrenoRecursos;// Created by Ladrim on 24/04/2014.

import Recursos.DAO.TerrenoRecursos.DB.TerrenoRecursosLocal;

public enum TerrenoRecursosDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override
        public TerrenoRecursosDAO getTerrenoRecursosDAO()
        {   return new TerrenoRecursosLocal(); }
    };

    public abstract TerrenoRecursosDAO getTerrenoRecursosDAO();
    private TerrenoRecursosDAOFactory(String nombre) {}
}
