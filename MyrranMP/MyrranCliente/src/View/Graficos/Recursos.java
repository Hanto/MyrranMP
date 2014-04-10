package View.Graficos;// Created by Hanto on 10/04/2014.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import zMain.MiscData;

public class Recursos
{
    public TextureAtlas atlas;

    public void crearAtlas()
    {   //Creamos un atlas con todas las imagenes que tengamos sueltas, util para el modo edicion/desarrollador
        //TexturePacker.process(MiscData.RECURSOS_Atlas_Carpeta_Imagenes_Origen, MiscData.RECURSOS_Atlas_Carpeta_Imagenes_Destino, MiscData.RECURSOS_Atlas_Atlas_Extension);
        //Cargamos el atlas en memoria
        atlas = new TextureAtlas(Gdx.files.internal(MiscData.RECURSOS_Atlas_Carpeta_Imagenes_Destino + MiscData.RECURSOS_Atlas_Atlas_Extension +".atlas"));
    }

    private static class Singleton  { private static final Recursos get = new Recursos(); }
    public static Recursos get()    { return Singleton.get; }
}
