package Datos.TipoSpell;// Created by Hanto on 17/04/2014.

import Interfaces.Spell.TipoSpellI;
import Model.Classes.Skill.Spell.TipoSpell;
import Model.Classes.Skill.Spell.TipoSpellFactory;

import java.util.HashMap;
import java.util.Map;

public class TipoSpellLocalDB
{
    private static class Singleton          { private static final TipoSpellLocalDB get = new TipoSpellLocalDB(); }
    public static TipoSpellLocalDB get()    { return Singleton.get; }

    public Map<String, TipoSpellI>  listaDeTipoSpells = new HashMap<>();

    public TipoSpellLocalDB()
    {   cargarDatos(); }

    public void cargarDatos()
    {
        for (TipoSpellFactory tipoSpellFactory: TipoSpellFactory.values())
        {
            TipoSpell tipoSpell = tipoSpellFactory.nuevoTipoSpell();
            tipoSpell.setID(tipoSpellFactory.name());
            listaDeTipoSpells.put(tipoSpell.getID(), tipoSpell);
        }
    }
}
