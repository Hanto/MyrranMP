package Model.Classes.Skill.BDebuff;// Created by Hanto on 04/06/2014.


import DAO.DAO;
import Comun.SkillStat;
import Interfaces.BDebuff.AuraI;
import Interfaces.BDebuff.BDebuffI;
import Interfaces.BDebuff.TipoBDebuffI;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.EntidadesPropiedades.Debuffeable;

public class BDebuff implements BDebuffI
{
    protected String id;
    protected String nombre;
    protected String descripcion;
    protected boolean isDebuff = false;
    protected byte stacksMaximos = 0;
    protected TipoBDebuffI tipoBDebuff;                             //Command Pattern: Codigo que se ejecuta al castear el skill
    protected SkillStat[] skillStats;                               //Stats concretos del skill

    //SET
    @Override public void setID(String id)                          { this.id = id; }
    @Override public void setNombre (String nombre)                 { this.nombre = nombre; }
    @Override public void setDescripcion (String descripcion)       { this.descripcion = descripcion; }
    @Override public void setIsDebuff (boolean b)                   { isDebuff = b; }
    @Override public void setStacksMaximos (byte i)                 { stacksMaximos = i; }
    @Override public void setTipoBDebuff(TipoBDebuffI tipoBDebuff)  { this.tipoBDebuff = tipoBDebuff; }

    //GET:
    @Override public String getID()                                 { return id; }
    @Override public String getNombre ()                            { return nombre; }
    @Override public String getDescripcion ()                       { return descripcion; }
    @Override public boolean isDebuff ()                            { return isDebuff; }
    @Override public int getStacksMaximos ()                        { return stacksMaximos; }
    @Override public TipoBDebuffI getTipoBDebuff()                  { return tipoBDebuff; }
    @Override public SkillStat [] skillStats ()                     { return skillStats; }

    //CONSTRUCTOR:
    public BDebuff (TipoBDebuffI tipoBDebuff)
    {   //Se vincula el objeto que ejecutara los metodos de este tipo de Spell
        this.tipoBDebuff = tipoBDebuff;

        nombre = tipoBDebuff.getNombre();
        descripcion = tipoBDebuff.getDescripcion();
        isDebuff = tipoBDebuff.getIsDebuff();
        stacksMaximos = tipoBDebuff.getStacksMaximos();

        //y se copian sus Stats base:
        skillStats = new SkillStat[tipoBDebuff.skillStat().length];
        for (int i=0; i<skillStats.length;i++)
        {
            SkillStat statSkill = new SkillStat(tipoBDebuff.skillStat()[i]);
            skillStats[i] = statSkill;
        }
    }

    public BDebuff (String tipoBDebuffID)
    {
        this.tipoBDebuff = DAO.tipoBDebuffDAOFactory.getBDebuffDAO().getTipoBDebuff(tipoBDebuffID);

        if (tipoBDebuff == null) { System.out.println("ERROR: BDebuffID no encontrado."); return; }

        skillStats = new SkillStat[tipoBDebuff.skillStat().length];
        for (int i=0; i<skillStats.length;i++)
        {
            SkillStat statSkill = new SkillStat(tipoBDebuff.skillStat()[i]);
            skillStats[i] = statSkill;
        }
    }

    private void aplicarAura(Caster caster, Debuffeable target)
    {
        AuraI aura = new Aura(tipoBDebuff, caster, target);
        aura.setDuracionMax(Float.MAX_VALUE);
        target.añadirAura(aura);
    }

    public void actualizarTick (AuraI aura)
    {   tipoBDebuff.actualizarAura(aura); }
}
