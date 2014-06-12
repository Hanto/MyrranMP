package DAO;// Created by Hanto on 10/06/2014.

import DAO.Accion.AccionDAOFactory;
import DAO.TipoBDebuff.TipoBDebuffDAOFactory;

public class DAO
{
    public static final TipoBDebuffDAOFactory tipoBDebuffDAOFactory = TipoBDebuffDAOFactory.LOCAL;
    public static final AccionDAOFactory accionDAOFactory = AccionDAOFactory.LOCAL;

}
