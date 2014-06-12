package Datos.MiscRecursos.DB;// Created by Hanto on 02/05/2014.

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class MiscRecursosLocalDB
{
    private static class Singleton          { private static final MiscRecursosLocalDB get = new MiscRecursosLocalDB(); }
    public static MiscRecursosLocalDB get() { return Singleton.get; }

    public Map<String, TextureRegion> listaDeTexturas = new HashMap<>();

}
