package Model.Skill.Spell;
// @author Ivan Delgado Huerta

import Interfaces.Caster;
import Model.DAO.DAO;
import Model.Skill.SkillStat;

public class Spell 
{
    protected int id;
    protected String nombre;
    protected String descripcion;
    
    protected TipoSpell tipoSpell;                                      //Command Pattern: Codigo que se ejecuta al castear el skill
    
    protected SkillStat[] skillStats;                                   //Stats concretos del skill

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
        
        //y se copian sus Stats base:
        skillStats = new SkillStat[tipospell.skillStat().length];
        for (int i=0; i<skillStats.length;i++)
        {
            SkillStat statSkill = new SkillStat(tipospell.skillStat()[i]);
            skillStats[i] = statSkill;       
        }
    }

    public Spell (int tipoSpellID)
    {
        tipoSpell = DAO.tipoSpellDAO.newInstance().getTipoSpell(tipoSpellID);

        if (tipoSpell == null) { System.out.println("ERROR: spellID no encontrado"); return; }

        skillStats = new SkillStat[tipoSpell.skillStat().length];
        for (int i=0; i<skillStats.length;i++)
        {
            SkillStat statSkill = new SkillStat(tipoSpell.skillStat()[i]);
            skillStats[i] = statSkill;
        }
    }
    
    public void castear (Caster caster, int targetX, int targetY)
    {
        if (caster.isCasteando()) { }
        else 
        {   //Marcamos al personaje como Casteando, y actualizamos su tiempo de casteo con el que marque el Spell (Stat Slot 0)
            caster.setTotalCastingTime(skillStats[TipoSpell.STAT_Cast].valorBase);
            tipoSpell.ejecutarCasteo(this, caster, targetX, targetY);
        }
    }
}
