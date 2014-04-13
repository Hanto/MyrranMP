package Modelo.Mobiles;// Created by Hanto on 10/04/2014.

import Controller.Input.PlayerIO;
import Modelo.AbstractModel;
import DTO.MobDTO;

public class PlayerModel extends AbstractModel
{
    protected Integer connectionID;
    //Posicion:
    protected Float x;                      //Coordenadas X:
    protected Float y;                      //Coordenadas Y:

    protected Integer numAnimacion = 5;

    protected Boolean irArriba = false;
    protected Boolean irAbajo = false;
    protected Boolean irDerecha = false;
    protected Boolean irIzquierda = false;
    protected Boolean disparar = false;

    protected Float velocidadMax = 80.0f;
    protected Float velocidadMod = 1.0f;


    public int getConnectionID()            { return connectionID; }
    public float getX()                     { return x; }
    public float getY()                     { return y; }

    public PlayerModel(int connectionID)
    {
        this.connectionID = connectionID;
    }

    public void setInput (PlayerIO playerInput)
    {
        irArriba = playerInput.irArriba;
        irAbajo = playerInput.irAbajo;
        irDerecha = playerInput.irDerecha;
        irIzquierda = playerInput.irIzquierda;
        disparar = playerInput.disparar;

        setAnimacion (playerInput.numAnimacion);
    }

    public void setPosition (float x, float y)
    {
        this.x = x; this.y = y;
        Object posicionDTO = new MobDTO.MoverPlayer(this, x, y);
        notificarActualizacion("setPosition", null, posicionDTO);
    }

    private void setAnimacion (int numAnimacion)
    {
        if (this.numAnimacion != numAnimacion)
        {
            this.numAnimacion = numAnimacion;
            Object AnimacionDTO = new MobDTO.CambiarAnimacionPlayer(numAnimacion);
            notificarActualizacion("setAnimacion", null, AnimacionDTO);
        }
    }

    private void moverse (float delta)
    {
        float X=x;
        float Y=y;

        if (irAbajo && !irDerecha && !irIzquierda)
        { Y = ((float)(y+ -(Math.sin(Math.toRadians(90d))*velocidadMax)*velocidadMod*delta)); }
        //Norte
        else if (irArriba && !irDerecha && !irIzquierda)
        { Y = ((float)(y+ -(Math.sin(Math.toRadians(270d))*velocidadMax)*velocidadMod*delta)); }
        //Este
        else if (irDerecha && !irArriba && !irAbajo)
        { X = ((float)(x+ (Math.cos(Math.toRadians(0d))*velocidadMax)*velocidadMod*delta)); }
        //Oeste
        else if (irIzquierda && !irArriba && !irAbajo)
        { X = ((float)(x+ (Math.cos(Math.toRadians(180d))*velocidadMax)*velocidadMod*delta)); }
        //SurOeste
        else if (irAbajo&& irIzquierda)
        { Y = ((float)(y+ -(Math.sin(Math.toRadians(135d))*velocidadMax)*velocidadMod*delta));
          X = ((float)(x+ (Math.cos(Math.toRadians(135d))*velocidadMax)*velocidadMod*delta)); }
        //SurEste
        else if (irAbajo && irDerecha)
        { Y = ((float)(y+ -(Math.sin(Math.toRadians(45d))*velocidadMax)*velocidadMod*delta));
          X = ((float)(x+ (Math.cos(Math.toRadians(45d))*velocidadMax)*velocidadMod*delta)); }
        //NorOeste
        else if (irArriba && irIzquierda)
        { Y = ((float)(y+ -(Math.sin(Math.toRadians(225d))*velocidadMax)*velocidadMod*delta));
          X = ((float)(x+ (Math.cos(Math.toRadians(225d))*velocidadMax)*velocidadMod*delta)); }
        //NorEste
        else if (irArriba && irDerecha)
        { Y = ((float)(y+ -(Math.sin(Math.toRadians(315d))*velocidadMax)*velocidadMod*delta));
          X = ((float)(x+ (Math.cos(Math.toRadians(315d))*velocidadMax)*velocidadMod*delta)); }

        setPosition(X,Y);
    }

    public void actualizar (float delta)
    {
        moverse(delta);
    }
}
