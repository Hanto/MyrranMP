package Model.DAO;// Created by Ladrim on 18/04/2014.

import Model.DAO.Accion.AccionDAOFactory;
import DAO.Spell.SpellDAOFactory;
import Model.DAO.Terreno.TerrenoDAOFactory;
import Model.DAO.TipoSpell.TipoSpellDAOFactory;

public class DAO
{
    public static final TerrenoDAOFactory terrenoDAOFactory = TerrenoDAOFactory.LOCAL;
    public static final TipoSpellDAOFactory tipoSpellDAOFactory = TipoSpellDAOFactory.LOCAL;
    public static final SpellDAOFactory spellDAOFactory = SpellDAOFactory.LOCAL;
    public static final AccionDAOFactory accionDAOFactory = AccionDAOFactory.LOCAL;
}
