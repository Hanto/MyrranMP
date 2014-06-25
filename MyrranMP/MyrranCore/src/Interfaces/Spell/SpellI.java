package Interfaces.Spell;// Created by Hanto on 09/06/2014.

import Core.SkillStat;
import Interfaces.BDebuff.BDebuffI;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.EntidadesPropiedades.Debuffeable;

import java.util.Iterator;

public interface SpellI extends Skill
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
    public float getTalentedSkillStat(Caster caster, int statID);
    public SkillStat getSkillStat(int statID);
    public Iterator<SkillStat> getSkillStats();
    public Iterator<BDebuffI> getDebuffsQueAplica();
    public int getNumSkillStats();

    //METODOS:
    public void añadirDebuff (BDebuffI debuff);
    public void añadirDebuff (String debuffID);
    public void castear (Caster caster, int targetX, int targetY);
    public void aplicarDebuffs (Caster caster, Debuffeable target);
}
