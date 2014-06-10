package Interfaces.BDebuff;// Created by Hanto on 09/06/2014.

import Data.Spell.SkillStat;

public interface BDebuffI
{   //SET
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
    public int getStacksMaximos ();
    public TipoBDebuffI getTipoBDebuff();
    public SkillStat[] skillStats ();
}
