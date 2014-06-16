package DB.Recursos.FuentesRecursos;// Created by Hanto on 02/05/2014.

import Core.AbrirFichero;
import Data.MiscData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FuentesRecursosLocalDB
{
    private static class Singleton              { private static final FuentesRecursosLocalDB get = new FuentesRecursosLocalDB(); }
    public static FuentesRecursosLocalDB get()  { return Singleton.get; }

    public Map<String, BitmapFont>listaDeFuentes = new HashMap<>();

    private FuentesRecursosLocalDB()
    {   cargarDatos(); }




    public void cargarDatos()
    {
        System.out.println("[CARGANDO TEXTURAS FUENTES]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero(MiscData.RECURSOS_XML+MiscData.XML_TexturasFuentes);

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren("Fuente");

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);
                String nombre = nodo.getText();

                BitmapFont fuente = new BitmapFont(Gdx.files.internal(MiscData.ATLAS_Fuentes_LOC + nombre +".fnt"), false);
                listaDeFuentes.put(nombre, fuente);

                System.out.println(" TexturaTexto : " + nombre);
            }
            System.out.println();
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de "+MiscData.XML_TexturasFuentes+": "+e); }
    }
}
