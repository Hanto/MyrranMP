package DAO.TipoBDebuff;

import DAO.TipoBDebuff.DB.TipoBDebuffLocal;

public enum TipoBDebuffDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override
        public TipoBDebuffDAO getBDebuffDAO()
        {   return new TipoBDebuffLocal(); }
    };

    public abstract TipoBDebuffDAO getBDebuffDAO();

    private TipoBDebuffDAOFactory(String nombre) {}

}