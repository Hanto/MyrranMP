package zMain;// Created by Hanto on 11/04/2014.

import Data.GameData;
import Data.GameDataDTO;
import Model.DAO.DAO;
import Model.DAO.Spell.SpellDAO;
import Model.DAO.Terreno.TerrenoDAO;
import Model.DAO.TipoSpell.TipoSpellDAO;
import Model.Geo.Terreno;
import Model.Skill.SkillStat;
import Model.Skill.Spell.Spell;
import Model.Skill.Spell.TipoSpell;
import Model.Skill.Spell.TipoSpellFactory;
import View.Graficos.Atlas;
import View.Mobiles.MobilesRecursos;
import View.Recursos.RSC;
import View.Recursos.TerrenoRSC.TerrenoRSC;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class LoadGameData
{
    private TextureAtlas atlas = Atlas.get().atlas;

    public void cargarTodo ()
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

    public void cargarTerrenos()
    {
        TerrenoDAO terrenoDAO = DAO.terrenoDAOFactory.getTerrenoDAO();
        TerrenoRSC terrenoRSC = RSC.terrenoRecursoDAO.getTerrenoRecursoDAO();

        for (GameDataDTO.TerrenoDTO terrenoDTO : GameData.get().listaDeTerrenoDTO)
        {
            terrenoDAO.añadirTerreno(new Terreno(terrenoDTO.id, terrenoDTO.nombre, terrenoDTO.isSolido));
            terrenoRSC.salvarTextura(terrenoDTO.id, terrenoDTO.nombreTextura, atlas);
        }
    }

    public void cargarTipoSpells()
    {
        TipoSpellDAO tipoSpellDAO = DAO.tipoSpellDAOFactory.getTipoSpellDAO();

        for (TipoSpellFactory tipoSpell: TipoSpellFactory.values())
        {   tipoSpellDAO.añadirTipoSpell(tipoSpell.nuevo()); }
    }

    public void cargarSpells()
    {
        SpellDAO spellDAO = DAO.spellDAOFactory.getSpellDAO();

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
