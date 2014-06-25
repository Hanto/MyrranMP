package Model.Classes.Mobiles;// Created by Hanto on 10/04/2014.

import Core.SkillTalentos;
import DB.DAO;
import DTO.NetDTO;
import Interfaces.BDebuff.AuraI;
import Interfaces.BDebuff.BDebuffI;
import Interfaces.EntidadesPropiedades.CasterConTalentos;
import Interfaces.EntidadesPropiedades.Debuffeable;
import Interfaces.EntidadesPropiedades.Vulnerable;
import Interfaces.EntidadesTipos.MobPlayer;
import Interfaces.Geo.MapaI;
import Interfaces.Input.PlayerIOI;
import Interfaces.Model.AbstractModel;
import Interfaces.Spell.SpellI;
import Model.DTO.PlayerDTO;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Player extends AbstractModel implements MobPlayer, CasterConTalentos, Vulnerable, Debuffeable
{
    protected int connectionID;
    protected MapaI mapaI;                         //mapaI al que pertecene el Player

    protected float x = 0.0f;                      //Coordenadas X:
    protected float y = 0.0f;                      //Coordenadas Y:

    protected int numAnimacion = 5;

    protected float velocidadMax = 80.0f;
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

    private Array<AuraI> listaDeAuras = new Array<>();
    private Map<String, SkillTalentos> talentos = new HashMap<>();

    protected boolean irArriba = false;
    protected boolean irAbajo = false;
    protected boolean irDerecha = false;
    protected boolean irIzquierda = false;
    protected boolean disparar = false;

    public Player()
    {
        añadirSpellTalentos("Heal");
    }

    //GET:
    @Override public int getConnectionID()                      { return connectionID; }
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
    @Override public void setTotalCastingTime(float castingTime){ this.actualCastingTime = 0.01f; totalCastingTime = castingTime;}
    @Override public void setVelocidaMod(float velocidadMod)    { this.velocidadMod = velocidadMod; }
    @Override public void setVelocidadMax(float velocidadMax)   { this.velocidadMax = velocidadMax; }
    @Override public void setDireccion(double direccion)        { }
    @Override public void añadirAura(AuraI aura)                { listaDeAuras.add(aura); }
    @Override public void eliminarAura(AuraI aura)              { listaDeAuras.removeValue(aura, true); }
    @Override public Iterator<AuraI> getAuras()                 { return listaDeAuras.iterator(); }
    @Override public void setActualHPs (float hps)              { modificarHPs(hps - actualHPs); }

    @Override public void añadirSpellTalentos(String spellID)
    {
        SpellI spell = DAO.spellDAOFactory.getSpellDAO().getSpell(spellID);
        if (spell == null) { System.out.println("ERROR: añadirSpellTalentos: spellID no encontrado: " + spellID ); return; }

        SkillTalentos spellTalentos = new SkillTalentos(spell);
        talentos.put(spellTalentos.getId(), spellTalentos);
        Iterator<BDebuffI> iterator = spell.getDebuffsQueAplica();
        while (iterator.hasNext())
        {
            SkillTalentos debuffTalentos = new SkillTalentos(iterator.next());
            talentos.put(debuffTalentos.getId(), debuffTalentos);
        }
    }

    @Override public int getSkillTalentos(String skillID, int statID)
    {
        SkillTalentos skillTalentos = talentos.get(skillID);
        if (skillTalentos == null) return 0;
        else return skillTalentos.getTalento(statID);
    }

    @Override public void setSkillTalento(String skillID, int statID, int valor)
    {
        SkillTalentos skillTalentos = talentos.get(skillID);
        if (skillTalentos == null) { System.out.println("ERROR: setSkillTalento, spellID no existe: " + skillID); return; }
        else
        {
            if (valor <0) { return; }
            if (skillTalentos.getIsSpell())
            {
                SpellI spell = DAO.spellDAOFactory.getSpellDAO().getSpell(skillTalentos.getId());
                int valormax = spell.getSkillStat(statID).getTalentoMaximo();
                if (valor > valormax) return;
            }
            if (!skillTalentos.getIsSpell())
            {
                BDebuffI debuff = DAO.debuffDAOFactory.getBDebuffDAO().getBDebuff(skillTalentos.getId());
                int valormax = debuff.getSkillStat(statID).getTalentoMaximo();
                if (valor > valormax) return;
            }
        }
        Object enviarModificarSkillTalento = new NetDTO.EnviarModificarSkillTalentoPPC(skillID, statID, valor);
        notificarActualizacion("setSkillTalento", null, enviarModificarSkillTalento);
    }

    public void modificarSkillTalento(String skillID, int statID, int valor)
    {
        SkillTalentos skillTalentos = talentos.get(skillID);
        if (skillTalentos == null) { System.out.println("ERROR: setSkillTalento, spellID no existe: " + skillID); return; }
        skillTalentos.setTalento(statID, valor);
        Object modificarSkillTalento = new NetDTO.ModificarSkillTalentoPPC(skillID, statID, valor);
        notificarActualizacion("modificarSkillTalento", null, modificarSkillTalento);
    }

    @Override public void modificarHPs(float HPs)
    {
        actualHPs += HPs;
        if (actualHPs > maxHPs) actualHPs = maxHPs;
        else if (actualHPs < 0) actualHPs = 0f;
        Object modificarHPs = new NetDTO.ModificarHPsPPC(this, HPs);
        notificarActualizacion("modificarHPs", null, modificarHPs);
    }

    @Override public void setNumAnimacion(int numAnimacion)
    {
        if (this.numAnimacion != numAnimacion)
        {
            this.numAnimacion = numAnimacion;
            Object AnimacionDTO = new NetDTO.AnimacionPPC(this);
            notificarActualizacion("setTipoAnimacion", null, AnimacionDTO);
        }
    }

    @Override public void setPosition (float x, float y)
    {
        this.x = x; this.y = y;
        Object posicionDTO = new NetDTO.PosicionPPC(this);
        notificarActualizacion("setPosition", null, posicionDTO);
    }

    @Override public void setNombre (String nombre)
    {
        this.nombre = nombre;
        Object nombreDTO = new PlayerDTO.NombrePlayer(nombre);
        notificarActualizacion("setNombre", null, nombreDTO);
    }

    @Override public void setNivel (int nivel)
    {
        this.nivel = nivel;
        Object nivelDTO = new PlayerDTO.NivelPlayer(nivel);
        notificarActualizacion("setNivel", null, nivelDTO);
    }

    @Override public void setMaxHPs (float mHps)
    {
        maxHPs = mHps;
        Object mHPsDTO = new PlayerDTO.MaxHPs(mHps);
        notificarActualizacion("setMaxHPs", null, mHPsDTO);
    }

    @Override public void setParametrosSpell(Object parametros)
    {
        parametrosSpell = parametros;
        Object setParametrosSpell = new PlayerDTO.SetParametrosSpell();
        notificarActualizacion("setParametrosSpell", null, setParametrosSpell);
    }

    @Override public void setSpellIDSeleccionado(String spellID)
    {
        spellIDSeleccionado = spellID;
        Object spellIDSeleccionadoDTO = new PlayerDTO.SetSpellIDSeleccionado(spellID);
        notificarActualizacion("setSpellIDSeleccionado", null, spellIDSeleccionadoDTO);
    }

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
            Object castearDTO = new NetDTO.CastearPPC(castear, screenX, screenY);
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

                Object castearDTO = new NetDTO.CastearPPC(castear, screenX, screenY);
                notificarActualizacion("setCastear", null, castearDTO);
                //actualCastingTime += 0.01f;
                castearInterrumpible = true;
            }
        }
    }




    public void setInput (PlayerIOI playerInput)
    {
        irArriba = playerInput.getIrArriba();
        irAbajo = playerInput.getIrAbajo();
        irDerecha = playerInput.getIrDerecha();
        irIzquierda = playerInput.getirIzquierda();

        disparar = playerInput.getDisparar();

        setNumAnimacion(playerInput.getNumAnimacion());

        if (playerInput.getStopCastear()) setCastear(false, playerInput.getScreenX(), playerInput.getScreenY());
        else if (playerInput.getStartCastear()) setCastear (true, playerInput.getScreenX(), playerInput.getScreenY());
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

            Object castingTimePercent = new NetDTO.CastingTimePercent(this);
            notificarActualizacion("actualizarCastingTime", null, castingTimePercent);
        }
    }

    public void actualizar (float delta)
    {
        moverse(delta);
        actualizarCastingTime(delta);
        if (castear) startCastear();
    }
}
