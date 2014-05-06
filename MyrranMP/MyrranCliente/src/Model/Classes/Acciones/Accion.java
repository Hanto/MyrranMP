package Model.Classes.Acciones;// Created by Hanto on 05/05/2014.

public abstract class Accion implements AccionInterface
{
    protected Object parametros;
    protected String iD;

    public String getID()           { return iD; }
}
