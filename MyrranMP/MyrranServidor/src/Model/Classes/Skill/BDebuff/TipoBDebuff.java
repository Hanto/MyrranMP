package Model.Classes.Skill.BDebuff;// Created by Hanto on 04/06/2014.

import Core.SkillStat;
import Interfaces.Model.AbstractModel;
import Interfaces.BDebuff.TipoBDebuffI;

public abstract class TipoBDebuff extends AbstractModel implements TipoBDebuffI
{
    public static final int STAT_Duracion = 0;

    protected String id;
    protected String nombre;
    protected String descripcion;
    protected boolean isDebuff = false;
    protected byte stacksMaximos = 0;
    protected SkillStat[] skillStats;

    //SET
    @Override public void setID(String id)                      { this.id = id; }
    @Override public void setNombre (String nombre)             { this.nombre = nombre; }
    @Override public void setDescripcion (String descripcion)   { this.descripcion = descripcion; }
    @Override public void setIsDebuff (boolean b)               { isDebuff = b; }
    @Override public void setStacksMaximos (byte i)             { stacksMaximos = i; }

    //GET
    @Override public String getID()                             { return id; }
    @Override public String getNombre()                         { return nombre; }
    @Override public String getDescripcion ()                   { return descripcion; }
    @Override public boolean getIsDebuff ()                     { return isDebuff; }
    @Override public byte getStacksMaximos ()                   { return stacksMaximos; }
    @Override public SkillStat[] skillStat ()                   { return skillStats; }


    //CONSTRUCTOR:
    public TipoBDebuff()
    {
        inicializarSkillStats();
    }
}
