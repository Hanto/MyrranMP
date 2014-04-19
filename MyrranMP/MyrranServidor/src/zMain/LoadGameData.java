package zMain;// Created by Hanto on 11/04/2014.

import Data.GameData;
import Data.GameDataDTO;
import Model.DAO.DAO;
import Model.DAO.Spell.SpellDAO;
import Model.DAO.Terreno.TerrenoDAO;
import Model.DAO.TipoSpell.TipoSpellDAO;
import Model.Geo.Terreno;
import Model.Skill.Spell.Spell;
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
        TerrenoDAO terrenoDAO = DAO.terrenoDAO.newInstance();
        for (GameDataDTO.TerrenoDTO terrenoDTO : GameData.get().listaDeTerrenoDTO)
        {   terrenoDAO.añadirTerreno(new Terreno(terrenoDTO.nombre, terrenoDTO.isSolido));}
    }

    public static void cargarTipoSpells()
    {
        TipoSpellDAO tipoSpellDAO = DAO.tipoSpellDAO.newInstance();
        tipoSpellDAO.añadirTipoSpell(new EditarTerreno());
    }

    public static void cargarSpells()
    {
        SpellDAO spellDAO = DAO.spellDAO.newInstance();

        Spell spell = new Spell(0);
        spellDAO.añadirSpell(spell);
    }
}
