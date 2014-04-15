package View.Geo;// Created by Hanto on 15/04/2014.

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import zMain.MiscData;

import java.util.HashMap;
import java.util.Map;

public class GeoRecursos
{
    private static class Singleton      { private static final GeoRecursos get = new GeoRecursos(); }
    public static GeoRecursos get()     { return Singleton.get; }

    private TextureAtlas atlas;
    private Map<String, TextureRegion> listaDeTexturasTerreno = new HashMap<>();


    public void setAtlas(TextureAtlas atlas)
    {
        this.atlas = atlas;
    }



    public TextureRegion getTexturaTerreno (String iDTerreno)
    {
        if ( atlas == null ) { System.out.println("ERROR Atlas no especificado"); return null; }
        return listaDeTexturasTerreno.get(iDTerreno);
    }

    public void salvarTexturaTerreno (String iDTerreno)
    {
        if ( atlas == null) { System.out.println("ERROR Atlas no especificado"); return; }
        try
        {
            TextureRegion texture = new TextureRegion(atlas.findRegion(MiscData.ATLAS_Terrenos_LOC + iDTerreno));
            listaDeTexturasTerreno.put(iDTerreno,texture);
        }
        catch (Exception e) { System.out.println("ERROR Textura Terreno: ["+iDTerreno+"] no encontrada."); }
    }
}

