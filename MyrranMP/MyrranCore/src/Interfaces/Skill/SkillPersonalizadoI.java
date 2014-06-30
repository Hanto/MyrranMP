package Interfaces.Skill;// Created by Hanto on 29/06/2014.

import Core.Skills.SkillMod;

import java.util.Iterator;

public interface SkillPersonalizadoI
{
    //SOLO GETTERS, para modificar los stats hacerlo a traves del player, o de la entidad que los posea, para activar sus observadores
    public String getID();
    public String getNombre();
    public int getNumSkillStats();

    public String getNombre(int statID);
    public int getNumTalentos(int statID);
    public float getValorBase(int statID);
    public float getBonoTalento (int statID);
    public float getBonosPorObjetos(int statID);
    public int getCosteTalento(int statID);
    public int getTalentoMaximo(int statID);
    public boolean getIsMejorable(int statID);
    public Iterator<SkillMod> getIterator();

    public float getValorTotal(int statID);
}
