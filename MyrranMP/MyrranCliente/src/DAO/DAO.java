package DAO;// Created by Ladrim on 18/04/2014.

import DAO.Spell.SpellDAOFactory;
import DAO.Terreno.TerrenoDAOFactory;
import DAO.TipoSpell.TipoSpellDAOFactory;

public class DAO
{
    public static final TerrenoDAOFactory terrenoDAOFactory = TerrenoDAOFactory.LOCAL;
    public static final TipoSpellDAOFactory tipoSpellDAOFactory = TipoSpellDAOFactory.LOCAL;
    public static final SpellDAOFactory spellDAOFactory = SpellDAOFactory.LOCAL;
}
