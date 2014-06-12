package DAO.Accion;// Created by Hanto on 06/05/2014.

import Interfaces.UI.Acciones.AccionI;

public interface AccionDAO
{
    public boolean salvarAccion(AccionI accion);
    public void eliminarAccion(String accionID);
    public AccionI getAccion (String accionID);
}
