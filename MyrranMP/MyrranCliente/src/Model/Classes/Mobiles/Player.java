package Model.Classes.Mobiles;// Created by Hanto on 10/04/2014.

import Interfaces.*;
import Model.Classes.AbstractModel;
import Model.Classes.Skill.Spell.Spell;
import Model.Classes.UI.Input.PlayerIO;
import Model.DAO.DAO;
import Model.DTO.PlayerDTO;

public class Player extends AbstractModel implements Caster, MobPC, Vulnerable
{
    protected Integer connectionID;
    protected MapaI mapaI;                         //mapaI al que pertecene el Player

    protected Float x = 0.0f;                      //Coordenadas X:
    protected Float y = 0.0f;                      //Coordenadas Y:

    protected Integer numAnimacion = 5;

    protected Float velocidadMax = 160.0f;
    protected Float velocidadMod = 1.0f;
    protected Double direccion;

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
    protected String spellIDSeleccionado = null;
    protected Object parametrosSpell;

    protected Boolean irArriba = false;
    protected Boolean irAbajo = false;
    protected Boolean irDerecha = false;
    protected Boolean irIzquierda = false;
    protected Boolean disparar = false;

    //GET:
    @Override public int getConnectionID()                      { return connectionID; }
    @Override public float getX()                               { return x; }
    @Override public float getY()                               { return y; }
    @Override public float getVelocidadMod()                    { return velocidadMod; }
    @Override public float getVelocidadMax()                    { return velocidadMax; }
    @Override public double getDireccion()                      { return direccion; }
    @Override public int getNumAnimacion()                      { return numAnimacion; }
    @Override public String getNombre()                         { return nombre; }
    @Override public int getNivel()                             { return nivel; }

    @Override public float getActualHPs()                       { return actualHPs; }
    @Override public float getMaxHPs()                          { return maxHPs; }

    @Override public MapaI getMapa()                            { return mapaI; }
    @Override public boolean isCasteando()                      { if (actualCastingTime >0) return true; else return false; }
    @Override public float getActualCastingTime()               { return actualCastingTime; }
    @Override public float getTotalCastingTime()                { return totalCastingTime; }
    @Override public String getSpellIDSeleccionado()            { return spellIDSeleccionado; }
    @Override public Object getParametrosSpell()                { return parametrosSpell; }
    @Override public void setTotalCastingTime(float castingTime){ actualCastingTime = 0.01f; totalCastingTime = castingTime;}
    @Override public void setVelocidaMod(float velocidadMod)    { this.velocidadMod = velocidadMod; }
    @Override public void setVelocidadMax(float velocidadMax)   { this.velocidadMax = velocidadMax; }
    @Override public void setDireccion(double direccion)        { }
    @Override public void setParametrosSpell(Object parametros)
    {
        parametrosSpell = parametros;
        Object setParametrosSpell = new PlayerDTO.SetParametrosSpell();
        notificarActualizacion("setParametrosSpell", null, setParametrosSpell);
    }

    @Override public void setCastear(boolean intentaCastear, int clickX, int clickY)
    {
        castear = intentaCastear;
        screenX = clickX;
        screenY = clickY;

        if (castear) startCastear();
        else stopCastear();
    }

    @Override public void setSpellIDSeleccionado(String spellID)
    {
        spellIDSeleccionado = spellID;
        Object spellIDSeleccionadoDTO = new PlayerDTO.SetSpellIDSeleccionado(spellID);
        notificarActualizacion("setSpellIDSeleccionado", null, spellIDSeleccionadoDTO);
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
            Spell spell = DAO.spellDAOFactory.getSpellDAO().getSpell(spellIDSeleccionado);
            if (spell != null)
            {
                spell.castear(this, screenX, screenY);

                Object castearDTO = new PlayerDTO.Castear(castear, screenX, screenY);
                notificarActualizacion("setCastear", null, castearDTO);
                //actualCastingTime += 0.01f;
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
            {
                actualCastingTime = 0f;
                totalCastingTime = 0f;
            }
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
            notificarActualizacion("setTipoAnimacion", null, AnimacionDTO);
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

    @Override public void setActualHPs (float hps)
    {
        actualHPs = hps;
        Object HPsDTO = new PlayerDTO.ActualHPsPlayer(hps);
        notificarActualizacion("setActualHPs", null, HPsDTO);
    }

    @Override public void setMaxHPs (float mHps)
    {
        maxHPs = mHps;
        Object mHPsDTO = new PlayerDTO.MaxHPs(mHps);
        notificarActualizacion("setMaxHPs", null, mHPsDTO);
    }

    private void moverse (float delta)
    {
        float X=x;
        float Y=y;

        //Sur
        if (irAbajo && !irDerecha && !irIzquierda)
        { Y += -velocidadMax*velocidadMod*delta; direccion = 4.71239d;}
        //Norte
        else if (irArriba && !irDerecha && !irIzquierda)
        { Y += +velocidadMax*velocidadMod*delta; direccion = 1.5708d; }
        //Este
        else if (irDerecha && !irArriba && !irAbajo)
        { X += +velocidadMax*velocidadMod*delta; direccion =  0d; }
        //Oeste
        else if (irIzquierda && !irArriba && !irAbajo)
        { X += -velocidadMax*velocidadMod*delta; direccion = 3.14159d; }
        //SurOeste
        else if (irAbajo&& irIzquierda)
        { Y += -0.707f*velocidadMax*velocidadMod*delta;
          X += -0.707f*velocidadMax*velocidadMod*delta; direccion = 3.927d; }
        //SurEste
        else if (irAbajo && irDerecha)
        { Y += -0.707f*velocidadMax*velocidadMod*delta;
          X += +0.707f*velocidadMax*velocidadMod*delta; direccion = 5.4779d; }
        //NorOeste
        else if (irArriba && irIzquierda)
        { Y += +0.707f*velocidadMax*velocidadMod*delta;
          X += -0.707f*velocidadMax*velocidadMod*delta; direccion = 2.35619d; }
        //NorEste
        else if (irArriba && irDerecha)
        { Y += +0.707f*velocidadMax*velocidadMod*delta;
          X += +0.707f*velocidadMax*velocidadMod*delta; direccion = 0.7854d; }

        setPosition(X, Y);
    }

    public void actualizar (float delta)
    {
        moverse(delta);
        actualizarCastingTime(delta);
        if (castear) startCastear();
    }
}
