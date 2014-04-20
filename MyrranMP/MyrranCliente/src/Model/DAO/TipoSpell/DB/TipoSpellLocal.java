package Model.DAO.TipoSpell.DB;// Created by Hanto on 17/04/2014.

import Model.DAO.TipoSpell.TipoSpellDAO;
import Model.Skill.Spell.TipoSpell;

import java.util.Map;

public class TipoSpellLocal implements TipoSpellDAO
{
    private Map<Integer, TipoSpell> listaDeTipoSpells = TipoSpellLocalDB.get().listaDeTipoSpells;


    @Override public int añadirTipoSpell(TipoSpell tipoSpell)
    {
        int iDMenor;
        for (iDMenor=0; iDMenor< listaDeTipoSpells.size(); iDMenor++)
        {   if (!listaDeTipoSpells.containsKey(iDMenor)) break; }

        tipoSpell.setID(iDMenor);
        listaDeTipoSpells.put(tipoSpell.getID(), tipoSpell);
        return iDMenor;
    }

    @Override public void salvarTipoSpell(TipoSpell tipoSpell)
    {
        if (listaDeTipoSpells.containsKey(tipoSpell.getID()))
        {   listaDeTipoSpells.put(tipoSpell.getID(), tipoSpell); }
    }

    @Override
    public void eliminarTipoSpell(int tipoSpellID)
    {
        if (listaDeTipoSpells.containsKey(tipoSpellID))
        {   listaDeTipoSpells.remove(tipoSpellID); }
    }

    @Override
    public TipoSpell getTipoSpell(int tipoSpellID)
    {   return listaDeTipoSpells.get(tipoSpellID); }
}
