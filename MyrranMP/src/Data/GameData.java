package Data;

import Data.Spell.SpellsData;
import Data.Spell.TipoSpellsData;

import java.util.ArrayList;
import java.util.List;

public class GameData
{
    private static class Singleton      { private static final GameData get = new GameData(); }
    public static GameData get()        { return Singleton.get; }

    public List<GameDataDTO.TerrenoRSCDTO> listaTerrenoRSC = new ArrayList<>();
    public List<GameDataDTO.TerrenoDTO> listaDeTerrenos = new ArrayList<>();

    public List<GameDataDTO.SpellRSCIconosDTO> listaDeSpellRSCIconos = new ArrayList<>();
    public List<GameDataDTO.SpellRSCCasteoDTO> listaDeSpellRSCCasteos = new ArrayList<>();
    public List<GameDataDTO.SpellRSCProyectilDTO> listaDeSpellRSCProyectiles = new ArrayList<>();
    public List<GameDataDTO.SpellDTO> listaDeSpells = new ArrayList<>();

    public List<GameDataDTO.PixiePCRazaDTO> listaDePCRazas = new ArrayList<>();
    public List<GameDataDTO.PixiePCCuerpoDTO> listaDePCCuerpos = new ArrayList<>();
    public List<GameDataDTO.PixiePCYelmoDTO> listaDePCYelmos = new ArrayList<>();
    public List<GameDataDTO.PixiePCCabezaDTO> listaDePCCabezas = new ArrayList<>();
    public List<GameDataDTO.PixiePCBotasDTO> listaDePCBotas = new ArrayList<>();
    public List<GameDataDTO.PixiePCGuantesDTO> listaDePCGuantes = new ArrayList<>();
    public List<GameDataDTO.PixiePCHombrerasDTO> listaDePCHombreras = new ArrayList<>();
    public List<GameDataDTO.PixiePCPantalonesDTO> listaDePCPantalones = new ArrayList<>();
    public List<GameDataDTO.PixiePCPetoDTO> listaDePCPetos = new ArrayList<>();
    public List<GameDataDTO.PixiePCCapasFrontalesDTO> listaDePCCapasFrontales = new ArrayList<>();
    public List<GameDataDTO.PixiePCCapasTraserasDTO> listaDePCCapasTraseras = new ArrayList<>();

    public GameData()
    {
        listaDePCRazas.add(new GameDataDTO.PixiePCRazaDTO                       ("Golem"));
        listaDePCCuerpos.add(new GameDataDTO.PixiePCCuerpoDTO                   ("Golem", "Golem"));
        listaDePCYelmos.add(new GameDataDTO.PixiePCYelmoDTO                     ("Golem", "Desnudo"));
        listaDePCCabezas.add(new GameDataDTO.PixiePCCabezaDTO                   ("Golem", "Desnudo"));
        listaDePCBotas.add(new GameDataDTO.PixiePCBotasDTO                      ("Golem", "Desnudo"));
        listaDePCGuantes.add(new GameDataDTO.PixiePCGuantesDTO                  ("Golem", "Desnudo"));
        listaDePCHombreras.add(new GameDataDTO.PixiePCHombrerasDTO              ("Golem", "Desnudo"));
        listaDePCPantalones.add(new GameDataDTO.PixiePCPantalonesDTO            ("Golem", "Desnudo"));
        listaDePCPetos.add(new GameDataDTO.PixiePCPetoDTO                       ("Golem", "Desnudo"));
        listaDePCCapasFrontales.add(new GameDataDTO.PixiePCCapasFrontalesDTO    ("Golem", "Desnudo"));
        listaDePCCapasTraseras.add(new GameDataDTO.PixiePCCapasTraserasDTO      ("Golem", "Desnudo"));


        listaTerrenoRSC.add(new GameDataDTO.TerrenoRSCDTO   ("Arena1"));
        listaTerrenoRSC.add(new GameDataDTO.TerrenoRSCDTO   ("Arena2"));
        listaTerrenoRSC.add(new GameDataDTO.TerrenoRSCDTO   ("Baldosas1"));
        listaTerrenoRSC.add(new GameDataDTO.TerrenoRSCDTO   ("Baldosas2"));
        listaTerrenoRSC.add(new GameDataDTO.TerrenoRSCDTO   ("Baldosas3"));
        listaTerrenoRSC.add(new GameDataDTO.TerrenoRSCDTO   ("Cesped1"));
        listaTerrenoRSC.add(new GameDataDTO.TerrenoRSCDTO   ("Cesped2"));
        listaTerrenoRSC.add(new GameDataDTO.TerrenoRSCDTO   ("Cesped3"));
        listaTerrenoRSC.add(new GameDataDTO.TerrenoRSCDTO   ("Cesped4"));
        listaTerrenoRSC.add(new GameDataDTO.TerrenoRSCDTO   ("Cesped5"));
        listaTerrenoRSC.add(new GameDataDTO.TerrenoRSCDTO   ("Suelo"));
        listaTerrenoRSC.add(new GameDataDTO.TerrenoRSCDTO   ("Suelo2"));
        listaTerrenoRSC.add(new GameDataDTO.TerrenoRSCDTO   ("Tierra1"));
        listaTerrenoRSC.add(new GameDataDTO.TerrenoRSCDTO   ("Tierra2"));
        listaTerrenoRSC.add(new GameDataDTO.TerrenoRSCDTO   ("Tierra3"));


        //ID, nombre, isSolido, textura:
        listaDeTerrenos.add(new GameDataDTO.TerrenoDTO  (0, "Arena1",       false, "Arena1"));
        listaDeTerrenos.add(new GameDataDTO.TerrenoDTO  (1, "Arena2",       false, "Arena2"));
        listaDeTerrenos.add(new GameDataDTO.TerrenoDTO  (2, "Baldosas1",    false, "Baldosas1"));
        listaDeTerrenos.add(new GameDataDTO.TerrenoDTO  (3, "Baldosas2",    false, "Baldosas2"));
        listaDeTerrenos.add(new GameDataDTO.TerrenoDTO  (4, "Baldosas3",    false, "Baldosas3"));
        listaDeTerrenos.add(new GameDataDTO.TerrenoDTO  (5, "Cesped1",      false, "Cesped1"));
        listaDeTerrenos.add(new GameDataDTO.TerrenoDTO  (6, "Cesped2",      false, "Cesped2"));
        listaDeTerrenos.add(new GameDataDTO.TerrenoDTO  (7, "Cesped3",      false, "Cesped3"));
        listaDeTerrenos.add(new GameDataDTO.TerrenoDTO  (8, "Cesped4",      false, "Cesped4"));
        listaDeTerrenos.add(new GameDataDTO.TerrenoDTO  (9, "Cesped5",      false, "Cesped5"));
        listaDeTerrenos.add(new GameDataDTO.TerrenoDTO  (10, "Suelo",       false, "Suelo"));
        listaDeTerrenos.add(new GameDataDTO.TerrenoDTO  (11, "Suelo2",      false, "Suelo2"));
        listaDeTerrenos.add(new GameDataDTO.TerrenoDTO  (12, "Tierra1",     false, "Tierra1"));
        listaDeTerrenos.add(new GameDataDTO.TerrenoDTO  (13, "Tierra2",     false, "Tierra2"));
        listaDeTerrenos.add(new GameDataDTO.TerrenoDTO  (14, "Tierra3",     false, "Tierra3"));

        //ICONOS:
        listaDeSpellRSCIconos.add(new GameDataDTO.SpellRSCIconosDTO(TipoSpellsData.ICONO_Editar));
        listaDeSpellRSCIconos.add(new GameDataDTO.SpellRSCIconosDTO(TipoSpellsData.ICONO_Fireball));
        listaDeSpellRSCIconos.add(new GameDataDTO.SpellRSCIconosDTO(TipoSpellsData.ICONO_Frostbolt));
        listaDeSpellRSCIconos.add(new GameDataDTO.SpellRSCIconosDTO(TipoSpellsData.ICONO_Muro));

        //PIXIES CASTEO:
        listaDeSpellRSCCasteos.add(new GameDataDTO.SpellRSCCasteoDTO(TipoSpellsData.CASTEO_Fireball));
        listaDeSpellRSCCasteos.add(new GameDataDTO.SpellRSCCasteoDTO(TipoSpellsData.CASTEO_FrostBolt));

        //PIXIES PROYECTIL:
        listaDeSpellRSCProyectiles.add(new GameDataDTO.SpellRSCProyectilDTO(TipoSpellsData.PROYECTIL_Fireball));
        listaDeSpellRSCProyectiles.add(new GameDataDTO.SpellRSCProyectilDTO(TipoSpellsData.PROYECTIL_Frostbolt));

        //SPELL: Editar Terreno:
        GameDataDTO.SpellDTO spellDTO = new GameDataDTO.SpellDTO(1,0);
        spellDTO.tipoSpell = GameDataDTO.TipoSpell.EDITARTERRENO;
        spellDTO.id = SpellsData.TERRAFORMAR_ID;
        spellDTO.nombre = SpellsData.TERRAFORMAR_Nombre;
        spellDTO.descripcion = SpellsData.TERRAFORMAR_Descripcion;
        spellDTO.skillStats[0].setValor(SpellsData.TERRAFORMAR_CastingTime);
        spellDTO.iDIcono = SpellsData.TERRAFORMAR_Icono;
        listaDeSpells.add(spellDTO);
    }
}
