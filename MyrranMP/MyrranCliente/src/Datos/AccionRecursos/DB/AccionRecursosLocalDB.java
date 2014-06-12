package Datos.AccionRecursos.DB;// Created by Hanto on 07/05/2014.

import Datos.AccionRecursos.DTO.AccionRecursos;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class AccionRecursosLocalDB
{
    private static class Singleton              { private static final AccionRecursosLocalDB get = new AccionRecursosLocalDB(); }
    public static AccionRecursosLocalDB get()   { return Singleton.get; }

    public Map<String, TextureRegion> listaDeTexturasAcciones = new HashMap<>();
    public Map<String, AccionRecursos> listaDeAccionRecursos = new HashMap<>();
}
