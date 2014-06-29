package DB.Recursos.FuentesRecursos.DAO;// Created by Hanto on 02/05/2014.

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public interface FuentesRecursosDAO
{
    public void salvarFuente(String nombreFuente, String nombreTextura, TextureAtlas atlas);
    public BitmapFont getFuente (String nombreFuente);
    public void dispose();
}
