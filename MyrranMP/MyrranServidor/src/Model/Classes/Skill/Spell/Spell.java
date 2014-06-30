package Model.Classes.Skill.Spell;
// @author Ivan Delgado Huerta

import Core.Skills.SkillStat;
import DB.DAO;
import Interfaces.BDebuff.BDebuffI;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.EntidadesPropiedades.CasterConTalentos;
import Interfaces.EntidadesPropiedades.Debuffeable;
import Interfaces.Model.AbstractModel;
import Interfaces.Skill.SkillI;
import Interfaces.Spell.SpellI;
import Interfaces.Spell.TipoSpellI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Spell extends AbstractModel implements SpellI
{
    public final int STAT_Cast = 0;

    protected String id;
    protected String nombre;
    protected String descripcion;
    protected TipoSpellI tipoSpell;                              //Command Pattern: Codigo que se ejecuta al castear el skill

    private SkillStat[] skillStats;                             //Stats concretos del skill
    private List<BDebuffI> listaDeDebuffsQueAplica = new ArrayList<>();

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
    @Override public SkillStat getSkillStat(int statID)         { return skillStats[statID]; }
    @Override public Iterator<SkillStat> getSkillStats()        { return Arrays.asList(skillStats).iterator(); }
    @Override public Iterator<BDebuffI> getDebuffsQueAplica()   { return listaDeDebuffsQueAplica.iterator(); }
    @Override public int getNumSkillStats()                     { return skillStats.length; }
    @Override public int getNumDebuffsQueAplica()               { return listaDeDebuffsQueAplica.size(); }


    //CONSTRUCTOR:
    public Spell (TipoSpellI tipospell)
    {   //Se vincula el objeto que ejecutara los metodos de este tipo de Spell
        if (tipospell == null) { System.out.println("ERROR: spellID no encontrado."); return; }
        tipoSpell = tipospell;

        nombre = tipospell.getNombre();
        descripcion = tipospell.getDescripcion();

        //y se copian sus Stats base:
        skillStats = new SkillStat[tipospell.getNumSkillStats()];
        for (int i=0; i<skillStats.length;i++)
        {
            SkillStat statSkill = new SkillStat(tipospell.getSkillStat(i));
            skillStats[i] = statSkill;       
        }
    }

    public Spell (String tipoSpellID)
    {   this(DAO.tipoSpellDAOFactory.getTipoSpellDAO().getTipoSpell(tipoSpellID)); }

    @Override public SkillI getSkill(String skillID)
    {
        if (id.equals(skillID)) return this;
        else
        {
            Iterator<BDebuffI> debuffIIterator = getDebuffsQueAplica();
            BDebuffI debuff;
            while (debuffIIterator.hasNext())
            {
                debuff = debuffIIterator.next();
                if (debuff.getID().equals(skillID)) { return debuff; }
            }
        }
        return null;
    }

    @Override public void añadirDebuff (BDebuffI debuff)
    {
        if (debuff == null) { System.out.println("ERROR: debuff que añadir al Spell "+id+" no encontrado."); return; }
        if (!listaDeDebuffsQueAplica.contains(debuff)) { listaDeDebuffsQueAplica.add(debuff); }
    }

    @Override public void añadirDebuff (String debuffID)
    {   añadirDebuff(DAO.debuffDAOFactory.getBDebuffDAO().getBDebuff(debuffID)); }

    @Override public void aplicarDebuffs (Caster caster, Debuffeable target)
    {
        for (BDebuffI debuff: listaDeDebuffsQueAplica)
        {   debuff.aplicarDebuff(caster, target);}
    }

    @Override public float getValorTotal(Caster caster, int statID)
    {
        if (caster instanceof CasterConTalentos)
        {   return ((CasterConTalentos)caster).getSkillPersonalizado(id).getValorTotal(statID); }
        else return getSkillStat(statID).getValorBase();
    }

    @Override public void castear (Caster caster, int targetX, int targetY)
    {
        if (caster.isCasteando()) { }
        else
        {   //Marcamos al personaje como Casteando, y actualizamos su tiempo de casteo con el que marque el Spell (Stat Slot 0)
            caster.setTotalCastingTime(getValorTotal(caster, STAT_Cast));
            tipoSpell.ejecutarCasteo(this, caster, targetX, targetY);
        }
    }
}
