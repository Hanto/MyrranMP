package Interfaces.Spell;// Created by Hanto on 09/06/2014.

import Comun.SkillStat;
import Interfaces.EntidadesPropiedades.Caster;

public interface TipoSpellI
{
    //SET
    public void setID(String id);
    public void setNombre (String nombre);
    public void setDescripcion (String descripcion);

    //GET
    public String getID();
    public String getNombre();
    public String getDescripcion ();
    public SkillStat[] skillStat ();

    //METODOS:
    public void inicializarSkillStats();
    public void ejecutarCasteo(SpellI spell, Caster caster, int targetX, int targetY);
}
