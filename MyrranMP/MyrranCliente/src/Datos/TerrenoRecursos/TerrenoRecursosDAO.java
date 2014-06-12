package Datos.TerrenoRecursos;// Created by Ladrim on 24/04/2014.

import Datos.TerrenoRecursos.DTO.TerrenoRecursos;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface TerrenoRecursosDAO
{
    public TextureRegion getTextura (String nombreTextura);
    public void salvarTextura (String nombreTextura, TextureRegion textura);
    public void salvarTextura (String nombreTextura, String nombreTexturaEnAtlas, TextureAtlas atlas);

    public void salvarTerrenoRecurso (TerrenoRecursos terreno);
    public TerrenoRecursos getTerrenoRecurso (int iDTerreno);
}
