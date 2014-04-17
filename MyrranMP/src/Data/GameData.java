package Data;

import java.util.ArrayList;
import java.util.List;

public class GameData
{
    private static class Singleton      { private static final GameData get = new GameData(); }
    public static GameData get()        { return Singleton.get; }


    public List<GameDataDTO.TerrenoDTO>listaDeTerrenoDTO = new ArrayList<>();

    public GameData()
    {

        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO("Arena1",      false, "Arena1"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO("Arena2",      false, "Arena2"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO("Baldosa21",   false, "Baldosas1"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO("Baldosa22",   false, "Baldosas2"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO("Baldosas3",   false, "Baldosas3"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO("Cesped1",     false, "Cesped1"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO("Cesped2",     false, "Cesped2"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO("Cesped3",     false, "Cesped3"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO("Cesped4",     false, "Cesped4"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO("Cesped5",     false, "Cesped5"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO("Suelo",       false, "Suelo"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO("Suelo2",      false, "Suelo2"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO("Tierra1",     false, "Tierra1"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO("Tierra2",     false, "Tierra2"));
        listaDeTerrenoDTO.add(new GameDataDTO.TerrenoDTO("Tierra3",     false, "Tierra3"));

    }
}
