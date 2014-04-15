package Model.Mobiles;// Created by Hanto on 10/04/2014.

import Controller.Input.PlayerIO;
import Model.DTO.PlayerDTO;
import Model.AbstractModel;

public class PlayerModel extends AbstractModel
{
    protected Integer connectionID;
    protected String nombre;
    protected Integer nivel;
    protected Float actualHPs;
    protected Float maxHPs;
    //Posicion:
    protected Float x = 0.0f;                      //Coordenadas X:
    protected Float y = 0.0f;                      //Coordenadas Y:

    protected Integer numAnimacion = 5;

    protected Boolean irArriba = false;
    protected Boolean irAbajo = false;
    protected Boolean irDerecha = false;
    protected Boolean irIzquierda = false;
    protected Boolean disparar = false;

    protected Float velocidadMax = 80.0f;
    protected Float velocidadMod = 1.0f;

    //GET:
    public int getConnectionID()            { return connectionID; }
    public String getNombre()               { return nombre; }
    public Integer getNivel()               { return nivel; }
    public Float getActualHPs()             { return actualHPs; }
    public Float getMaxHPs()                { return maxHPs; }
    public float getX()                     { return x; }
    public float getY()                     { return y; }

    //SET:
    public void setConnectionID (int connectionID)
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

    private void setAnimacion (int numAnimacion)
    {
        if (this.numAnimacion != numAnimacion)
        {
            this.numAnimacion = numAnimacion;
            Object AnimacionDTO = new PlayerDTO.AnimacionPlayer(numAnimacion);
            notificarActualizacion("setAnimacion", null, AnimacionDTO);
        }
    }

    public void setPosition (float x, float y)
    {
        this.x = x; this.y = y;
        Object posicionDTO = new PlayerDTO.PositionPlayer(this, x, y);
        notificarActualizacion("setPosition", null, posicionDTO);
    }

    public void setNombre (String nombre)
    {
        this.nombre = nombre;
        Object nombreDTO = new PlayerDTO.NombrePlayer(nombre);
        notificarActualizacion("setNombre", null, nombreDTO);
    }

    public void setNivel (int nivel)
    {
        this.nivel = nivel;
        Object nivelDTO = new PlayerDTO.NivelPlayer(nivel);
        notificarActualizacion("setNivel", null, nivelDTO);

    }

    public void setActualHPs (float hps)
    {
        actualHPs = hps;
        Object HPsDTO = new PlayerDTO.ActualHPsPlayer(hps);
        notificarActualizacion("setActualHPs", null, HPsDTO);
    }

    public void setMaxHPs (float mHps)
    {
        maxHPs = mHps;
        Object mHPsDTO = new PlayerDTO.MaxHPs(mHps);
        notificarActualizacion("setMaxHPs", null, mHPsDTO);
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
