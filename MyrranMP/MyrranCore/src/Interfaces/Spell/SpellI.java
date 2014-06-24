package Interfaces.Spell;// Created by Hanto on 09/06/2014.

import Core.SkillStat;
import Interfaces.BDebuff.BDebuffI;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.EntidadesPropiedades.Debuffeable;

import java.util.Iterator;

public interface SpellI
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
    public SkillStat getSkillStat(int numSkillStat);
    public Iterator<SkillStat> getSkillStats();
    public Iterator<BDebuffI> getDebuffsQueAplica();

    //METODOS:
    public void añadirDebuff (BDebuffI debuff);
    public void añadirDebuff (String debuffID);
    public void castear (Caster caster, int targetX, int targetY);
    public void aplicarDebuffs (Caster caster, Debuffeable target);
}
