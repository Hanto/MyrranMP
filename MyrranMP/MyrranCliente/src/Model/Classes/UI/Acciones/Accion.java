package Model.Classes.UI.Acciones;// Created by Hanto on 05/05/2014.

import Interfaces.UI.AccionI;

public abstract class Accion implements AccionI
{
    protected Object parametros;
    protected String iD;

    public String getID()           { return iD; }
}
