package Interfaces.Skill;// Created by Hanto on 24/06/2014.

import Core.Skills.SkillStat;
import Interfaces.EntidadesPropiedades.Caster;

import java.util.Iterator;

public interface SkillI
{
    public void setID(String id);
    public void setNombre (String nombre);
    public void setDescripcion (String descripcion);

    public float getValorTotal(Caster caster, int statID);
    public String getID();
    public String getNombre ();
    public String getDescripcion ();
    public SkillStat getSkillStat(int numSkillStat);
    public Iterator<SkillStat> getSkillStats();
    public int getNumSkillStats();
}
