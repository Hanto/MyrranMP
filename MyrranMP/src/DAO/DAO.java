package DAO;// Created by Hanto on 10/06/2014.

import DAO.Spell.SpellDAOFactory;
import DAO.Terreno.TerrenoDAOFactory;
import DAO.TipoBDebuff.TipoBDebuffDAOFactory;
import DAO.TipoSpell.TipoSpellDAOFactory;

public class DAO
{
    public static final SpellDAOFactory spellDAOFactory = SpellDAOFactory.LOCAL;
    public static final TipoBDebuffDAOFactory tipoBDebuffDAOFactory = TipoBDebuffDAOFactory.LOCAL;
    public static final TerrenoDAOFactory terrenoDAOFactory = TerrenoDAOFactory.LOCAL;
    public static final TipoSpellDAOFactory tipoSpellDAOFactory = TipoSpellDAOFactory.LOCAL;
}
