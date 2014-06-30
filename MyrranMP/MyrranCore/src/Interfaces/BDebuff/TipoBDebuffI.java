package Interfaces.BDebuff;// Created by Hanto on 09/06/2014.

import Core.Skills.SkillStat;

import java.util.Iterator;

public interface TipoBDebuffI
{   //SET:
    public void setID(String id);
    public void setNombre (String nombre);
    public void setDescripcion (String descripcion);
    public void setIsDebuff (boolean b);
    public void setStacksMaximos (byte i);
    public void setSkillStat(SkillStat skillStat, int statID);
    public void setNumSkillStats(int numSkillStats);

    //GET
    public String getID();
    public String getNombre();
    public String getDescripcion();
    public boolean getIsDebuff();
    public byte getStacksMaximos();
    public SkillStat getSkillStat(int statID);
    public Iterator<SkillStat> getSkillStats();
    public int getNumSkillStats();

    //METODOS:
    public void inicializarSkillStats();
    public void actualizarTick(AuraI aura);
}
