package Interfaces;// Created by Ladrim on 18/04/2014.

public interface Mob
{
    //GET:
    public float getX();
    public float getY();
    public float getVelocidadMod();
    public float getVelocidadMax();
    public float getVelocidad();
    public double getDireccion();

    //SET:
    public void setPosition(float x, float y);
    public void setVelocidaMod(float velocidadMod);
    public void setVelocidadMax(float velocidadMax);
    public void setVelocidad(float velocidad);
    public void setDireccion(double direccion);
}
