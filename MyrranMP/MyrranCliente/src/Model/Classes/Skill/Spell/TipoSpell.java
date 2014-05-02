package Model.Classes.Skill.Spell;
// @author Ivan Delgado Huerta

import Model.Classes.AbstractModel;
import Model.Classes.Skill.SkillStat;

public abstract class TipoSpell extends AbstractModel implements TipoSpellInterface
{
    public static final int STAT_Cast = 0;
    
    protected String id;
    protected String nombre;
    protected String descripcion;
    
    protected SkillStat[] skillStats;
    
    //SET
    public void setID(String id)                        { this.id = id; }
    public void setNombre (String nombre)               { this.nombre = nombre; }
    public void setDescripcion (String descripcion)     { this.descripcion = descripcion; }
    //GET
    public String getID()                               { return id; }
    public String getNombre()                           { return nombre; }
    public String getDescripcion ()                     { return descripcion; }

    public SkillStat [] skillStat ()                    { return skillStats; }

    
    //CONSTRUCTOR:
    public TipoSpell()
    {
        inicializarSkillStats();
    }
}
