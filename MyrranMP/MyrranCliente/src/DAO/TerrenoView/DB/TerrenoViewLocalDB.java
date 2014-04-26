package DAO.TerrenoView.DB;// Created by Hanto on 15/04/2014.

import Data.MiscData;
import View.Graficos.Atlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class TerrenoViewLocalDB
{
    private static class Singleton      { private static final TerrenoViewLocalDB get = new TerrenoViewLocalDB(); }
    public static TerrenoViewLocalDB get()     { return Singleton.get; }

    public Map<Integer, TextureRegion> listaDeTexturasTerreno = new HashMap<>();
    public TextureRegion grid = new TextureRegion(Atlas.get().atlas.findRegion(MiscData.ATLAS_Terrenos_LOC + "grid"));
}

