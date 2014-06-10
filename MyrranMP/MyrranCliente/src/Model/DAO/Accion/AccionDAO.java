package Model.DAO.Accion;// Created by Hanto on 06/05/2014.

import Model.Classes.UI.Acciones.Accion;

public interface AccionDAO
{
    public boolean salvarAccion(Accion accion);
    public void eliminarAccion(String accionID);
    public Accion getAccion (String accionID);
}
