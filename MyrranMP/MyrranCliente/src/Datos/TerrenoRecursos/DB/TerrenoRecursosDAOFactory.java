package Datos.TerrenoRecursos.DB;// Created by Ladrim on 24/04/2014.

import Datos.TerrenoRecursos.TerrenoRecursosDAO;
import Datos.TerrenoRecursos.TerrenoRecursosLocal;

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
