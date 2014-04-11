package Modelo.Mobiles;// Created by Hanto on 10/04/2014.

import Controller.Input.PlayerIO;
import Modelo.DTO.ClienteDTO;
import Models.AbstractModel;

public class Player extends AbstractModel implements PlayerModel
{
    protected int connectionID;
    //Posicion:
    protected float x;                      //Coordenadas X:
    protected float y;                      //Coordenadas Y:

    protected int numAnimacion = 5;

    protected boolean irArriba = false;
    protected boolean irAbajo = false;
    protected boolean irDerecha = false;
    protected boolean irIzquierda = false;
    protected boolean disparar = false;

    protected float velocidadMax = 80;
    protected float velocidadMod = 1;


    @Override public int getConnectionID()  { return connectionID; }
    @Override public float getX()           { return x; }
    @Override public float getY()           { return y; }

    public Player (int connectionID)
    {
        this.connectionID = connectionID;
    }

    @Override public void setInput (PlayerIO playerInput)
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
        Object posicionDTO = new ClienteDTO.MoverPlayer(this, x, y);
        notificarActualizacion("setPosition", null, posicionDTO);
    }

    public void setAnimacion (int numAnimacion)
    {
        if (this.numAnimacion != numAnimacion)
        {
            this.numAnimacion = numAnimacion;
            Object AnimacionDTO = new ClienteDTO.CambiarAnimacionPlayer(numAnimacion);
            notificarActualizacion("setAnimacion", null, AnimacionDTO);
        }
    }

    public void moverse (float delta)
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

    @Override public void actualizar (float delta)
    {
        moverse(delta);
    }
}
