package Model.Classes.Mobiles;// Created by Hanto on 07/04/2014.

import Model.AbstractModel;

public class Proyectil extends AbstractModel
{
    //Posicion:
    protected Float x=0.0f;                 //Coordenadas X:
    protected Float y=0.0f;                 //Coordenadas Y:
    protected Float oldPosX;                //Coordenadas X, de la ultima posicion X segura
    protected Float oldPosY;                //Coordenadas Y, de la ultima posicion Y segura

    //Velocidad y Direccion:
    protected Float velocidadMod=1.0f;      //Modificadores de Velocidad: debido a Snares, a Sprints, Roots
    protected Float velocidadMax;           //Velocidad Maxima:
    protected Float velocidad;              //Velocidad Actual:
    protected Double direccion;             //Direccion Actual en Radianes

    //Duracion
    protected Float duracionActual = 0.0f;
    protected Float duracionMaxima;


    //GET:
    public float getX()                     { return x; }
    public float getY()                     { return y; }

    public Proyectil()
    {

    }

    public void setPosicion (float x, float y)
    {
        this.x = x; this.y = y;
    }

    public void moverse (float delta)
    {
        oldPosX = x; oldPosY = y;
        float newPosX =  (float)(x+ (Math.cos(direccion))*velocidad*velocidadMod*delta);
        float newPosY =  (float)(y+ (Math.sin(direccion))*velocidad*velocidadMod*delta);
        setPosicion(newPosX, newPosY);
    }

    public void consumirse (float delta)
    {
        duracionActual += delta;
        if (duracionActual > duracionMaxima) {}
    }

    public void actualizar (float delta)
    {
        moverse(delta);
        consumirse(delta);
    }


}
