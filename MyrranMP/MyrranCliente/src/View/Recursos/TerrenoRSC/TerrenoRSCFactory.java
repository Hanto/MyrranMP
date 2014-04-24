package View.Recursos.TerrenoRSC;// Created by Ladrim on 24/04/2014.

import View.Recursos.TerrenoRSC.DB.TerrenoRSCLocal;

public enum TerrenoRSCFactory
{
    LOCAL("LOCAL")
    {
        @Override
        public TerrenoRSC getTerrenoRecursoDAO()
        {   return new TerrenoRSCLocal(); }
    };

    public abstract TerrenoRSC getTerrenoRecursoDAO();
    private TerrenoRSCFactory(String nombre) {}
}
