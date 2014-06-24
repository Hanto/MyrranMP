package Interfaces.Spell;// Created by Hanto on 09/06/2014.

import Core.SkillStat;
import Interfaces.EntidadesPropiedades.Caster;

import java.util.Iterator;

public interface TipoSpellI
{
    //SET
    public void setID(String id);
    public void setNombre (String nombre);
    public void setDescripcion (String descripcion);
    public void setSkillStat(SkillStat skillStat, int numStat);

    //GET
    public String getID();
    public String getNombre();
    public String getDescripcion ();
    public SkillStat getSkillStat(int numSkillStat);
    public Iterator<SkillStat> getSkillStats();
    public int getNumSkillStats();

    //METODOS:
    public void inicializarSkillStats();
    public void ejecutarCasteo(SpellI spell, Caster caster, int targetX, int targetY);
}
