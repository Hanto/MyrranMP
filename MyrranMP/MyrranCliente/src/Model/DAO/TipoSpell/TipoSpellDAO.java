package Model.DAO.TipoSpell;// Created by Hanto on 17/04/2014.

import Model.Skill.Spell.TipoSpell;

public interface TipoSpellDAO
{
    public int añadirTipoSpell(TipoSpell tipoSpell);
    public void salvarTipoSpell(TipoSpell tipoSpell);
    public void eliminarTipoSpell(int tipoSpellID);
    public TipoSpell getTipoSpell(int tipoSpellID);
}
