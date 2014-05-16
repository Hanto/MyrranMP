package zMain;// Created by Hanto on 11/04/2014.

import Data.GameData;
import DTO.GameDataDTO;
import Data.MiscData;
import Model.Classes.Acciones.Accion;
import Model.Classes.Acciones.TiposAccion.*;
import Model.Classes.Geo.Terreno;
import Model.Classes.Skill.SkillStat;
import Model.Classes.Skill.Spell.Spell;
import Model.Classes.Skill.Spell.TipoSpell;
import Model.Classes.Skill.Spell.TipoSpellFactory;
import Model.DAO.Accion.AccionDAO;
import Model.DAO.DAO;
import Model.DAO.Spell.SpellDAO;
import Model.DAO.Terreno.TerrenoDAO;
import Model.DAO.TipoSpell.TipoSpellDAO;
import Recursos.Classes.AccionRecursos;
import Recursos.Classes.AtlasRecursos;
import Recursos.Classes.SpellRecursos;
import Recursos.Classes.TerrenoRecursos;
import Recursos.DAO.AccionRecursos.AccionRecursosDAO;
import Recursos.DAO.FuentesRecursos.FuentesRecursosDAO;
import Recursos.DAO.MiscRecursos.MiscRecursosDAO;
import Recursos.DAO.PixiePCRecursos.PixiePCRecursosDAO;
import Recursos.DAO.RSC;
import Recursos.DAO.SkillRecursos.SkillRecursosDAO;
import Recursos.DAO.TerrenoRecursos.TerrenoRecursosDAO;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class LoadGameData
{
    private TextureAtlas atlas = AtlasRecursos.get().atlas;

    public void cargarTodo ()
    {
        cargarRazasPC();
        cargarCuerposPC();
        cargarCabezasPC();
        cargarYelmosPC();
        cargarBotasPC();
        cargarGuantesPC();
        cargarHombrerasPC();
        cargarPantalonesPC();
        cargarPetosPC();
        cargarCapasFrontalesPC();
        cargarCapasTraserasPC();

        cargarTexturasTerrenos();
        cargarTerrenos();

        cargarSpellIconos();
        cargarSpellCasteos();
        cargarSpellProyectiles();

        cargarTipoSpells();
        cargarSpells();

        cargarFuentes();
        cargarMiscRecursos();

        cargarTexturasAcciones();
        cargarAcciones();
    }

    public void cargarRazasPC()
    {
        PixiePCRecursosDAO pixiePCDAO = RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO();
        for (GameDataDTO.PixiePCRazaDTO razaDTO: GameData.get().listaDePCRazas)
        {   pixiePCDAO.salvarRazaPC(razaDTO.iDRaza);}
    }

    public void cargarCuerposPC()
    {
        PixiePCRecursosDAO pixiePCDAO = RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO();
        for (GameDataDTO.PixiePCCuerpoDTO cuerpoDTO: GameData.get().listaDePCCuerpos)
        {   pixiePCDAO.salvarCuerpoPC(cuerpoDTO.iDRaza, cuerpoDTO.nombreTextura, atlas); }
    }

    public void cargarCabezasPC()
    {
        PixiePCRecursosDAO pixiePCDAO = RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO();
        for (GameDataDTO.PixiePCCabezaDTO cabezaDTO: GameData.get().listaDePCCabezas)
        {   pixiePCDAO.salvarCabezaPC(cabezaDTO.iDRaza, cabezaDTO.nombreTextura, atlas); }
    }

    public void cargarYelmosPC()
    {
        PixiePCRecursosDAO pixiePCDAO = RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO();
        for (GameDataDTO.PixiePCYelmoDTO yelmoDTO: GameData.get().listaDePCYelmos)
        {   pixiePCDAO.salvarYelmoPC(yelmoDTO.iDRaza, yelmoDTO.nombreTextura, atlas); }
    }

    public void cargarBotasPC()
    {
        PixiePCRecursosDAO pixiePCDAO = RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO();
        for (GameDataDTO.PixiePCBotasDTO botasDTO: GameData.get().listaDePCBotas)
        {   pixiePCDAO.salvarBotasPC(botasDTO.iDRaza, botasDTO.nombreTextura, atlas); }
    }

    public void cargarGuantesPC()
    {
        PixiePCRecursosDAO pixiePCDAO = RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO();
        for (GameDataDTO.PixiePCGuantesDTO guantesPC: GameData.get().listaDePCGuantes)
        {   pixiePCDAO.salvarGuantesPC(guantesPC.iDRaza, guantesPC.nombreTextura, atlas); }
    }

    public void cargarHombrerasPC()
    {
        PixiePCRecursosDAO pixiePCDAO = RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO();
        for (GameDataDTO.PixiePCHombrerasDTO hombrerasPC: GameData.get().listaDePCHombreras)
        {   pixiePCDAO.salvarHombrerasPC(hombrerasPC.iDRaza, hombrerasPC.nombreTextura, atlas); }
    }

    public void cargarPantalonesPC()
    {
        PixiePCRecursosDAO pixiePCDAO = RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO();
        for (GameDataDTO.PixiePCPantalonesDTO pantalonesPC: GameData.get().listaDePCPantalones)
        {   pixiePCDAO.salvarPantalonesPC(pantalonesPC.iDRaza, pantalonesPC.nombreTextura, atlas);}
    }

    public void cargarPetosPC()
    {
        PixiePCRecursosDAO pixiePCDAO = RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO();
        for (GameDataDTO.PixiePCPetoDTO petosPC: GameData.get().listaDePCPetos)
        {   pixiePCDAO.salvarPetoPC(petosPC.iDRaza, petosPC.nombreTextura, atlas); }
    }

    public void cargarCapasFrontalesPC()
    {
        PixiePCRecursosDAO pixiePCDAO = RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO();
        for (GameDataDTO.PixiePCCapasFrontalesDTO capasFrontalesPC: GameData.get().listaDePCCapasFrontales)
        {   pixiePCDAO.salvarCapasFrontalesPC(capasFrontalesPC.iDRaza, capasFrontalesPC.nombreTextura, atlas); }
    }

    public void cargarCapasTraserasPC()
    {
        PixiePCRecursosDAO pixiePCDAO = RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO();
        for (GameDataDTO.PixiePCCapasTraserasDTO capaTraseraPC: GameData.get().listaDePCCapasTraseras)
        {   pixiePCDAO.salvarCapasTraserasPC(capaTraseraPC.iDRaza, capaTraseraPC.nombreTextura, atlas); }
    }




    public void cargarTexturasTerrenos()
    {
        TerrenoRecursosDAO terrenoRecursosDAO = RSC.terrenoRecursosDAO.getTerrenoRecursosDAO();
        for (GameDataDTO.TerrenoRSCDTO terrenoRSC : GameData.get().listaTerrenoRSC)
        {   terrenoRecursosDAO.salvarTextura(terrenoRSC.iDTerreno, terrenoRSC.nombreTextura, atlas); }
    }

    public void cargarTerrenos()
    {
        TerrenoDAO terrenoDAO = DAO.terrenoDAOFactory.getTerrenoDAO();
        TerrenoRecursosDAO terrenoRecursosDAO = RSC.terrenoRecursosDAO.getTerrenoRecursosDAO();

        for (GameDataDTO.TerrenoDTO terrenoDTO : GameData.get().listaDeTerrenos)
        {
            terrenoDAO.añadirTerreno(new Terreno(terrenoDTO.id, terrenoDTO.nombre, terrenoDTO.isSolido));
            terrenoRecursosDAO.salvarTerrenoRecurso(new TerrenoRecursos(terrenoDTO.id, terrenoRecursosDAO.getTextura(terrenoDTO.nombreTextura)));
        }
    }




    public void cargarSpellIconos ()
    {
        SkillRecursosDAO skillRecursosDAO = RSC.skillRecursosDAO.getSpellRecursosDAO();

        for (GameDataDTO.SpellRSCIconosDTO spellIconoDTO: GameData.get().listaDeSpellRSCIconos)
        {   skillRecursosDAO.salvarIcono(spellIconoDTO.iDIcono, spellIconoDTO.nombreTextura, atlas);}
    }

    public void cargarSpellCasteos()
    {
        SkillRecursosDAO skillRecursosDAO = RSC.skillRecursosDAO.getSpellRecursosDAO();

        for (GameDataDTO.SpellRSCCasteoDTO spellCasteoDTO: GameData.get().listaDeSpellRSCCasteos)
        {   skillRecursosDAO.salvarAnimacionCasteo(spellCasteoDTO.iDCasteo, spellCasteoDTO.nombreTextura, atlas);}
    }

    public void cargarSpellProyectiles()
    {
        SkillRecursosDAO skillRecursosDAO = RSC.skillRecursosDAO.getSpellRecursosDAO();

        for (GameDataDTO.SpellRSCProyectilDTO spellRSCProyectilDTO : GameData.get().listaDeSpellRSCProyectiles)
        {   skillRecursosDAO.salvarAnimacionProyectil(spellRSCProyectilDTO.iDCasteo, spellRSCProyectilDTO.nombreTextura, atlas); }
    }

    public void cargarTipoSpells()
    {
        TipoSpellDAO tipoSpellDAO = DAO.tipoSpellDAOFactory.getTipoSpellDAO();

        for (TipoSpellFactory tipoSpellFactory: TipoSpellFactory.values())
        {
            TipoSpell tipoSpell = tipoSpellFactory.nuevo();
            tipoSpell.setID(tipoSpellFactory.name());
            tipoSpellDAO.añadirTipoSpell(tipoSpell);
        }
    }

    public void cargarSpells()
    {
        SpellDAO spellDAO = DAO.spellDAOFactory.getSpellDAO();
        SkillRecursosDAO skillRecursosDAO = RSC.skillRecursosDAO.getSpellRecursosDAO();

        Spell spell;
        SpellRecursos spellRecursos;
        TipoSpell tipoSpell;

        for (GameDataDTO.SpellDTO spellDTO : GameData.get().listaDeSpells)
        {
            tipoSpell = DAO.tipoSpellDAOFactory.getTipoSpellDAO().getTipoSpell(spellDTO.tipoSpell.name());
            spell = new Spell(tipoSpell);

            if (spell.skillStats().length != spellDTO.skillStats.length)
                System.out.println("ERROR importando spell: [" + spell.getNombre() + "], numero de stats difiere del tipo");

            spell.setID(spellDTO.id);
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

            spellRecursos = new SpellRecursos(spellDTO.id, spellDTO.listaIDAnimaciones.length);
            spellRecursos.setIcono(skillRecursosDAO.getIcono(spellDTO.iDIcono));

            for (int i = 0; i < spellDTO.listaIDAnimaciones.length; i++)
            {   spellRecursos.setTipoAnimacion(i, skillRecursosDAO.getAnimacion(spellDTO.listaIDAnimaciones[i])); }

            skillRecursosDAO.salvarSpellRecursos(spellRecursos);
        }
    }




    public void cargarFuentes()
    {
        FuentesRecursosDAO fuentesDAO = RSC.fuenteRecursosDAO.getFuentesRecursosDAO();
        fuentesDAO.salvarFuente(MiscData.FUENTE_Nombres, "14.fnt", atlas);
    }

    public void cargarMiscRecursos()
    {
        MiscRecursosDAO miscRecursosDAO = RSC.miscRecusosDAO.getMiscRecursosDAO();
        miscRecursosDAO.salvarTextura(MiscData.BARRASPELLS_Textura_Casillero, "UI/Casillero", atlas);
        miscRecursosDAO.salvarTextura(MiscData.BARRASPELLS_RebindButtonON, "UI/RebindOn", atlas);
        miscRecursosDAO.salvarTextura(MiscData.BARRASPELLS_RebindButtonOFF, "UI/RebindOff", atlas);
    }




    public void cargarTexturasAcciones()
    {
        AccionRecursosDAO accionRecursosDAO = RSC.accionRecursosDAO.getAccionRecursosDAO();
        accionRecursosDAO.salvarTextura("IrNorte",  "IrNorte",  atlas);
        accionRecursosDAO.salvarTextura("IrSur",    "IrSur",    atlas);
        accionRecursosDAO.salvarTextura("IrEste",   "IrEste",   atlas);
        accionRecursosDAO.salvarTextura("IrOeste",  "IrOeste",  atlas);
    }


    public void cargarAcciones()
    {
        cargarAccion(new IrNorte());
        cargarAccion(new IrSur());
        cargarAccion(new IrOeste());
        cargarAccion(new IrEste());

        AccionDAO accionDAO = DAO.accionDAOFactory.getAccionDAO();
        AccionRecursosDAO accionRecursosDAO = RSC.accionRecursosDAO.getAccionRecursosDAO();

        AccionRecursos accionRecurso;
        Accion accion;

        for (GameDataDTO.SpellDTO spellDTO : GameData.get().listaDeSpells)
        {
            accion = new SeleccionarSpell(DAO.spellDAOFactory.getSpellDAO().getSpell(spellDTO.id));
            accionDAO.salvarAccion(accion);

            accionRecurso = new AccionRecursos(accion.getID(), RSC.skillRecursosDAO.getSpellRecursosDAO().getSpellRecursos(spellDTO.id).getIcono());
            accionRecursosDAO.salvarAccionRecurso(accionRecurso);
        }
    }

    public void cargarAccion(Accion accion)
    {
        AccionDAO accionDAO = DAO.accionDAOFactory.getAccionDAO();
        AccionRecursosDAO accionRecursosDAO = RSC.accionRecursosDAO.getAccionRecursosDAO();

        accionDAO.salvarAccion(accion);
        accionRecursosDAO.salvarAccionRecurso(new AccionRecursos(accion.getID(), accionRecursosDAO.getTextura(accion.getID())));
    }
}
