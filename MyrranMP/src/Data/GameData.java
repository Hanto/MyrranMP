package Data;

import Data.Spell.SpellsData;

import java.util.ArrayList;
import java.util.List;

public class GameData
{
    private static class Singleton      { private static final GameData get = new GameData(); }
    public static GameData get()        { return Singleton.get; }


    public List<GameDataDTO.TerrenoDTO>listaDeTerrenoDTO = new ArrayList<>();
    public List<GameDataDTO.SpellDTO>listaDeSpellsDTO = new ArrayList<>();

    public GameData()
    {
        //ID, nombre, isSolido, textura:
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO(0, "Arena1",      false, "Arena1"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO(1, "Arena2",      false, "Arena2"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO(2, "Baldosa21",   false, "Baldosas1"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO(3, "Baldosa22",   false, "Baldosas2"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO(4, "Baldosas3",   false, "Baldosas3"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO(5, "Cesped1",     false, "Cesped1"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO(6, "Cesped2",     false, "Cesped2"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO(7, "Cesped3",     false, "Cesped3"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO(8, "Cesped4",     false, "Cesped4"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO(9, "Cesped5",     false, "Cesped5"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO(10,"Suelo",       false, "Suelo"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO(11,"Suelo2",      false, "Suelo2"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO(12,"Tierra1",     false, "Tierra1"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO(13,"Tierra2",     false, "Tierra2"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO(14,"Tierra3",     false, "Tierra3"));

        GameDataDTO.SpellDTO spellDTO = new GameDataDTO.SpellDTO(1);
        spellDTO.tipoSpell = GameDataDTO.TipoSpell.EDITARTERRENO;
        spellDTO.id = SpellsData.TERRAFORMAR_ID;
        spellDTO.nombre = SpellsData.TERRAFORMAR_Nombre;
        spellDTO.descripcion = SpellsData.TERRAFORMAR_Descripcion;
        spellDTO.skillStats[0].setValor(0.03f);
        listaDeSpellsDTO.add(spellDTO);
    }
}
