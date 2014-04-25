package DAO.TerrenoRSC;// Created by Ladrim on 24/04/2014.

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface TerrenoRSC
{
    public TextureRegion getTextura (int iDTerreno);
    public void salvarTextura (int iDTerreno, TextureRegion textura);
    public void salvarTextura(int iDTerreno, String nombreTexturaEnAtlas, TextureAtlas atlas);
}
