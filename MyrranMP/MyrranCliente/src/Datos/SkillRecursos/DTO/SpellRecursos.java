package Datos.SkillRecursos.DTO;// Created by Hanto on 30/04/2014.

import View.Classes.Graficos.Pixie;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpellRecursos
{
    private String id;
    private TextureRegion icono;
    private Pixie[] tipoAnimacion;

    public String getID()                                       { return id; }
    public TextureRegion getIcono()                             { return icono; }
    public Pixie getTipoAnimacion(int tipoAnimacion)            { return this.tipoAnimacion[tipoAnimacion]; }

    public void setID(String id)                                { this.id = id; }
    public void setIcono(TextureRegion icono)                   { this.icono = icono; }
    public void setTipoAnimacion(int tipoAnimacion, Pixie pixie){ this.tipoAnimacion[tipoAnimacion] = pixie; }

    public SpellRecursos(String iDSpell, int numTiposAnimacion)
    {   id = iDSpell; tipoAnimacion = new Pixie[numTiposAnimacion]; }
}
