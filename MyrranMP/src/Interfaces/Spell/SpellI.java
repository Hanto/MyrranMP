package Interfaces.Spell;// Created by Hanto on 09/06/2014.

import Data.Spell.SkillStat;

public interface SpellI
{
    //SET
    public void setID(String id);
    public void setNombre (String nombre);
    public void setDescripcion (String descripcion);
    //GET:
    public String getID();
    public String getNombre ();
    public String getDescripcion ();
    public SkillStat[] skillStats ();
}
