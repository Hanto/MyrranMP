package Interfaces.Spell;// Created by Hanto on 09/06/2014.

import Core.Skills.SkillStat;
import Interfaces.BDebuff.BDebuffI;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.EntidadesPropiedades.Debuffeable;
import Interfaces.Skill.SkillI;

import java.util.Iterator;

public interface SpellI extends SkillI
{
    //SET
    public void setID(String id);
    public void setNombre (String nombre);
    public void setDescripcion (String descripcion);
    public void setTipoSpell(TipoSpellI tipoSpell);

    //GET:
    public String getID();
    public String getNombre ();
    public String getDescripcion ();
    public TipoSpellI getTipoSpell();
    public float getValorTotal(Caster caster, int statID);
    public SkillStat getSkillStat(int statID);
    public Iterator<SkillStat> getSkillStats();
    public Iterator<BDebuffI> getDebuffsQueAplica();
    public int getNumSkillStats();
    public int getNumDebuffsQueAplica();
    public SkillI getSkill(String skillID);

    //METODOS:
    public void añadirDebuff (BDebuffI debuff);
    public void añadirDebuff (String debuffID);
    public void castear (Caster caster, int targetX, int targetY);
    public void aplicarDebuffs (Caster caster, Debuffeable target);
}
