package Modelo.DAO;// Created by Hanto on 14/04/2014.

import Modelo.DAO.Database.PcDB;

public enum PcDBFactory
{
    LOCAL()
    {
        @Override
        public PcDAO newInstance()
        {   return new PcDB(); }
    },
    MySQL()
    {
        @Override
        public PcDAO newInstance()
        {   return new PcDB(); }
    };

    public abstract PcDAO newInstance();
}
