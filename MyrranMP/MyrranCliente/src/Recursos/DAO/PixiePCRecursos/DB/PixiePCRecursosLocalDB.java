package Recursos.DAO.PixiePCRecursos.DB;// Created by Hanto on 01/05/2014.

import View.Graficos.Pixie;

import java.util.HashMap;
import java.util.Map;

public class PixiePCRecursosLocalDB
{
    private static class Singleton      { private static final PixiePCRecursosLocalDB get = new PixiePCRecursosLocalDB(); }
    public static PixiePCRecursosLocalDB get()  { return Singleton.get; }

    public HashMap<String, EquipoPC> listaDePCRazas = new HashMap<>();
    public static class EquipoPC
    {
        public Map<String,Pixie> listaDeCuerpos = new HashMap<>();
        public Map<String,Pixie>listaDeCabezas = new HashMap<>();
        public Map<String,Pixie>listaDeYelmos = new HashMap<>();
        public Map<String,Pixie>listaDePetos = new HashMap<>();
        public Map<String,Pixie>listaDePantalones = new HashMap<>();
        public Map<String,Pixie>listaDeGuantes = new HashMap<>();
        public Map<String,Pixie>listaDeBotas = new HashMap<>();
        public Map<String,Pixie>listaDeHombreras = new HashMap<>();
        public Map<String,Pixie>listaDeCapasTraseras = new HashMap<>();
        public Map<String,Pixie>listaDeCapasFrontales = new HashMap<>();
    }
}
