package DB;// Created by Hanto on 11/06/2014.

import DB.Datos.Spell.SpellDAOFactory;
import DB.Datos.Terreno.TerrenoDAOFactory;
import DB.Datos.TipoBDebuff.TipoBDebuffDAOFactory;
import DB.Datos.TipoSpell.TipoSpellDAOFactory;

public class DAO
{
    public static final TerrenoDAOFactory terrenoDAOFactory = TerrenoDAOFactory.LOCAL;
    public static final TipoSpellDAOFactory tipoSpellDAOFactory = TipoSpellDAOFactory.LOCAL;
    public static final SpellDAOFactory spellDAOFactory = SpellDAOFactory.LOCAL;
    public static final TipoBDebuffDAOFactory tipoBDebuffDAOFactory = TipoBDebuffDAOFactory.LOCAL;
}
