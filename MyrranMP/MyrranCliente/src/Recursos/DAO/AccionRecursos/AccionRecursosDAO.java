package Recursos.DAO.AccionRecursos;// Created by Hanto on 07/05/2014.

import Recursos.Classes.AccionRecursos;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface AccionRecursosDAO
{
    public TextureRegion getTextura (String nombreTextura);
    public void salvarTextura (String nombreTextura, TextureRegion textura);
    public void salvarTextura(String nombreTextura, String nombreTexturaEnAtlas, TextureAtlas atlas);

    public void salvarAccionRecurso (AccionRecursos accionRecurso);
    public AccionRecursos getAccionRecurso (String iDAccionRecurso);

}
