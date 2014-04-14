package Modelo.DAO.DAOFactory;// Created by Hanto on 14/04/2014.

import Modelo.DAO.Database.TerrenoDB;
import Modelo.DAO.TerrenoDAO;

public enum TerrenoDBFactory
{
    LOCAL()
    {
        @Override
        public TerrenoDAO newInstance()
        {   return new TerrenoDB(); }
    },
    MySQL()
    {
        @Override
        public TerrenoDAO newInstance()
        {   return new TerrenoDB(); }
    };

    public abstract TerrenoDAO newInstance();
}
