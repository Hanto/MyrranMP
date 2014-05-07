package Model.DAO.Accion.DB;// Created by Hanto on 06/05/2014.

import Model.Classes.Acciones.Accion;

import java.util.HashMap;
import java.util.Map;

public class AccionLocalDB
{
    private static class Singleton      { private static final AccionLocalDB get = new AccionLocalDB(); }
    public static AccionLocalDB get()   { return Singleton.get; }

    public Map<String, Accion>listaDeAcciones = new HashMap<>();
}
