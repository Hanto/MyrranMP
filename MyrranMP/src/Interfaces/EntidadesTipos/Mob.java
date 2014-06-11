package Interfaces.EntidadesTipos;// Created by Ladrim on 18/04/2014.

import Interfaces.Model.ModelI;

public interface Mob extends ModelI
{
    //GET:
    public float getX();
    public float getY();
    public float getVelocidadMod();
    public float getVelocidadMax();
    public double getDireccion();

    public int getNumAnimacion();

    //SET:
    public void setPosition(float x, float y);
    public void setVelocidaMod(float velocidadMod);
    public void setVelocidadMax(float velocidadMax);
    public void setDireccion(double direccion);
}
