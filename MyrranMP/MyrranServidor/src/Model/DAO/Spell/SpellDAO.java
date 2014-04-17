package Model.DAO.Spell;// Created by Hanto on 17/04/2014.

import Model.Skill.Spell.Spell;

public interface SpellDAO
{
    public int añadirSpell(Spell spell);
    public void salvarSpell(Spell spell);
    public void eliminarSpell (int spellID);
    public Spell getSpell(int spellID);
}
