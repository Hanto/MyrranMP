package DB.Recursos.TerrenoRecursos.DAO;// Created by Ladrim on 24/04/2014.

import DB.Recursos.TerrenoRecursos.DTO.TerrenoRecursos;
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
