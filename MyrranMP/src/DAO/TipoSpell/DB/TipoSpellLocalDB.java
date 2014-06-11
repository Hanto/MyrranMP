package DAO.TipoSpell.DB;// Created by Hanto on 17/04/2014.

import Interfaces.Spell.TipoSpellI;

import java.util.HashMap;
import java.util.Map;

public class TipoSpellLocalDB
{
    private static class Singleton          { private static final TipoSpellLocalDB get = new TipoSpellLocalDB(); }
    public static TipoSpellLocalDB get()    { return Singleton.get; }

    public Map<String, TipoSpellI>  listaDeTipoSpells = new HashMap<>();
}