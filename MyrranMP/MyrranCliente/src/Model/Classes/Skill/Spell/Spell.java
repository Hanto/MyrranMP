package Model.Classes.Skill.Spell;
// @author Ivan Delgado Huerta

import Comun.SkillStat;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.Model.AbstractModel;
import Interfaces.Spell.SpellI;
import Interfaces.Spell.TipoSpellI;

public class Spell extends AbstractModel implements SpellI
{
    public static final int STAT_Cast = 0;

    protected String id;
    protected String nombre;
    protected String descripcion;

    protected TipoSpellI tipoSpell;

    protected SkillStat[] skillStats;                           //Stats concretos del skill

    //SET
    @Override public void setID(String id)                      { this.id = id; }
    @Override public void setNombre (String nombre)             { this.nombre = nombre; }
    @Override public void setDescripcion (String descripcion)   { this.descripcion = descripcion; }
    //GET:
    @Override public String getID()                             { return id; }
    @Override public String getNombre ()                        { return nombre; }
    @Override public String getDescripcion ()                   { return descripcion; }
    @Override public SkillStat [] skillStats ()                 { return skillStats; }


    //CONSTRUCTOR:
    public Spell (TipoSpellI tipospell)
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

    public Spell (String tipoSpellID)
    {
        tipoSpell = Datos.DAO.tipoSpellDAOFactory.getTipoSpellDAO().getTipoSpell(tipoSpellID);

        if (tipoSpell == null) { System.out.println("ERROR: spellID no encontrado"); return; }

        nombre = tipoSpell.getNombre();
        descripcion = tipoSpell.getDescripcion();

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
            caster.setTotalCastingTime(skillStats[STAT_Cast].getValorBase());
        }
    }
}
