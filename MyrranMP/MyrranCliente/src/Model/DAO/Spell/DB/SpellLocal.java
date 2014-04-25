package Model.DAO.Spell.DB;// Created by Hanto on 17/04/2014.

import Model.DAO.Spell.SpellDAO;
import Model.Skill.Spell.Spell;

import java.util.Map;

public class SpellLocal implements SpellDAO
{
    private Map<String, Spell> listaDeSpells = SpellLocalDB.get().listaDeSpells;

    @Override public boolean añadirSpell(Spell spell)
    {
        if (listaDeSpells.containsKey(spell.getID()))
        {   System.out.println("ERROR: ya existe un Spell con este ID["+spell.getID()+"]");  return false; }
        else {  listaDeSpells.put(spell.getID(), spell); return true; }
    }

    @Override public void salvarSpell(Spell spell)
    {
        if (listaDeSpells.containsKey(spell.getID()))
        {   listaDeSpells.put(spell.getID(), spell); }
    }

    @Override public void eliminarSpell(String spellID)
    {
        if (listaDeSpells.containsKey(spellID))
        {   listaDeSpells.remove(spellID); }
    }

    @Override public Spell getSpell(String spellID)
    {   return listaDeSpells.get(spellID); }
}
