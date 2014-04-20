package Model.DAO.Spell.DB;// Created by Hanto on 17/04/2014.

import Model.DAO.Spell.SpellDAO;
import Model.Skill.Spell.Spell;

import java.util.Map;

public class SpellLocal implements SpellDAO
{
    private Map<Integer, Spell> listaDeSpells = SpellLocalDB.get().listaDeSpells;

    @Override public int añadirSpell(Spell spell)
    {
        int iDMenor;
        for (iDMenor=0; iDMenor< listaDeSpells.size(); iDMenor++)
        {   if (!listaDeSpells.containsKey(iDMenor)) break; }

        spell.setID(iDMenor);
        listaDeSpells.put(spell.getID(), spell);
        return iDMenor;
    }

    @Override public void salvarSpell(Spell spell)
    {
        if (listaDeSpells.containsKey(spell.getID()))
        {   listaDeSpells.put(spell.getID(), spell); }
    }

    @Override public void eliminarSpell(int spellID)
    {
        if (listaDeSpells.containsKey(spellID))
        {   listaDeSpells.remove(spellID); }
    }

    @Override
    public Spell getSpell(int spellID)
    {   return listaDeSpells.get(spellID); }
}
