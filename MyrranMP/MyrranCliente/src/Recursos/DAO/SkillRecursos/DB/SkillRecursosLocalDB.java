package Recursos.DAO.SkillRecursos.DB;// Created by Hanto on 30/04/2014.

import Recursos.Classes.SpellRecursos;
import Recursos.Classes.TipoSpellRecursos;
import View.Graficos.Pixie;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class SkillRecursosLocalDB
{
    private static class Singleton              { private static final SkillRecursosLocalDB get = new SkillRecursosLocalDB(); }
    public static SkillRecursosLocalDB get()    { return Singleton.get; }

    public Map<String, TextureRegion> listaDeIconos = new HashMap<>();
    public Map<String, Pixie> listaDeAnimaciones = new HashMap<>();
    public Map<String, SpellRecursos> listaSpell = new HashMap<>();
    public Map<String, TipoSpellRecursos> listaTipoSpell = new HashMap<>();

}
