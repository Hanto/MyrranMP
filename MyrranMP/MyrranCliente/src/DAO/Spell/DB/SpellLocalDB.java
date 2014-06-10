package DAO.Spell.DB;// Created by Hanto on 17/04/2014.

import Model.Classes.Skill.Spell.Spell;

import java.util.HashMap;
import java.util.Map;

public class SpellLocalDB
{
    private static class Singleton      { private static final SpellLocalDB get = new SpellLocalDB(); }
    public static SpellLocalDB get()    { return Singleton.get; }

    public Map<String, Spell> listaDeSpells = new HashMap<>();
}
