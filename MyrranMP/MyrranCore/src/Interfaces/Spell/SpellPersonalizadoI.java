package Interfaces.Spell;// Created by Hanto on 30/06/2014.

import Interfaces.Skill.SkillPersonalizadoI;

import java.util.Iterator;

public interface SpellPersonalizadoI
{
    //SOLO GETTERS, para modificar los stats hacerlo a traves del player, o de la entidad que los posea, para activar sus observadores
    public String getID();
    public SkillPersonalizadoI getCustomSpell();
    public SkillPersonalizadoI getSkillPersonalizado(String skillID);
    public SkillPersonalizadoI getDebuffPersonalizado(String debuffID);
    public Iterator<SkillPersonalizadoI> getIteratorCustomDebuffs();

    public int getCosteTotalTalentos();
    public int getNumDebuffsQueAplica();

}
