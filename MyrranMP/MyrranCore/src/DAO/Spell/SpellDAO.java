package DAO.Spell;// Created by Hanto on 17/04/2014.

import Interfaces.Spell.SpellI;

public interface SpellDAO
{
    public boolean añadirSpell(SpellI spell);
    public void salvarSpell(SpellI spell);
    public void eliminarSpell (String spellID);
    public SpellI getSpell(String spellID);
}
