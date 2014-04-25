package DAO.TerrenoRSC.DB;// Created by Ladrim on 24/04/2014.

import Data.MiscData;
import DAO.TerrenoRSC.TerrenoRSC;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Map;

public class TerrenoRSCLocal implements TerrenoRSC
{
    private Map<Integer, TextureRegion> listaDeTexturasTerreno = TerrenoRSCLocalDB.get().listaDeTexturasTerreno;


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
