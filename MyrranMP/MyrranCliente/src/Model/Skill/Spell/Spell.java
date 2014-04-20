package Model.Skill.Spell;
// @author Ivan Delgado Huerta

import Interfaces.Caster;
import Model.Skill.SkillStat;

public class Spell
{
    public static final int STAT_Cast = 0;

    protected int id;
    protected String nombre;
    protected String descripcion;

    protected TipoSpell tipoSpell;

    protected SkillStat[] skillStats;                   //Stats concretos del skill

    //SET
    public void setID(int id)                           { this.id = id; }
    public void setNombre (String nombre)               { this.nombre = nombre; }
    public void setDescripcion (String descripcion)     { this.descripcion = descripcion; }
    //GET:
    public int getID()                                  { return id; }
    public String getNombre ()                          { return nombre; }
    public String getDescripcion ()                     { return descripcion; }
    
    public SkillStat [] skillStats ()                   { return skillStats; }


    //CONSTRUCTOR:
    public Spell (TipoSpell tipospell)
    {   //Se vincula el objeto que ejecutara los metodos de este tipo de Spell
        tipoSpell = tipospell;

        nombre = tipospell.getNombre();
        descripcion = tipospell.getDescripcion();

        //y se copian sus Stats base:
        skillStats = new SkillStat[tipospell.skillStat().length];
        for (int i=0; i<skillStats.length;i++)
        {
            SkillStat statSkill = new SkillStat(tipospell.skillStat()[i]);
            skillStats[i] = statSkill;
        }
    }
    
    public void castear (Caster caster, int targetX, int targetY)
    {
        if (caster.isCasteando()) { }
        else 
        {   //Marcamos al personaje como Casteando, y actualizamos su tiempo de casteo con el que marque el Spell (Stat Slot 0)
            caster.setTotalCastingTime(skillStats[STAT_Cast].valorBase);
        }
    }
}
