package Interfaces.BDebuff;// Created by Hanto on 09/06/2014.


import Core.SkillStat;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.EntidadesPropiedades.Debuffeable;

import java.util.Iterator;

public interface BDebuffI
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
    public SkillStat getSkillStat(int numSkillStat);
    public Iterator<SkillStat> getSkillStats();

    //METODOS:
    public void aplicarDebuff(Caster caster, Debuffeable target);
    public void actualizarTick (AuraI aura);

}
