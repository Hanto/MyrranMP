package DB.Recursos.AccionRecursos;// Created by Hanto on 07/05/2014.

import Core.AbrirFichero;
import DB.RSC;
import DB.Recursos.AccionRecursos.DTO.AccionRecursos;
import Data.MiscData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccionRecursosLocalDB
{
    private static class Singleton              { private static final AccionRecursosLocalDB get = new AccionRecursosLocalDB(); }
    public static AccionRecursosLocalDB get()   { return Singleton.get; }

    public Map<String, TextureRegion> listaDeTexturasAcciones = new HashMap<>();
    public Map<String, AccionRecursos> listaDeAccionRecursos = new HashMap<>();


    private AccionRecursosLocalDB()
    {   cargarDatos(); }

    public void cargarDatos()
    {
        cargarTexturasIconos();
        cargarAccionRecursos();
    }


    public void cargarTexturasIconos()
    {
        System.out.println("[CARGANDO TEXTURAS ICONOS ACCIONES]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero(MiscData.RECURSOS_XML + "TexturasIconosAcciones.xml");

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren("Icono");

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);
                String nombre = nodo.getText();

                TextureRegion textura = new TextureRegion(RSC.atlasRecursosDAO.getAtlasRecursosDAO().getAtlas().findRegion(MiscData.ATLAS_TexturasIconos_LOC + nombre));
                listaDeTexturasAcciones.put(nombre, textura);

                System.out.println(" TexturaIconoAccion : " + nombre);
            }
            System.out.println();
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de TexturasIconosAcciones."+e); }
    }

    public void cargarAccionRecursos()
    {
        System.out.println("[CARGANDO ACCION RECURSOS]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero(MiscData.RECURSOS_XML+"Acciones.xml");

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren("Accion");

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);

                String iD           = nodo.getChildText("iD");
                String nombreIcono  = nodo.getChildText("icono");

                AccionRecursos spellRecursos =  new AccionRecursos(iD, listaDeTexturasAcciones.get(nombreIcono));
                listaDeAccionRecursos.put(iD, spellRecursos);

                System.out.println(" iD :          " + iD);
                System.out.println(" Icono       : " + nombreIcono);
            }
            System.out.println();
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de Acciones."+e); }
    }
}
