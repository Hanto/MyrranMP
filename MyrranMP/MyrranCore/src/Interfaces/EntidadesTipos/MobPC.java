package Interfaces.EntidadesTipos;// Created by Ladrim on 19/04/2014.

public interface MobPC extends Mob
{
    public int getConnectionID();
    public String getNombre();
    public int getNivel();

    public void setConnectionID(int connectionID);
    public void setNombre(String nombre);
    public void setNivel(int nivel);
}
