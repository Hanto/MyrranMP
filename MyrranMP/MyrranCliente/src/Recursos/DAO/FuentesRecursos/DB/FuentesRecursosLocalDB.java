package Recursos.DAO.FuentesRecursos.DB;// Created by Hanto on 02/05/2014.

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.HashMap;
import java.util.Map;

public class FuentesRecursosLocalDB
{
    private static class Singleton              { private static final FuentesRecursosLocalDB get = new FuentesRecursosLocalDB(); }
    public static FuentesRecursosLocalDB get()  { return Singleton.get; }

    public Map<String, BitmapFont>listaDeFuentes = new HashMap<>();
}
