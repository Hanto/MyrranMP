package DAO.Spell;// Created by Hanto on 17/04/2014.

import Model.Skill.Spell.Spell;

public interface SpellDAO
{
    public boolean añadirSpell(Spell spell);
    public void salvarSpell(Spell spell);
    public void eliminarSpell (String spellID);
    public Spell getSpell(String spellID);
}
