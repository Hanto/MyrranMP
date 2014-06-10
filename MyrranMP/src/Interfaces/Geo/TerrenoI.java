package Interfaces.Geo;// Created by Hanto on 10/06/2014.

public interface TerrenoI
{
    //SET:
    public void setId (short i);
    public void setNombre (String s);
    public void setIsSolido (boolean b);

    //GET:
    public short getID();
    public String getNombre();
    public boolean getIsSolido();
}
