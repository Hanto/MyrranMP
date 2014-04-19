package View.Graficos;// Created by Hanto on 10/04/2014.

import Data.MiscData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Atlas
{
    private static class Singleton  { private static final Atlas get = new Atlas(); }
    public static Atlas get()       { return Singleton.get; }

    public TextureAtlas atlas;

    public void crearAtlas()
    {   //Creamos un atlas con todas las imagenes que tengamos sueltas, util para el modo edicion/desarrollador
        //TexturePacker.process(MiscData.RECURSOS_Atlas_Carpeta_Imagenes_Origen, MiscData.RECURSOS_Atlas_Carpeta_Imagenes_Destino, MiscData.RECURSOS_Atlas_Atlas_Extension);
        //Cargamos el atlas en memoria
        atlas = new TextureAtlas(Gdx.files.internal(MiscData.RECURSOS_Atlas_Carpeta_Imagenes_Destino + MiscData.RECURSOS_Atlas_Atlas_Extension +".atlas"));
    }

    //Constructor:
    public Atlas() { crearAtlas(); }
}
