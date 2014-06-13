package DB.Recursos.MiscRecursos.DAO;// Created by Hanto on 02/05/2014.

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface MiscRecursosDAO
{
    public void salvarTextura(String nombreRecurso, String rutaYnombreTextura, TextureAtlas atlas);
    public TextureRegion cargarTextura(String nombreRecurso);
}
