package Interfaces.Spell;// Created by Hanto on 09/06/2014.

import Core.SkillStat;
import Interfaces.EntidadesPropiedades.Caster;

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
    public SkillStat[] skillStats ();

    //METODOS:
    public void castear (Caster caster, int targetX, int targetY);
}
