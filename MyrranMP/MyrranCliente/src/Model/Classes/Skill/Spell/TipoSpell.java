package Model.Classes.Skill.Spell;
// @author Ivan Delgado Huerta

import Core.SkillStat;
import Interfaces.Model.AbstractModel;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.Spell.SpellI;
import Interfaces.Spell.TipoSpellI;

public abstract class TipoSpell extends AbstractModel implements TipoSpellI
{
    public static final int STAT_Cast = 0;
    
    protected String id;
    protected String nombre;
    protected String descripcion;
    
    protected SkillStat[] skillStats;
    
    //SET
    @Override public void setID(String id)                      { this.id = id; }
    @Override public void setNombre (String nombre)             { this.nombre = nombre; }
    @Override public void setDescripcion (String descripcion)   { this.descripcion = descripcion; }
    //GET
    @Override public String getID()                             { return id; }
    @Override public String getNombre()                         { return nombre; }
    @Override public String getDescripcion ()                   { return descripcion; }
    @Override public SkillStat [] skillStats()                  { return skillStats; }

    
    //CONSTRUCTOR:
    public TipoSpell()
    {
        inicializarSkillStats();
    }

    @Override public void ejecutarCasteo(SpellI spell, Caster caster, int targetX, int targetY) {}
}
