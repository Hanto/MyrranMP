package DAO.Terreno.DB;// Created by Hanto on 15/04/2014.

import Interfaces.Geo.TerrenoI;

import java.util.HashMap;
import java.util.Map;

public class TerrenoLocalDB
{
    private static class Singleton      { private static final TerrenoLocalDB get = new TerrenoLocalDB(); }
    public static TerrenoLocalDB get()  { return Singleton.get; }

    public Map<Short, TerrenoI> listaDeTerrenos = new HashMap<>();
}