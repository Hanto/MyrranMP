package Model.Classes.Skill.BDebuff;// Created by Hanto on 04/06/2014.

import Core.Skills.SkillStat;
import Interfaces.Model.AbstractModel;
import Interfaces.BDebuff.TipoBDebuffI;

import java.util.Arrays;
import java.util.Iterator;

public abstract class TipoBDebuff extends AbstractModel implements TipoBDebuffI
{
    protected String id;
    protected String nombre;
    protected String descripcion;
    protected boolean isDebuff = false;

    private byte stacksMaximos = 0;
    private SkillStat[] skillStats;

    //SET
    @Override public void setID(String id)                              { this.id = id; }
    @Override public void setNombre (String nombre)                     { this.nombre = nombre; }
    @Override public void setDescripcion (String descripcion)           { this.descripcion = descripcion; }
    @Override public void setIsDebuff (boolean b)                       { isDebuff = b; }
    @Override public void setStacksMaximos (byte i)                     { stacksMaximos = i; }
    @Override public void setSkillStat(SkillStat skillStat, int statID) { skillStats[statID] = skillStat; }
    @Override public void setNumSkillStats(int numSkillStats)           { skillStats = new SkillStat[numSkillStats]; }

    //GET
    @Override public String getID()                                     { return id; }
    @Override public String getNombre()                                 { return nombre; }
    @Override public String getDescripcion ()                           { return descripcion; }
    @Override public boolean getIsDebuff ()                             { return isDebuff; }
    @Override public byte getStacksMaximos ()                           { return stacksMaximos; }
    @Override public SkillStat getSkillStat(int statID)                 { return skillStats[statID]; }
    @Override public Iterator<SkillStat> getSkillStats()                { return Arrays.asList(skillStats).iterator(); }
    @Override public int getNumSkillStats()                             { return skillStats.length; }


    //CONSTRUCTOR:
    public TipoBDebuff()
    {
        inicializarSkillStats();
    }
}
