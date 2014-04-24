package View.Recursos.TerrenoRSC.DB;// Created by Hanto on 15/04/2014.

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class TerrenoRSCLocalDB
{
    private static class Singleton      { private static final TerrenoRSCLocalDB get = new TerrenoRSCLocalDB(); }
    public static TerrenoRSCLocalDB get()     { return Singleton.get; }

    public Map<Integer, TextureRegion> listaDeTexturasTerreno = new HashMap<>();
}

