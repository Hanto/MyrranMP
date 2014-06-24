package Model.Classes.Skill.BDebuff;// Created by Hanto on 04/06/2014.


import DB.DAO;
import Data.MiscData;
import Core.SkillStat;
import Interfaces.BDebuff.AuraI;
import Interfaces.BDebuff.BDebuffI;
import Interfaces.BDebuff.TipoBDebuffI;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.EntidadesPropiedades.Debuffeable;

import java.util.Arrays;
import java.util.Iterator;

public class BDebuff implements BDebuffI
{
    protected String id;
    protected String nombre;
    protected String descripcion;
    protected boolean isDebuff = false;
    protected byte stacksMaximos = 0;
    protected TipoBDebuffI tipoBDebuff;                             //Command Pattern: Codigo que se ejecuta al castear el skill
    protected SkillStat[] skillStats;                               //Stats concretos del skill

    //SET
    @Override public void setID(String id)                          { this.id = id; }
    @Override public void setNombre (String nombre)                 { this.nombre = nombre; }
    @Override public void setDescripcion (String descripcion)       { this.descripcion = descripcion; }
    @Override public void setIsDebuff (boolean b)                   { isDebuff = b; }
    @Override public void setStacksMaximos (byte i)                 { stacksMaximos = i; }
    @Override public void setTipoBDebuff(TipoBDebuffI tipoBDebuff)  { this.tipoBDebuff = tipoBDebuff; }

    //GET:
    @Override public String getID()                                 { return id; }
    @Override public String getNombre ()                            { return nombre; }
    @Override public String getDescripcion ()                       { return descripcion; }
    @Override public boolean isDebuff ()                            { return isDebuff; }
    @Override public int getStacksMaximos ()                        { return stacksMaximos; }
    @Override public TipoBDebuffI getTipoBDebuff()                  { return tipoBDebuff; }
    @Override public SkillStat getSkillStat(int numSkillStat)       { return skillStats[numSkillStat]; }
    @Override public Iterator<SkillStat> getSkillStats()            { return Arrays.asList(skillStats).iterator(); }

    //CONSTRUCTOR:
    public BDebuff (TipoBDebuffI tipoBDebuff)
    {   //Se vincula el objeto que ejecutara los metodos de este tipo de Spell
        if (tipoBDebuff == null) { System.out.println("ERROR: TipoSpellID no encontrado."); return; }
        this.tipoBDebuff = tipoBDebuff;

        nombre = tipoBDebuff.getNombre();
        descripcion = tipoBDebuff.getDescripcion();
        isDebuff = tipoBDebuff.getIsDebuff();
        stacksMaximos = tipoBDebuff.getStacksMaximos();

        //y se copian sus Stats base:
        skillStats = new SkillStat[tipoBDebuff.skillStats().length];
        for (int i=0; i<skillStats.length;i++)
        {
            SkillStat statSkill = new SkillStat(tipoBDebuff.skillStats()[i]);
            skillStats[i] = statSkill;
        }
        Arrays.asList(skillStats).iterator();

    }

    public BDebuff (String tipoBDebuffID)
    {   this(DAO.tipoBDebuffDAOFactory.getTipoBDebuffDAO().getTipoBDebuff(tipoBDebuffID)); }

    private AuraI auraExisteYEsDelCaster(Caster caster, Debuffeable target)
    {
        AuraI aura;
        Iterator<AuraI> iterator = target.getAuras();

        while (iterator.hasNext())
        {
            aura = iterator.next();
            if (aura.getDebuff().getID().equals(id) && aura.getCaster() == caster)
            {   return aura; }
        }
        return null;
    }

    @Override public void aplicarDebuff(Caster caster, Debuffeable target)
    {
        AuraI aura = auraExisteYEsDelCaster(caster, target);

        if (aura != null)
        {
            aura.setTicksAplicados((byte)0);
            if (aura.getStacks() < stacksMaximos) aura.setStacks((byte) (aura.getStacks()+1));
            aura.setDuracion(aura.getDuracion() % MiscData.BDEBUFF_DuracionTick);
        }
        else
        {
            aura = new Aura(this, caster, target);
            aura.setDuracionMax(getSkillStat(TipoBDebuff.STAT_Duracion).getValorBase());
            target.añadirAura(aura);
        }
    }

    @Override public void actualizarTick (AuraI aura)
    {   tipoBDebuff.actualizarTick(aura); }
}
