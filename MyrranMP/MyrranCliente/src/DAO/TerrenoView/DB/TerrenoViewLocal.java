package DAO.TerrenoView.DB;// Created by Ladrim on 24/04/2014.

import DAO.TerrenoView.TerrenoViewDAO;
import Data.MiscData;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Map;

public class TerrenoViewLocal implements TerrenoViewDAO
{
    private Map<Integer, TextureRegion> listaDeTexturasTerreno = TerrenoViewLocalDB.get().listaDeTexturasTerreno;


    @Override public TextureRegion getTextura(int iDTerreno)
    {   return listaDeTexturasTerreno.get(iDTerreno); }

    @Override public void salvarTextura(int iDTerreno, TextureRegion textura)
    {   listaDeTexturasTerreno.put(iDTerreno, textura); }

    @Override public void salvarTextura(int iDTerreno, String nombreTexturaEnAtlas, TextureAtlas atlas)
    {
        TextureRegion textura = new TextureRegion(atlas.findRegion(MiscData.ATLAS_Terrenos_LOC+nombreTexturaEnAtlas));
        listaDeTexturasTerreno.put(iDTerreno, textura);
    }
}
