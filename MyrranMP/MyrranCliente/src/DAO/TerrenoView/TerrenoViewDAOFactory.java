package DAO.TerrenoView;// Created by Ladrim on 24/04/2014.

import DAO.TerrenoView.DB.TerrenoViewLocal;

public enum TerrenoViewDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override
        public TerrenoViewDAO getTerrenoViewDAO()
        {   return new TerrenoViewLocal(); }
    };

    public abstract TerrenoViewDAO getTerrenoViewDAO();
    private TerrenoViewDAOFactory(String nombre) {}
}
