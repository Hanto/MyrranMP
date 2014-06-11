package Recursos.Classes;// Created by Hanto on 10/04/2014.

import Data.Misc.MiscData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AtlasRecursos
{
    private static class Singleton  { private static final AtlasRecursos get = new AtlasRecursos(); }
    public static AtlasRecursos get()       { return Singleton.get; }

    public TextureAtlas atlas;

    private void crearAtlas()
    {   //Creamos un atlas con todas las imagenes que tengamos sueltas, util para el modo edicion/desarrollador
        //TexturePacker.process(MiscData.RECURSOS_Atlas_Carpeta_Imagenes_Origen, MiscData.RECURSOS_Atlas_Carpeta_Imagenes_Destino, MiscData.RECURSOS_Atlas_Atlas_Extension);
        //Cargamos el atlas en memoria
        atlas = new TextureAtlas(Gdx.files.internal(MiscData.RECURSOS_Atlas_Carpeta_Imagenes_Destino + MiscData.RECURSOS_Atlas_Atlas_Extension +".atlas"));
    }

    //Constructor:
    public AtlasRecursos() { crearAtlas(); }

    public TextureRegion getTextura (String localizacion)
    {   return (new TextureRegion(atlas.findRegion(localizacion))); }
}
