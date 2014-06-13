package DB.Recursos.TerrenoRecursos.DAO;// Created by Ladrim on 24/04/2014.

import DB.Recursos.TerrenoRecursos.TerrenoRecursosLocalDB;
import Data.MiscData;
import DB.Recursos.TerrenoRecursos.DTO.TerrenoRecursos;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Map;

public class TerrenoRecursosLocal implements TerrenoRecursosDAO
{
    private Map<String, TextureRegion> listaDeTexturasTerreno = TerrenoRecursosLocalDB.get().listaDeTexturasTerreno;
    private Map<Integer, TerrenoRecursos> listaDeTerrenosRecursos = TerrenoRecursosLocalDB.get().listaDeTerrenosRecursos;


    @Override public void salvarTextura(String nombreTextura, TextureRegion textura)
    {   listaDeTexturasTerreno.put(nombreTextura, textura); }

    @Override public void salvarTextura(String nombreTextura, String nombreTexturaEnAtlas, TextureAtlas atlas)
    {
        TextureRegion textura = new TextureRegion(atlas.findRegion(MiscData.ATLAS_Terrenos_LOC+nombreTexturaEnAtlas));
        listaDeTexturasTerreno.put(nombreTextura, textura);
    }

    @Override public TextureRegion getTextura(String nombreTextura)
    {   return listaDeTexturasTerreno.get(nombreTextura); }



    @Override public void salvarTerrenoRecurso(TerrenoRecursos terreno)
    {   listaDeTerrenosRecursos.put(terreno.getID(), terreno); }

    @Override public TerrenoRecursos getTerrenoRecurso(int iDTerreno)
    {   return listaDeTerrenosRecursos.get(iDTerreno); }

}
