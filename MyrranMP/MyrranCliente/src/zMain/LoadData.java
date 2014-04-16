package zMain;// Created by Hanto on 11/04/2014.

import Model.DAO.Terreno.TerrenoDAO;
import Model.DAO.Terreno.TerrenoDAOFactory;
import Model.Geo.TerrenoModel;
import View.Geo.GeoRecursos;
import View.Mobiles.MobilesRecursos;

public class LoadData
{
    public static void cargarTodo ()
    {
        cargarRecursos();
        cargarTerrenos();
        cargarTerrenosRecursos();
    }

    public static void cargarRecursos ()
    {
        MobilesRecursos.get().añadirRazaPC("Golem");
        MobilesRecursos.get().salvarCuerpoPC("Golem", "Golem");

        MobilesRecursos.get().salvarYelmoPC("Golem", "Desnudo");
        MobilesRecursos.get().salvarCabezaPC("Golem", "Desnudo");
        MobilesRecursos.get().salvarBotasPC("Golem", "Desnudo");
        MobilesRecursos.get().salvarGuantesPC("Golem", "Desnudo");
        MobilesRecursos.get().salvarHombrerasPC("Golem", "Desnudo");
        MobilesRecursos.get().salvarPantalonesPC("Golem", "Desnudo");
        MobilesRecursos.get().salvarPetoPC("Golem", "Desnudo");
        MobilesRecursos.get().salvarCapasFrontalesPC("Golem", "Desnudo");
        MobilesRecursos.get().salvarCapasTraserasPC("Golem", "Desnudo");
    }

    public static void cargarTerrenos()
    {
        TerrenoDAO terrenoDAO = TerrenoDAOFactory.LOCAL.newInstance();
        terrenoDAO.añadirTerreno(new TerrenoModel("Arena1",      false));
        terrenoDAO.añadirTerreno(new TerrenoModel("Arena2",     false));
        terrenoDAO.añadirTerreno(new TerrenoModel("Baldosas1",     false));
        terrenoDAO.añadirTerreno(new TerrenoModel("Baldosas2",     false));
        terrenoDAO.añadirTerreno(new TerrenoModel("Baldosas3",      false));
        terrenoDAO.añadirTerreno(new TerrenoModel("Cesped1",   false));
        terrenoDAO.añadirTerreno(new TerrenoModel("Cesped2",     false));
        terrenoDAO.añadirTerreno(new TerrenoModel("Cesped3",     false));
        terrenoDAO.añadirTerreno(new TerrenoModel("Cesped4",     false));
        terrenoDAO.añadirTerreno(new TerrenoModel("Cesped5",     false));
        terrenoDAO.añadirTerreno(new TerrenoModel("Suelo",     false));
        terrenoDAO.añadirTerreno(new TerrenoModel("Suelo2",     false));
        terrenoDAO.añadirTerreno(new TerrenoModel("Tierra1",     false));
        terrenoDAO.añadirTerreno(new TerrenoModel("Tierra2",     false));
        terrenoDAO.añadirTerreno(new TerrenoModel("Tierra3",     false));
    }

    public static void cargarTerrenosRecursos()
    {
        GeoRecursos.get().salvarTexturaTerreno("Arena1");
        GeoRecursos.get().salvarTexturaTerreno("Arena2");
        GeoRecursos.get().salvarTexturaTerreno("Baldosas1");
        GeoRecursos.get().salvarTexturaTerreno("Baldosas2");
        GeoRecursos.get().salvarTexturaTerreno("Baldosas3");
        GeoRecursos.get().salvarTexturaTerreno("Cesped1");
        GeoRecursos.get().salvarTexturaTerreno("Cesped2");
        GeoRecursos.get().salvarTexturaTerreno("Cesped3");
        GeoRecursos.get().salvarTexturaTerreno("Cesped4");
        GeoRecursos.get().salvarTexturaTerreno("Cesped5");
        GeoRecursos.get().salvarTexturaTerreno("Suelo");
        GeoRecursos.get().salvarTexturaTerreno("Suelo2");
        GeoRecursos.get().salvarTexturaTerreno("Tierra1");
        GeoRecursos.get().salvarTexturaTerreno("Tierra2");
        GeoRecursos.get().salvarTexturaTerreno("Tierra3");
    }
}
