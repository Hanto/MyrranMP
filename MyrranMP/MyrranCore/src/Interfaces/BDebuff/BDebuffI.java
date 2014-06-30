package Interfaces.BDebuff;// Created by Hanto on 09/06/2014.


import Core.Skills.SkillStat;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.EntidadesPropiedades.Debuffeable;
import Interfaces.Skill.SkillI;

import java.util.Iterator;

public interface BDebuffI extends SkillI
{
//SET
    public void setID(String id);
    public void setNombre (String nombre);
    public void setDescripcion (String descripcion);
    public void setIsDebuff (boolean b);
    public void setStacksMaximos (byte i);
    public void setTipoBDebuff(TipoBDebuffI tipoBDebuff);

    //GET:
    public String getID();
    public String getNombre ();
    public String getDescripcion ();
    public boolean isDebuff ();
    public byte getStacksMaximos ();
    public TipoBDebuffI getTipoBDebuff();
    public float getValorTotal(Caster caster, int statID);
    public SkillStat getSkillStat(int statID);
    public Iterator<SkillStat> getSkillStats();
    public int getNumSkillStats();

    //METODOS:
    public void aplicarDebuff(Caster caster, Debuffeable target);
    public void actualizarTick (AuraI aura);

}
