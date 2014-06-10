package Model.Classes.Skill.BDebuff;// Created by Hanto on 04/06/2014.


import Data.Spell.SkillStat;
import Interfaces.BDebuff.AuraI;
import Interfaces.BDebuff.BDebuffI;
import Interfaces.BDebuff.TipoBDebuffI;
import Interfaces.Entidades.Caster;
import Interfaces.Entidades.Debuffeable;

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
    @Override public SkillStat [] skillStats ()                     { return skillStats; }

    //CONSTRUCTOR:
    public BDebuff (TipoBDebuffI tipoaura)
    {   //Se vincula el objeto que ejecutara los metodos de este tipo de Spell
        tipoBDebuff = tipoaura;

        nombre = tipoaura.getNombre();
        descripcion = tipoaura.getDescripcion();
        isDebuff = tipoaura.getIsDebuff();
        stacksMaximos = tipoaura.getStacksMaximos();

        //y se copian sus Stats base:
        skillStats = new SkillStat[tipoaura.skillStat().length];
        for (int i=0; i<skillStats.length;i++)
        {
            SkillStat statSkill = new SkillStat(tipoaura.skillStat()[i]);
            skillStats[i] = statSkill;
        }
    }

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

    private void aplicarAura(Caster caster, Debuffeable target)
    {
        AuraI aura = auraExisteYEsDelCaster(caster, target);

        if (aura != null)
        {
            aura.setTicksAplicados((byte)0);
            if (aura.getStacks() < stacksMaximos) aura.setStacks((byte) (aura.getStacks()+1));
            //aura.setDuracion(aura.getDuracion()% MiscData.);
        }
        else
        {
            aura = new Aura(tipoBDebuff, caster, target);
            aura.setDuracionMax(skillStats()[TipoBDebuff.STAT_Cast].getValorBase());
            target.añadirAura(aura);
        }
    }

    public void actualizarTick (AuraI aura)
    {   tipoBDebuff.actualizarAura(aura); }
}
