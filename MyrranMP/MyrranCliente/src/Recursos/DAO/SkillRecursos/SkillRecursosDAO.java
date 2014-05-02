package Recursos.DAO.SkillRecursos;// Created by Hanto on 30/04/2014.

import Recursos.Classes.SpellRecursos;
import Recursos.Classes.TipoSpellRecursos;
import View.Graficos.Pixie;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface SkillRecursosDAO
{
    public void salvarIcono(String nombreIcono, String nombreTextura, TextureAtlas atlas);
    public TextureRegion getIcono (String nombreIcono);

    public void salvarAnimacionCasteo(String nombreAnimacion, String nombrePixie, TextureAtlas atlas);
    public void salvarAnimacionProyectil(String nombreAnimacion, String nombrePixie, TextureAtlas atlas);
    public Pixie getAnimacion (String nombreAnimacion);

    public void salvarTipoSpellRecursos(TipoSpellRecursos tipoSpellRecursos);
    public TipoSpellRecursos getTipoSpellRecursos(String TipoSpellID);

    public void salvarSpellRecursos(SpellRecursos spellRecursos);
    public SpellRecursos getSpellRecursos(String spellID);
}
