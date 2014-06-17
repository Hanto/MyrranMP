package DB.Datos.Terreno;// Created by Hanto on 15/04/2014.

import Core.AbrirFichero;
import Data.MiscData;
import Interfaces.Geo.TerrenoI;
import Model.Classes.Geo.Terreno;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerrenoLocalDB
{
    private static class Singleton      { private static final TerrenoLocalDB get = new TerrenoLocalDB(); }
    public static TerrenoLocalDB get()  { return Singleton.get; }

    public Map<Short, TerrenoI> listaDeTerrenos = new HashMap<>();

    private TerrenoLocalDB()
    {   cargarDatos(); }

    public void cargarDatos()
    {
        System.out.println("[CARGANDO TERRENOS]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero(MiscData.RECURSOS_XML+MiscData.XML_DataTerrenos);

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren("Terreno");

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);

                short iD        = Short.parseShort(nodo.getChildText("iD"));
                String nombre   = nodo.getChildText("nombre");
                String nombreT  = nodo.getChildText("nombreTextura");
                boolean isSolido= Boolean.parseBoolean(nodo.getChildText("isSolido"));

                Terreno terreno = new Terreno(iD, nombre, isSolido);
                listaDeTerrenos.put(iD, terreno);

                System.out.println(" iD :            " + iD);
                System.out.println(" nombre :        " + nombre);
                System.out.println(" nombreTextura : " + nombreT);
                System.out.println(" isSolido :      " + isSolido);
            }
            System.out.println();
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de "+MiscData.XML_DataTerrenos+": "+e); }
    }
}
