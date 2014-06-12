package Datos.SkillRecursos;// Created by Hanto on 30/04/2014.

import Data.Misc.MiscData;
import Datos.SkillRecursos.DB.SkillRecursosLocalDB;
import Datos.SkillRecursos.DTO.SpellRecursos;
import Datos.SkillRecursos.DTO.TipoSpellRecursos;
import View.Classes.Graficos.Pixie;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Map;

public class SkillRecursosLocal implements SkillRecursosDAO
{
    public Map<String, TextureRegion> listaDeIconos = SkillRecursosLocalDB.get().listaDeIconos;
    public Map<String, Pixie> listaDeAnimaciones = SkillRecursosLocalDB.get().listaDeAnimaciones;

    public Map<String, TipoSpellRecursos> listaTipoSpell = SkillRecursosLocalDB.get().listaTipoSpell;
    public Map<String, SpellRecursos> listaSpell = SkillRecursosLocalDB.get().listaSpell;



    @Override public void salvarIcono(String nombreIcono, String nombreTextura, TextureAtlas atlas)
    {
        TextureRegion textura = new TextureRegion(atlas.findRegion(MiscData.ATLAS_Spell_Iconos_Loc+nombreTextura));
        listaDeIconos.put(nombreIcono, textura);
    }

    @Override public TextureRegion getIcono (String nombreIcono)
    {   return listaDeIconos.get(nombreIcono); }



    @Override public void salvarAnimacionCasteo(String nombreAnimacion, String nombrePixie, TextureAtlas atlas)
    {
        Pixie pixie = new Pixie(atlas.findRegion(MiscData.ATLAS_Spell_Animations_LOC+nombrePixie),1,3);
        pixie.añadirAnimacion("Casteo", new int[]{0, 1, 2}, 0.15f, false);
        pixie.animaciones().get(0).animarYEliminar = true;
        listaDeAnimaciones.put(nombreAnimacion, pixie);
    }

    @Override public void salvarAnimacionProyectil(String nombreAnimacion, String nombrePixie, TextureAtlas atlas)
    {
        Pixie pixie = new Pixie(atlas.findRegion(MiscData.ATLAS_Spell_Animations_LOC+nombrePixie),1,3);
        pixie.añadirAnimacion("Casteo", new int[]{0, 1, 2}, 0.15f, false);
        listaDeAnimaciones.put(nombreAnimacion, pixie);
    }

    @Override public Pixie getAnimacion (String nombreAnimacion)
    {   return listaDeAnimaciones.get(nombreAnimacion); }



    @Override public void salvarTipoSpellRecursos(TipoSpellRecursos tipoSpellRecursos)
    {   listaTipoSpell.put(tipoSpellRecursos.getID(), tipoSpellRecursos); }

    @Override public TipoSpellRecursos getTipoSpellRecursos(String tipoSpellID)
    {   return listaTipoSpell.get(tipoSpellID);}



    @Override public void salvarSpellRecursos(SpellRecursos spellRecursos)
    {   listaSpell.put(spellRecursos.getID(), spellRecursos); }

    @Override public SpellRecursos getSpellRecursos(String spellID)
    {   return listaSpell.get(spellID); }
}
