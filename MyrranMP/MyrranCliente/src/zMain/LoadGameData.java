package zMain;// Created by Hanto on 11/04/2014.

import Data.GameDataDTO;
import Data.GameData;
import Model.DAO.Terreno.TerrenoDAO;
import Model.DAO.Terreno.TerrenoDAOFactory;
import Model.Geo.TerrenoModel;
import View.Geo.GeoRecursos;
import View.Mobiles.MobilesRecursos;

public class LoadGameData
{
    public static void cargarTodo ()
    {
        cargarRecursos();
        cargarTerrenos();
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
        TerrenoDAO terrenoDAO = TerrenoDAOFactory.LOCAL.newInstance();
        int iDTerreno;
        for (GameDataDTO.TerrenoDTO terrenoDTO : GameData.get().listaDeTerrenoDTO)
        {
            iDTerreno = terrenoDAO.añadirTerreno(new TerrenoModel(terrenoDTO.nombre, terrenoDTO.isSolido));
            GeoRecursos.get().salvarTexturaTerreno(iDTerreno, terrenoDTO.nombreTextura);
        }
    }
}
