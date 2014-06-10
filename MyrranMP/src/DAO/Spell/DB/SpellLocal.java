package DAO.Spell.DB;// Created by Hanto on 17/04/2014.

import Interfaces.Spell.SpellI;
import DAO.Spell.SpellDAO;

import java.util.Map;

public class SpellLocal implements SpellDAO
{
    private Map<String, SpellI> listaDeSpells = SpellLocalDB.get().listaDeSpells;

    @Override public boolean añadirSpell(SpellI spell)
    {
        if (listaDeSpells.containsKey(spell.getID()))
        {   System.out.println("ERROR: ya existe un Spell con este ID["+spell.getID()+"]");  return false; }
        else {  listaDeSpells.put(spell.getID(), spell); return true; }
    }

    @Override public void salvarSpell(SpellI spell)
    {
        if (listaDeSpells.containsKey(spell.getID()))
        {   listaDeSpells.put(spell.getID(), spell); }
    }

    @Override public void eliminarSpell(String spellID)
    {
        if (listaDeSpells.containsKey(spellID))
        {   listaDeSpells.remove(spellID); }
    }

    @Override public SpellI getSpell(String spellID)
    {   return listaDeSpells.get(spellID); }
}
