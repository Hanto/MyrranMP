package Model.Classes.Mobiles;// Created by Hanto on 07/04/2014.


import DTO.NetDTO;
import Interfaces.BDebuff.AuraI;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.EntidadesPropiedades.Debuffeable;
import Interfaces.EntidadesPropiedades.Vulnerable;
import Interfaces.EntidadesTipos.MobPC;
import Interfaces.Geo.MapaI;
import Interfaces.Model.AbstractModel;
import Interfaces.Spell.SpellI;
import Model.Classes.Geo.Mapa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PC extends AbstractModel implements MobPC, Caster, Vulnerable, Debuffeable
{
    protected int connectionID;                                 //ID de la conexion con el servidor
    protected MapaI mapaI;                                      //mapaI al que pertecene el Player

    protected float x=0.0f;                                     //Coordenadas X:
    protected float y=0.0f;                                     //Coordenadas Y:
    protected int numAnimacion = 5;

    protected float velocidadMod=1.0f;                          //Modificadores de Velocidad: debido a Snares, a Sprints, Roots
    protected float velocidadMax;                               //Velocidad Maxima:
    protected double direccion;                                 //Direccion Actual en Radianes

    protected String nombre = "Hanto";
    protected int nivel = 1;

    protected float actualHPs=1;
    protected float maxHPs=2000;

    protected boolean castear = false;

    protected int targetX = 0;
    protected int targetY = 0;

    protected float actualCastingTime = 0.0f;
    protected float totalCastingTime = 0.0f;
    protected String spellIDSeleccionado = null;
    protected Object parametrosSpell;

    protected List<AuraI>listaDeAuras = new ArrayList<>();


    //Constructor:
    public PC(int connectionID, Mapa mapa)
    {
        this.connectionID = connectionID;
        this.mapaI = mapa;
    }

    //GET:
    @Override public int getConnectionID ()                     { return connectionID; }
    @Override public float getX()                               { return x; }
    @Override public float getY()                               { return y; }
    @Override public int getNumAnimacion()                      { return numAnimacion; }
    @Override public float getVelocidadMod()                    { return velocidadMod; }
    @Override public float getVelocidadMax()                    { return velocidadMax; }
    @Override public double getDireccion()                      { return direccion; }
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

    //SET:
    @Override public void setConnectionID (int connectionID)    { this.connectionID = connectionID; }
    @Override public void setTotalCastingTime(float castingTime){ actualCastingTime = 0.01f; totalCastingTime = castingTime;}
    @Override public void setVelocidaMod(float velocidadMod)    { this.velocidadMod = velocidadMod; }
    @Override public void setVelocidadMax(float velocidadMax)   { this.velocidadMax = velocidadMax; }
    @Override public void setDireccion(double direccion)        { this.direccion = direccion; }
    @Override public void setSpellIDSeleccionado(String spellID){ spellIDSeleccionado = spellID; }
    @Override public void setParametrosSpell(Object parametros) { parametrosSpell = parametros; }
    @Override public void setNombre(String nombre)              { this.nombre = nombre; }
    @Override public void setNivel(int nivel)                   { this.nivel = nivel; }
    @Override public void añadirAura(AuraI aura)                { listaDeAuras.add(aura); }
    @Override public void eliminarAura(AuraI aura)              { listaDeAuras.remove(aura); }
    @Override public Iterator<AuraI> getAuras()                 { return listaDeAuras.iterator(); }
    @Override public void setMaxHPs(float HPs)                  { maxHPs = HPs; }
    @Override public void setActualHPs(float HPs)               { modificarHPs(HPs - actualHPs); }

    @Override public void modificarHPs(float HPs)
    {
        actualHPs += HPs;
        if (actualHPs > maxHPs) actualHPs = maxHPs;
        else if (actualHPs < 0) actualHPs = 0;
        Object modificarHPs = new NetDTO.ModificarHPsPPC(this, HPs);
        notificarActualizacion("modificarHPs", null, modificarHPs);
    }

    @Override public void setCastear (boolean castear, int targetX, int targetY)
    {
        this.castear = castear;
        this.targetX = targetX;
        this.targetY = targetY;
        if (castear) castear();
    }

    @Override public void setPosition(float x, float y)
    {
        this.x = x; this.y = y;
        Object posicionDTO = new NetDTO.PosicionPPC(this);
        notificarActualizacion("setPosition", null, posicionDTO);
    }

    @Override public void setNumAnimacion(int numAnimacion)
    {
        if (this.numAnimacion != numAnimacion)
        {
            this.numAnimacion = numAnimacion;
            Object animacionDTO = new NetDTO.AnimacionPPC(this);
            notificarActualizacion("setNumAnimacion", null, animacionDTO);
        }
    }

    public void eliminar()
    {
        Object eliminarDTO = new NetDTO.EliminarPPC(this);
        notificarActualizacion("eliminar", null, eliminarDTO);
    }

    private void castear()
    {
        if (!isCasteando())
        {
            SpellI spell = DB.DAO.spellDAOFactory.getSpellDAO().getSpell(spellIDSeleccionado);
            if (spell != null)
            {
                spell.castear(this, targetX, targetY);
                //actualCastingTime += 0.01f;
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

    public void actualizarAuras (float delta)
    {
        AuraI aura;
        Iterator<AuraI> aurasIteator = getAuras();
        while (aurasIteator.hasNext())
        {
            aura = aurasIteator.next();
            aura.actualizarAura(delta);
            if (aura.getDuracion() >= aura.getDuracionMax())
                aurasIteator.remove();
        }
    }

    public void actualizar(float delta)
    {
        actualizarCastingTime(delta);
        actualizarAuras(delta);
        if (castear) castear();
    }
}
