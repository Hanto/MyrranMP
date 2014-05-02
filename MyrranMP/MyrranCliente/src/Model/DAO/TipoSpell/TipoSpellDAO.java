package Model.DAO.TipoSpell;// Created by Hanto on 17/04/2014.

import Model.Classes.Skill.Spell.TipoSpell;

public interface TipoSpellDAO
{
    public boolean añadirTipoSpell(TipoSpell tipoSpell);
    public void salvarTipoSpell(TipoSpell tipoSpell);
    public void eliminarTipoSpell(String tipoSpellID);
    public TipoSpell getTipoSpell(String tipoSpellID);
}
