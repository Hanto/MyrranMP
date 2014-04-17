package zMain;// Created by Hanto on 11/04/2014.

import Data.GameData;
import Data.GameDataDTO;
import Model.DAO.Terreno.TerrenoDAO;
import Model.DAO.Terreno.TerrenoDAOFactory;
import Model.DAO.TipoSpell.TipoSpellDAO;
import Model.DAO.TipoSpell.TipoSpellDAOFactory;
import Model.Geo.Terreno;
import Model.Skill.Spell.TiposSpell.EditarTerreno;

public class LoadGameData
{
    public static void cargarTodo ()
    {
        cargarTerrenos();
        cargarTipoSpells();
        cargarSpells();
    }

    public static void cargarTerrenos()
    {
        TerrenoDAO terrenoDAO = TerrenoDAOFactory.LOCAL.newInstance();

        for (GameDataDTO.TerrenoDTO terrenoDTO : GameData.get().listaDeTerrenoDTO)
        {
            terrenoDAO.añadirTerreno(new Terreno(terrenoDTO.nombre, terrenoDTO.isSolido));
        }
    }

    public static void cargarTipoSpells()
    {
        TipoSpellDAO tipoSpellDAO = TipoSpellDAOFactory.LOCAL.newInstance();
        tipoSpellDAO.añadirTipoSpell(new EditarTerreno());
    }

    public static void cargarSpells()
    {

    }
}
