package Core.Skills;// Created by Hanto on 25/06/2014.

import Interfaces.Skill.SkillI;
import Interfaces.Skill.SkillPersonalizadoI;

import java.util.Arrays;
import java.util.Iterator;

public class SkillPersonalizado implements SkillPersonalizadoI
{
    private String id;
    private SkillI skill;
    private SkillMod[] skillMods;

    //SET:
    public void setID(String id)                            { this.id = id; }
    public void setNumTalentos(int statID, int valor)       { skillMods[statID].setNumTalentos(valor); }
    public void setBonosPorObjetos(int statID, float valor) { skillMods[statID].setBonosPorObjetos(valor); }

    //GET:
    @Override public String getID()                         { return id; }
    @Override public String getNombre()                     { return skill.getNombre(); }
    @Override public int getNumSkillStats()                 { return skill.getNumSkillStats(); }

    @Override public String getNombre(int statID)           { return skill.getSkillStat(statID).getNombre(); }
    @Override public int getNumTalentos(int statID)         { return skillMods[statID].getNumTalentos(); }
    @Override public float getValorBase(int statID)         { return skill.getSkillStat(statID).getValorBase(); }
    @Override public float getBonoTalento (int statID)      { return skill.getSkillStat(statID).getBonoTalento(); }
    @Override public float getBonosPorObjetos(int statID)   { return skillMods[statID].getBonosPorObjetos(); }
    @Override public int getCosteTalento(int statID)        { return skill.getSkillStat(statID).getCosteTalento(); }
    @Override public int getTalentoMaximo(int statID)       { return skill.getSkillStat(statID).getTalentoMaximo(); }
    @Override public boolean getIsMejorable(int statID)     { return skill.getSkillStat(statID).getisMejorable(); }
    @Override public Iterator<SkillMod>getIterator()        { return Arrays.asList(skillMods).iterator(); }

    public SkillPersonalizado(SkillI skill)
    {
        this.skill = skill;
        id = skill.getID();

        skillMods = new SkillMod[skill.getNumSkillStats()];
        for (int i = 0; i < skillMods.length; i++)
        {   skillMods[i] = new SkillMod(i); }
    }

    @Override public float getValorTotal(int statID)
    {   return (getValorBase(statID) + getNumTalentos(statID) * getBonoTalento(statID) + getBonosPorObjetos(statID)); }
}
