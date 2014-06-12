package Datos;// Created by Hanto on 11/06/2014.

import Datos.Spell.SpellDAOFactory;
import Datos.Terreno.TerrenoDAOFactory;
import Datos.TipoSpell.TipoSpellDAOFactory;

public class DAO
{
    public static final TerrenoDAOFactory terrenoDAOFactory = TerrenoDAOFactory.LOCAL;
    public static final TipoSpellDAOFactory tipoSpellDAOFactory = TipoSpellDAOFactory.LOCAL;
    public static final SpellDAOFactory spellDAOFactory = SpellDAOFactory.LOCAL;
}
