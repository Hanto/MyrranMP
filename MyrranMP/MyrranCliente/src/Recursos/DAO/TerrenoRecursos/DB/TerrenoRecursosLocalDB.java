package Recursos.DAO.TerrenoRecursos.DB;// Created by Hanto on 15/04/2014.

import Data.Misc.MiscData;
import Recursos.Classes.AtlasRecursos;
import Recursos.Classes.TerrenoRecursos;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class TerrenoRecursosLocalDB
{
    private static class Singleton              { private static final TerrenoRecursosLocalDB get = new TerrenoRecursosLocalDB(); }
    public static TerrenoRecursosLocalDB get()  { return Singleton.get; }

    public Map<String, TextureRegion> listaDeTexturasTerreno = new HashMap<>();
    public Map<Integer, TerrenoRecursos> listaDeTerrenos = new HashMap<>();

    public TextureRegion grid = new TextureRegion(AtlasRecursos.get().atlas.findRegion(MiscData.ATLAS_Terrenos_LOC + "grid"));
}

