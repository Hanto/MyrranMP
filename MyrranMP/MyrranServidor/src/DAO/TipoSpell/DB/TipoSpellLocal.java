package DAO.TipoSpell.DB;// Created by Hanto on 17/04/2014.

import DAO.TipoSpell.TipoSpellDAO;
import Model.Skill.Spell.TipoSpell;

import java.util.Map;

public class TipoSpellLocal implements TipoSpellDAO
{
    private Map<String, TipoSpell> listaDeTipoSpells = TipoSpellLocalDB.get().listaDeTipoSpells;


    @Override public boolean añadirTipoSpell(TipoSpell tipoSpell)
    {
        if (listaDeTipoSpells.containsKey(tipoSpell.getID()))
        {   System.out.println("ERROR: ya existe un TipoSpell con este ID["+tipoSpell.getID()+"]");  return false; }
        else {  listaDeTipoSpells.put(tipoSpell.getID(), tipoSpell); return true; }
    }

    @Override public void salvarTipoSpell(TipoSpell tipoSpell)
    {
        if (listaDeTipoSpells.containsKey(tipoSpell.getID()))
        {   listaDeTipoSpells.put(tipoSpell.getID(), tipoSpell); }
    }

    @Override public void eliminarTipoSpell(String tipoSpellID)
    {
        if (listaDeTipoSpells.containsKey(tipoSpellID))
        {   listaDeTipoSpells.remove(tipoSpellID); }
    }

    @Override public TipoSpell getTipoSpell(String tipoSpellID)
    {   return listaDeTipoSpells.get(tipoSpellID); }
}
