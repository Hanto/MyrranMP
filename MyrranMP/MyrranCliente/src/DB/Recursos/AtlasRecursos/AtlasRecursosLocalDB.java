package DB.Recursos.AtlasRecursos;// Created by Hanto on 10/04/2014.

import Core.AbrirFichero;
import Data.MiscData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.InputStream;

public class AtlasRecursosLocalDB
{
    private static class Singleton  { private static final AtlasRecursosLocalDB get = new AtlasRecursosLocalDB(); }
    public static AtlasRecursosLocalDB get()       { return Singleton.get; }

    public TextureAtlas atlas;

    //Constructor:
    private AtlasRecursosLocalDB()
    {   cargarDatos(); }




    private void cargarDatos()
    {
        System.out.println("[CARGANDO CONFIGURACION ATLAS]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero(MiscData.RECURSOS_XML + "TexturasAtlas.xml");

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            boolean generarAtlas = Boolean.parseBoolean(rootNode.getChildText("Generar"));

            System.out.println(" GenerarAtlas : " + generarAtlas);

            //Creamos un atlas con todas las imagenes que tengamos sueltas, util para el modo edicion/desarrollador
            if (generarAtlas)
            {   TexturePacker.process(MiscData.RECURSOS_Atlas_Carpeta_Imagenes_Origen, MiscData.RECURSOS_Atlas_Carpeta_Imagenes_Destino, MiscData.RECURSOS_Atlas_Atlas_Extension); }

            System.out.println();
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de TexturasAtlas."+e); }

        //Cargamos el atlas en memoria
        atlas = new TextureAtlas(Gdx.files.internal(MiscData.RECURSOS_Atlas_Carpeta_Imagenes_Destino + MiscData.RECURSOS_Atlas_Atlas_Extension +".atlas"));
    }
}
