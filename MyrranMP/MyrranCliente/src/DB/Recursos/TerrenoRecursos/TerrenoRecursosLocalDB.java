package DB.Recursos.TerrenoRecursos;// Created by Hanto on 15/04/2014.

import Core.AbrirFichero;
import DB.RSC;
import DB.Recursos.TerrenoRecursos.DTO.TerrenoRecursos;
import Data.MiscData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerrenoRecursosLocalDB
{
    private static class Singleton              { private static final TerrenoRecursosLocalDB get = new TerrenoRecursosLocalDB(); }
    public static TerrenoRecursosLocalDB get()  { return Singleton.get; }

    public Map<String, TextureRegion> listaDeTexturasTerreno = new HashMap<>();
    public Map<Integer, TerrenoRecursos> listaDeTerrenosRecursos = new HashMap<>();

    private TerrenoRecursosLocalDB()
    {   cargarDatos(); }

    public void cargarDatos()
    {
        cargarTexturasTerrenos();
        cargarTerrenoRecursos();
    }


    public void cargarTexturasTerrenos()
    {
        System.out.println("[CARGANDO TEXTURAS TERRENOS]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero(MiscData.RECURSOS_XML+"TexturasTerrenos.xml");

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren("Terreno");

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);
                String nombre = nodo.getText();

                TextureRegion textura = new TextureRegion(RSC.atlasRecursosDAO.getAtlasRecursosDAO().getAtlas().findRegion(MiscData.ATLAS_TexturasTerrenos_LOC + nombre));
                listaDeTexturasTerreno.put(nombre, textura);

                System.out.println(" TexturaTerreno : " + nombre);
            }
            System.out.println();
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de TexturasTerrenos."+e); }
    }

    public void cargarTerrenoRecursos()
    {
        System.out.println("[CARGANDO TERRENOS RECURSOS]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero(MiscData.RECURSOS_XML+"Terrenos.xml");

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren("Terreno");

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);

                short iD        = Short.parseShort(nodo.getChildText("iD"));
                String nombreT  = nodo.getChildText("nombreTextura");

                TerrenoRecursos terrenoRSC = new TerrenoRecursos(iD, listaDeTexturasTerreno.get(nombreT));
                listaDeTerrenosRecursos.put(terrenoRSC.getID(), terrenoRSC);

                System.out.println(" iD:             " + iD);
                System.out.println(" nombreTextura:  " + nombreT);
            }
            System.out.println();
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de Terrenos."+e); }
    }
}

