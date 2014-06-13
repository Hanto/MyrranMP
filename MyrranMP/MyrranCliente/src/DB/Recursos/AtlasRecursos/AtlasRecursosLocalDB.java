package DB.Recursos.AtlasRecursos;// Created by Hanto on 10/04/2014.

import Data.MiscData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AtlasRecursosLocalDB
{
    private static class Singleton  { private static final AtlasRecursosLocalDB get = new AtlasRecursosLocalDB(); }
    public static AtlasRecursosLocalDB get()       { return Singleton.get; }

    public TextureAtlas atlas;

    //Constructor:
    private AtlasRecursosLocalDB()
    {   cargarDatos(); }

    private void cargarDatos()
    {   //Creamos un atlas con todas las imagenes que tengamos sueltas, util para el modo edicion/desarrollador
        //TexturePacker.process(MiscData.RECURSOS_Atlas_Carpeta_Imagenes_Origen, MiscData.RECURSOS_Atlas_Carpeta_Imagenes_Destino, MiscData.RECURSOS_Atlas_Atlas_Extension);
        //Cargamos el atlas en memoria
        atlas = new TextureAtlas(Gdx.files.internal(MiscData.RECURSOS_Atlas_Carpeta_Imagenes_Destino + MiscData.RECURSOS_Atlas_Atlas_Extension +".atlas"));
    }
}
