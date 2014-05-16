package zMain;// Created by Hanto on 11/04/2014.

import Data.GameData;
import DTO.GameDataDTO;
import Model.DAO.DAO;
import Model.DAO.Spell.SpellDAO;
import Model.DAO.Terreno.TerrenoDAO;
import Model.DAO.TipoSpell.TipoSpellDAO;
import Model.Classes.Geo.Terreno;
import Model.Classes.Skill.SkillStat;
import Model.Classes.Skill.Spell.Spell;
import Model.Classes.Skill.Spell.TipoSpell;
import Model.Classes.Skill.Spell.TipoSpellFactory;

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
        TerrenoDAO terrenoDAO = DAO.terrenoDAOFactory.getTerrenoDAO();

        for (GameDataDTO.TerrenoDTO terrenoDTO : GameData.get().listaDeTerrenos)
        {   terrenoDAO.añadirTerreno(new Terreno(terrenoDTO.id, terrenoDTO.nombre, terrenoDTO.isSolido));}
    }

    public static void cargarTipoSpells()
    {
        TipoSpellDAO tipoSpellDAO = DAO.tipoSpellDAOFactory.getTipoSpellDAO();

        for (TipoSpellFactory tipoSpellFactory: TipoSpellFactory.values())
        {
            TipoSpell tipoSpell = tipoSpellFactory.nuevo();
            tipoSpell.setID(tipoSpellFactory.name());
            tipoSpellDAO.añadirTipoSpell(tipoSpell);
        }
    }

    public static void cargarSpells()
    {
        SpellDAO spellDAO = DAO.spellDAOFactory.getSpellDAO();

        Spell spell;
        TipoSpell tipoSpell;

        for (GameDataDTO.SpellDTO spellDTO : GameData.get().listaDeSpells)
        {
            tipoSpell = DAO.tipoSpellDAOFactory.getTipoSpellDAO().getTipoSpell(spellDTO.tipoSpell.name());
            spell = new Spell(tipoSpell);

            if (spell.skillStats().length != spellDTO.skillStats.length)
                System.out.println("ERROR importando spell: ["+spell.getNombre()+"], numero de stats difiere del tipo");

            spell.setID(spellDTO.id);
            spell.setNombre(spellDTO.nombre);
            spell.setDescripcion(spellDTO.descripcion);

            for (int i=0; i<spellDTO.skillStats.length; i++)
            {
                SkillStat statOriginal = spell.skillStats()[i];
                Data.Spell.SkillStat statModificado = spellDTO.skillStats[i];

                if (statModificado.getHayNombre())          statOriginal.nombre = statModificado.getNombre();
                if (statModificado.getHayValorBase())       statOriginal.valorBase = statModificado.getValorBase();
                if (statModificado.getHayBonoTalento())     statOriginal.bonoTalento = statModificado.getBonoTalento();
                if (statModificado.getHayCosteTalento())    statOriginal.costeTalento = statModificado.getCosteTalento();
                if (statModificado.getHayTalentoMaximo())   statOriginal.talentoMaximo = statModificado.getTalentoMaximo();
                if (statModificado.getHayIsMejorable())     statOriginal.setIsMejorable(statModificado.getisMejorable());
            }

            spellDAO.añadirSpell(spell);
        }
    }

}
