package Model.Classes.Mobiles;// Created by Hanto on 10/04/2014.

import DAO.DAO;
import Interfaces.Model.AbstractModel;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.EntidadesTipos.MobPlayer;
import Interfaces.EntidadesPropiedades.Vulnerable;
import Interfaces.Geo.MapaI;
import Interfaces.Spell.SpellI;
import Interfaces.UI.Input.PlayerIOI;
import Model.DTO.PlayerDTO;

public class Player extends AbstractModel implements Caster, MobPlayer, Vulnerable
{
    protected int connectionID;
    protected MapaI mapaI;                         //mapaI al que pertecene el Player

    protected float x = 0.0f;                      //Coordenadas X:
    protected float y = 0.0f;                      //Coordenadas Y:

    protected int numAnimacion = 5;

    protected float velocidadMax = 360.0f;
    protected float velocidadMod = 1.0f;
    protected double direccion;

    protected String nombre;
    protected int nivel;

    protected float actualHPs;
    protected float maxHPs;

    protected boolean castear = false;
    protected boolean castearInterrumpible = false;

    protected int screenX = 0;
    protected int screenY = 0;
    protected float actualCastingTime = 0.0f;
    protected float totalCastingTime = 0.0f;
    protected String spellIDSeleccionado = null;
    protected Object parametrosSpell;

    protected boolean irArriba = false;
    protected boolean irAbajo = false;
    protected boolean irDerecha = false;
    protected boolean irIzquierda = false;
    protected boolean disparar = false;

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
    @Override public void modificarHPs(float HPs)
    {
        actualHPs += HPs;
        if (actualHPs > maxHPs) actualHPs = maxHPs;
        else if (actualHPs < 0) actualHPs = 0f;
    }

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
            SpellI spell = DAO.spellDAOFactory.getSpellDAO().getSpell(spellIDSeleccionado);
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

    public void setInput (PlayerIOI playerInput)
    {
        irArriba = playerInput.getIrArriba();
        irAbajo = playerInput.getIrAbajo();
        irDerecha = playerInput.getIrDerecha();
        irIzquierda = playerInput.getirIzquierda();

        disparar = playerInput.getDisparar();

        setAnimacion (playerInput.getNumAnimacion());

        if (playerInput.getStopCastear()) setCastear(false, playerInput.getScreenX(), playerInput.getScreenY());
        else if (playerInput.getStartCastear()) setCastear (true, playerInput.getScreenX(), playerInput.getScreenY());
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
        { Y += -velocidadMax*velocidadMod*delta; direccion = 4.71239d; setPosition(X, Y);}
        //Norte
        else if (irArriba && !irDerecha && !irIzquierda)
        { Y += +velocidadMax*velocidadMod*delta; direccion = 1.5708d; setPosition(X, Y);}
        //Este
        else if (irDerecha && !irArriba && !irAbajo)
        { X += +velocidadMax*velocidadMod*delta; direccion =  0d; setPosition(X, Y);}
        //Oeste
        else if (irIzquierda && !irArriba && !irAbajo)
        { X += -velocidadMax*velocidadMod*delta; direccion = 3.14159d; setPosition(X, Y);}
        //SurOeste
        else if (irAbajo&& irIzquierda)
        { Y += -0.707f*velocidadMax*velocidadMod*delta;
          X += -0.707f*velocidadMax*velocidadMod*delta; direccion = 3.927d; setPosition(X, Y);}
        //SurEste
        else if (irAbajo && irDerecha)
        { Y += -0.707f*velocidadMax*velocidadMod*delta;
          X += +0.707f*velocidadMax*velocidadMod*delta; direccion = 5.4779d; setPosition(X, Y);}
        //NorOeste
        else if (irArriba && irIzquierda)
        { Y += +0.707f*velocidadMax*velocidadMod*delta;
          X += -0.707f*velocidadMax*velocidadMod*delta; direccion = 2.35619d; setPosition(X, Y);}
        //NorEste
        else if (irArriba && irDerecha)
        { Y += +0.707f*velocidadMax*velocidadMod*delta;
          X += +0.707f*velocidadMax*velocidadMod*delta; direccion = 0.7854d; setPosition(X, Y);}

    }

    public void actualizar (float delta)
    {
        moverse(delta);
        actualizarCastingTime(delta);
        if (castear) startCastear();
    }
}
