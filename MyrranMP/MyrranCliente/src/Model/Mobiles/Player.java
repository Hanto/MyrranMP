package Model.Mobiles;// Created by Hanto on 10/04/2014.

import Controller.Input.PlayerIO;
import Interfaces.Caster;
import Interfaces.MapaI;
import Model.AbstractModel;
import Model.DAO.DAO;
import Model.DTO.PlayerDTO;
import Model.Skill.Spell.Spell;

public class Player extends AbstractModel implements Caster
{
    protected Integer connectionID;
    protected MapaI mapaI;                                      //mapaI al que pertecene el Player

    protected Float x = 0.0f;                      //Coordenadas X:
    protected Float y = 0.0f;                      //Coordenadas Y:

    protected Integer numAnimacion = 5;

    protected Float velocidadMax = 80.0f;
    protected Float velocidadMod = 1.0f;

    protected String nombre;
    protected Integer nivel;

    protected Float actualHPs;
    protected Float maxHPs;

    protected Boolean castear = false;
    protected Boolean castearInterrumpible = false;

    protected Integer screenX = 0;
    protected Integer screenY = 0;
    protected Float actualCastingTime = 0.0f;
    protected Float totalCastingTime = 0.0f;
    protected Integer spellIDSeleccionado = -1;

    protected Boolean irArriba = false;
    protected Boolean irAbajo = false;
    protected Boolean irDerecha = false;
    protected Boolean irIzquierda = false;
    protected Boolean disparar = false;

    //GET:
    public int getConnectionID()                                { return connectionID; }
    public String getNombre()                                   { return nombre; }
    public Integer getNivel()                                   { return nivel; }
    public Float getActualHPs()                                 { return actualHPs; }
    public Float getMaxHPs()                                    { return maxHPs; }
    public float getX()                                         { return x; }
    public float getY()                                         { return y; }

    //CASTER:
    @Override public MapaI getMapa()                            { return mapaI; }
    @Override public boolean isCasteando()                      { if (actualCastingTime >0) return true; else return false; }
    @Override public float getActualCastingTime()               { return actualCastingTime; }
    @Override public float getTotalCastingTime()                { return totalCastingTime; }
    @Override public int getSpellIDSeleccionado()               { return spellIDSeleccionado; }
    @Override public void setTotalCastingTime(float castingTime){ actualCastingTime = 0f; totalCastingTime = castingTime;}
    @Override public void setSpellIDSeleccionado(int spellID)   { spellIDSeleccionado = spellID; }
    @Override public void setCastear(boolean intentaCastear, int clickX, int clickY)
    {
        castear = intentaCastear;
        screenX = clickX;
        screenY = clickY;

        if (castear) startCastear();
        else stopCastear();
    }

    private void stopCastear()
    {
        if (castearInterrumpible)
        {
            Object castearDTO = new PlayerDTO.Castear(castear, screenX, screenY);
            notificarActualizacion("setCastear", null, castearDTO);
            castearInterrumpible = false;
        }
    }

    private void startCastear()
    {
        if (!isCasteando())
        {
            spellIDSeleccionado = 0;

            Spell spell = DAO.spellDAO.nuevo().getSpell(spellIDSeleccionado);
            if (spell != null)
            {
                spell.castear(this, screenX, screenY);

                Object castearDTO = new PlayerDTO.Castear(castear, screenX, screenY);
                notificarActualizacion("setCastear", null, castearDTO);
                actualCastingTime += 0.01f;
                castearInterrumpible = true;
            }
        }
    }

    private void actualizarCastingTime(float delta)
    {
        if (isCasteando())
        {
            actualCastingTime += delta;
            if (actualCastingTime >= totalCastingTime)
            {   setTotalCastingTime(0f); }
        }
    }

    //SET:
    public void setConnectionID (int connectionID)
    {   this.connectionID = connectionID; }

    public void setInput (PlayerIO playerInput)
    {
        irArriba = playerInput.irArriba;
        irAbajo = playerInput.irAbajo;
        irDerecha = playerInput.irDerecha;
        irIzquierda = playerInput.irIzquierda;

        disparar = playerInput.disparar;

        setAnimacion (playerInput.numAnimacion);

        if (playerInput.stopCastear) setCastear(false, playerInput.screenX, playerInput.screenY);
        else if (playerInput.startCastear) setCastear (true, playerInput.screenX, playerInput.screenY);
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
        actualizarCastingTime(delta);
        if (castear) startCastear();
    }
}