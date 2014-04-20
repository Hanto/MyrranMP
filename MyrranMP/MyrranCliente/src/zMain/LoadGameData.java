package zMain;// Created by Hanto on 11/04/2014.

import Data.GameData;
import Data.GameDataDTO;
import Model.DAO.DAO;
import Model.DAO.Spell.SpellDAO;
import Model.DAO.Terreno.TerrenoDAO;
import Model.DAO.Terreno.TerrenoDAOFactory;
import Model.DAO.TipoSpell.TipoSpellDAO;
import Model.Geo.Terreno;
import Model.Skill.SkillStat;
import Model.Skill.Spell.Spell;
import Model.Skill.Spell.TipoSpell;
import Model.Skill.Spell.TipoSpellFactory;
import View.Geo.GeoRecursos;
import View.Mobiles.MobilesRecursos;

public class LoadGameData
{
    public static void cargarTodo ()
    {
        cargarRecursos();
        cargarTerrenos();
        cargarTipoSpells();
        cargarSpells();
    }

    public static void cargarRecursos ()
    {
        MobilesRecursos.get().añadirRazaPC              ("Golem");
        MobilesRecursos.get().salvarCuerpoPC            ("Golem",    "Golem");

        MobilesRecursos.get().salvarYelmoPC             ("Golem",    "Desnudo");
        MobilesRecursos.get().salvarCabezaPC            ("Golem",    "Desnudo");
        MobilesRecursos.get().salvarBotasPC             ("Golem",    "Desnudo");
        MobilesRecursos.get().salvarGuantesPC           ("Golem",    "Desnudo");
        MobilesRecursos.get().salvarHombrerasPC         ("Golem",    "Desnudo");
        MobilesRecursos.get().salvarPantalonesPC        ("Golem",    "Desnudo");
        MobilesRecursos.get().salvarPetoPC              ("Golem",    "Desnudo");
        MobilesRecursos.get().salvarCapasFrontalesPC    ("Golem",    "Desnudo");
        MobilesRecursos.get().salvarCapasTraserasPC     ("Golem",    "Desnudo");
    }

    public static void cargarTerrenos()
    {
        TerrenoDAO terrenoDAO = TerrenoDAOFactory.LOCAL.nuevo();
        int iDTerreno;
        for (GameDataDTO.TerrenoDTO terrenoDTO : GameData.get().listaDeTerrenoDTO)
        {
            iDTerreno = terrenoDAO.añadirTerreno(new Terreno(terrenoDTO.nombre, terrenoDTO.isSolido));
            GeoRecursos.get().salvarTexturaTerreno(iDTerreno, terrenoDTO.nombreTextura);
        }
    }

    public static void cargarTipoSpells()
    {
        TipoSpellDAO tipoSpellDAO = DAO.tipoSpellDAO.nuevo();

        for (TipoSpellFactory tipoSpell: TipoSpellFactory.values())
        {   tipoSpellDAO.añadirTipoSpell(tipoSpell.nuevo()); }
    }

    public static void cargarSpells()
    {
        SpellDAO spellDAO = DAO.spellDAO.nuevo();

        Spell spell;
        TipoSpell tipoSpell;

        for (GameDataDTO.SpellDTO spellDTO : GameData.get().listaDeSpellsDTO)
        {
            tipoSpell = TipoSpellFactory.valueOf(spellDTO.tipoSpell.name()).nuevo();
            spell = new Spell(tipoSpell);

            if (spell.skillStats().length != spellDTO.skillStats.length)
                System.out.println("ERROR importando spell: [" + spell.getNombre() + "], numero de stats difiere del tipo");

            spell.setNombre(spellDTO.nombre);
            spell.setDescripcion(spellDTO.descripcion);

            for (int i = 0; i < spellDTO.skillStats.length; i++)
            {
                SkillStat statOriginal = spell.skillStats()[i];
                Data.Spell.SkillStat statModificado = spellDTO.skillStats[i];

                if (statModificado.getHayNombre()) statOriginal.nombre = statModificado.getNombre();
                if (statModificado.getHayValorBase()) statOriginal.valorBase = statModificado.getValorBase();
                if (statModificado.getHayBonoTalento()) statOriginal.bonoTalento = statModificado.getBonoTalento();
                if (statModificado.getHayCosteTalento()) statOriginal.costeTalento = statModificado.getCosteTalento();
                if (statModificado.getHayTalentoMaximo()) statOriginal.talentoMaximo = statModificado.getTalentoMaximo();
                if (statModificado.getHayIsMejorable()) statOriginal.setIsMejorable(statModificado.getisMejorable());
            }

            spellDAO.añadirSpell(spell);
        }
    }
}
