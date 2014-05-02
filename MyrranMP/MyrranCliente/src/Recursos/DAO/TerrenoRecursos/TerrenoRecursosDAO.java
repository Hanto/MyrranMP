package Recursos.DAO.TerrenoRecursos;// Created by Ladrim on 24/04/2014.

import Recursos.Classes.TerrenoRecursos;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface TerrenoRecursosDAO
{
    public TextureRegion getTextura (String nombreTextura);
    public void salvarTextura (String nombreTextura, TextureRegion textura);
    public void salvarTextura(String nombreTextura, String nombreTexturaEnAtlas, TextureAtlas atlas);

    public void salvarTerrenoRSC (TerrenoRecursos terreno);
    public TerrenoRecursos getTerrenoRSC (int iDTerreno);
}
