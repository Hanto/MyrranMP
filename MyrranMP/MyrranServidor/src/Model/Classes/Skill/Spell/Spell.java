package Model.Classes.Skill.Spell;
// @author Ivan Delgado Huerta

import Core.SkillStat;
import DB.DAO;
import Interfaces.BDebuff.BDebuffI;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.EntidadesPropiedades.Debuffeable;
import Interfaces.Model.AbstractModel;
import Interfaces.Spell.SpellI;
import Interfaces.Spell.TipoSpellI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Spell extends AbstractModel implements SpellI
{
    public static final int STAT_Cast = 0;

    protected String id;
    protected String nombre;
    protected String descripcion;
    protected TipoSpellI tipoSpell;                              //Command Pattern: Codigo que se ejecuta al castear el skill
    protected SkillStat[] skillStats;                           //Stats concretos del skill
    protected List<BDebuffI> listaDeDebuffsQueAplica = new ArrayList<>();

    //SET
    @Override public void setID(String id)                      { this.id = id; }
    @Override public void setNombre (String nombre)             { this.nombre = nombre; }
    @Override public void setDescripcion (String descripcion)   { this.descripcion = descripcion; }
    @Override public void setTipoSpell(TipoSpellI tipoSpell)    { this.tipoSpell = tipoSpell; }

    //GET:
    @Override public String getID()                             { return id; }
    @Override public String getNombre ()                        { return nombre; }
    @Override public String getDescripcion ()                   { return descripcion; }
    @Override public TipoSpellI getTipoSpell()                  { return tipoSpell; }
    @Override public SkillStat [] skillStats ()                 { return skillStats; }
    @Override public Iterator<BDebuffI> getDebuffsQueAplica()   { return listaDeDebuffsQueAplica.iterator(); }


    //CONSTRUCTOR:
    public Spell (TipoSpellI tipospell)
    {   //Se vincula el objeto que ejecutara los metodos de este tipo de Spell
        tipoSpell = tipospell;

        nombre = tipospell.getNombre();
        descripcion = tipospell.getDescripcion();

        //y se copian sus Stats base:
        skillStats = new SkillStat[tipospell.skillStats().length];
        for (int i=0; i<skillStats.length;i++)
        {
            SkillStat statSkill = new SkillStat(tipospell.skillStats()[i]);
            skillStats[i] = statSkill;       
        }
    }

    public Spell (String tipoSpellID)
    {
        tipoSpell = DAO.tipoSpellDAOFactory.getTipoSpellDAO().getTipoSpell(tipoSpellID);

        if (tipoSpell == null) { System.out.println("ERROR: spellID no encontrado."); return; }

        nombre = tipoSpell.getNombre();
        descripcion = tipoSpell.getDescripcion();

        skillStats = new SkillStat[tipoSpell.skillStats().length];
        for (int i=0; i<skillStats.length;i++)
        {
            SkillStat statSkill = new SkillStat(tipoSpell.skillStats()[i]);
            skillStats[i] = statSkill;
        }
    }


    @Override public void añadirDebuff (BDebuffI debuff)
    {   if (!listaDeDebuffsQueAplica.contains(debuff)) { listaDeDebuffsQueAplica.add(debuff); } }

    @Override public void añadirDebuff (String debuffID)
    {
        BDebuffI debuff = DAO.debuffDAOFactory.getBDebuffDAO().getBDebuff(debuffID);

        if (debuff == null) { System.out.println("ERROR: debuff que añadir al Spell "+id+" no encontrado."); return; }

        añadirDebuff(debuff);
    }

    @Override public void aplicarDebuffs (Caster caster, Debuffeable target)
    {
        for (BDebuffI debuff: listaDeDebuffsQueAplica)
        {   debuff.aplicarDebuff(caster, target);}
    }


    @Override public void castear (Caster caster, int targetX, int targetY)
    {
        if (caster.isCasteando()) { }
        else 
        {   //Marcamos al personaje como Casteando, y actualizamos su tiempo de casteo con el que marque el Spell (Stat Slot 0)
            caster.setTotalCastingTime(skillStats[STAT_Cast].getValorBase());
            tipoSpell.ejecutarCasteo(this, caster, targetX, targetY);
        }
    }
}
