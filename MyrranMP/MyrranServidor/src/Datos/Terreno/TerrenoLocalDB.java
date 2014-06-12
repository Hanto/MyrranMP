package Datos.Terreno;// Created by Hanto on 15/04/2014.

import Interfaces.Geo.TerrenoI;
import Model.Classes.Geo.Terreno;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerrenoLocalDB
{
    private static class Singleton      { private static final TerrenoLocalDB get = new TerrenoLocalDB(); }
    public static TerrenoLocalDB get()  { return Singleton.get; }

    public Map<Short, TerrenoI> listaDeTerrenos = new HashMap<>();

    public TerrenoLocalDB()
    {   cargarDatos(); }

    public void cargarDatos()
    {
        SAXBuilder builder = new SAXBuilder();
        File fichero = new File("Data/Terrenos.xml");

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
                boolean isSolido= Boolean.parseBoolean(nodo.getChildText("isSolido"));

                Terreno terreno = new Terreno(iD, nombre, isSolido);
                listaDeTerrenos.put(iD, terreno);

                System.out.println("nombre :        " + iD);
                System.out.println("nombreTextura : " + nombre);
                System.out.println("isSolido :      " + isSolido);
            }
        }
        catch (IOException io) { System.out.println(io.getMessage()); }
        catch (JDOMException jdomex) { System.out.println(jdomex.getMessage()); }
    }

}
