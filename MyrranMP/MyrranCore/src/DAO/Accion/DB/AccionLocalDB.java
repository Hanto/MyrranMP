package DAO.Accion.DB;// Created by Hanto on 06/05/2014.

import Interfaces.UI.Acciones.AccionI;

import java.util.HashMap;
import java.util.Map;

public class AccionLocalDB
{
    private static class Singleton      { private static final AccionLocalDB get = new AccionLocalDB(); }
    public static AccionLocalDB get()   { return Singleton.get; }

    public Map<String, AccionI>listaDeAcciones = new HashMap<>();
}
