package DB.Recursos.AtlasRecursos.DAO;// Created by Hanto on 12/06/2014.

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface AtlasRecursosDAO
{
    public TextureAtlas getAtlas();
    public TextureRegion getTextura (String localizacion);
}
