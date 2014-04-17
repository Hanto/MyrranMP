package Model.DAO;// Created by Hanto on 17/04/2014.

import Model.DAO.Spell.SpellDAOFactory;
import Model.DAO.Terreno.TerrenoDAOFactory;
import Model.DAO.TipoSpell.TipoSpellDAOFactory;

public class DAO
{
    //DAOFactory
    public static final TerrenoDAOFactory terrenoDAO = TerrenoDAOFactory.LOCAL;
    public static final TipoSpellDAOFactory tipoSpellDAO = TipoSpellDAOFactory.LOCAL;
    public static final SpellDAOFactory spellDAO = SpellDAOFactory.LOCAL;
}
