package Interfaces.BDebuff;// Created by Hanto on 09/06/2014.

import Core.SkillStat;

public interface TipoBDebuffI
{   //SET:
    public void setID(String id);
    public void setNombre (String nombre);
    public void setDescripcion (String descripcion);
    public void setIsDebuff (boolean b);
    public void setStacksMaximos (byte i);

    //GET
    public String getID();
    public String getNombre();
    public String getDescripcion();
    public boolean getIsDebuff();
    public byte getStacksMaximos();
    public SkillStat[] skillStats();

    //METODOS:
    public void inicializarSkillStats();
    public void actualizarAura(AuraI aura);
}
