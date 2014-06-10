package DAO.TipoSpell.DB;// Created by Hanto on 17/04/2014.

import DAO.TipoSpell.TipoSpellDAO;
import Interfaces.Spell.TipoSpellI;

import java.util.Map;

public class TipoSpellLocal implements TipoSpellDAO
{
    private Map<String, TipoSpellI> listaDeTipoSpells = TipoSpellLocalDB.get().listaDeTipoSpells;


    @Override public boolean añadirTipoSpell(TipoSpellI tipoSpell)
    {
        if (listaDeTipoSpells.containsKey(tipoSpell.getID()))
        {   System.out.println("ERROR: ya existe un TipoSpell con este ID["+tipoSpell.getID()+"]");  return false; }
        else {  listaDeTipoSpells.put(tipoSpell.getID(), tipoSpell); return true; }
    }

    @Override public void salvarTipoSpell(TipoSpellI tipoSpell)
    {
        if (listaDeTipoSpells.containsKey(tipoSpell.getID()))
        {   listaDeTipoSpells.put(tipoSpell.getID(), tipoSpell); }
    }

    @Override public void eliminarTipoSpell(String tipoSpellID)
    {
        if (listaDeTipoSpells.containsKey(tipoSpellID))
        {   listaDeTipoSpells.remove(tipoSpellID); }
    }

    @Override public TipoSpellI getTipoSpell(String tipoSpellID)
    {   return listaDeTipoSpells.get(tipoSpellID); }
}
