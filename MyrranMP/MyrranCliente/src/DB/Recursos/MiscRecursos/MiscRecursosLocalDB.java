package DB.Recursos.MiscRecursos;// Created by Hanto on 02/05/2014.

import Core.AbrirFichero;
import DB.RSC;
import Data.MiscData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiscRecursosLocalDB
{
    private static class Singleton          { private static final MiscRecursosLocalDB get = new MiscRecursosLocalDB(); }
    public static MiscRecursosLocalDB get() { return Singleton.get; }

    public Map<String, TextureRegion> listaDeTexturasMisc = new HashMap<>();

    private MiscRecursosLocalDB()
    {   cargarDatos(); }



    public void cargarDatos()
    {
        System.out.println("[CARGANDO TEXTURAS MISCELANEAS]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero(MiscData.RECURSOS_XML+MiscData.XML_TexturasMisc);

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren("Textura");

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);
                String nombre = nodo.getText();

                TextureRegion textura = new TextureRegion(RSC.atlasRecursosDAO.getAtlasRecursosDAO().getAtlas().findRegion(MiscData.ATLAS_TexturasMisc_LOC + nombre));
                listaDeTexturasMisc.put(nombre, textura);

                System.out.println(" TexturaMisc : " + nombre);
            }
            System.out.println();
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de "+MiscData.XML_TexturasMisc+": "+e); }
    }

}
