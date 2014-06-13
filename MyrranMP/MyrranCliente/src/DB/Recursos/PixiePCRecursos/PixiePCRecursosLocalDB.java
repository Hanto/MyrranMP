package DB.Recursos.PixiePCRecursos;// Created by Hanto on 01/05/2014.

import Core.AbrirFichero;
import DB.Recursos.PixiePCRecursos.DTO.PixieRecursos;
import View.Classes.Graficos.Pixie;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PixiePCRecursosLocalDB
{
    private static class Singleton              { private static final PixiePCRecursosLocalDB get = new PixiePCRecursosLocalDB(); }
    public static PixiePCRecursosLocalDB get()  { return Singleton.get;}

    public HashMap<String, EquipoPC> listaDePCRazas = new HashMap<>();
    public static class EquipoPC
    {
        public Map<String,Pixie>listaDeCuerpos = new HashMap<>();
        public Map<String,Pixie>listaDeCabezas = new HashMap<>();
        public Map<String,Pixie>listaDeYelmos = new HashMap<>();
        public Map<String,Pixie>listaDePetos = new HashMap<>();
        public Map<String,Pixie>listaDePantalones = new HashMap<>();
        public Map<String,Pixie>listaDeGuantes = new HashMap<>();
        public Map<String,Pixie>listaDeBotas = new HashMap<>();
        public Map<String,Pixie>listaDeHombreras = new HashMap<>();
        public Map<String,Pixie>listaDeCapasTraseras = new HashMap<>();
        public Map<String,Pixie>listaDeCapasFrontales = new HashMap<>();

        public Map<String,Pixie>getSlot(String slot)
        {
            switch (slot)
            {
                case "Cuerpo":      return listaDeCuerpos;
                case "Cabeza":      return listaDeCabezas;
                case "Yelmo":       return listaDeYelmos;
                case "Peto":        return listaDePetos;
                case "Pantalon":    return listaDePantalones;
                case "Guante":      return listaDeGuantes;
                case "Bota":        return listaDeBotas;
                case "Hombrera":    return listaDeHombreras;
                case "CapaTrasera": return listaDeCapasTraseras;
                case "CapaFrontal": return listaDeCapasFrontales;
                default:            return null;
            }
        }
    }

    private PixiePCRecursosLocalDB()
    {   cargarDatos(); }

    public void cargarDatos()
    {
        cargarRazas();
        cargarCuerpos();
        cargarSlot("Cabeza");
        cargarSlot("Yelmo");
        cargarSlot("Bota");
        cargarSlot("Guante");
        cargarSlot("Hombrera");
        cargarSlot("Pantalon");
        cargarSlot("Peto");
        cargarSlot("CapaFrontal");
        cargarSlot("CapaTrasera");
    }

    public void cargarRazas()
    {
        System.out.println("[CARGANDO RAZAS]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero("Data/PixieRaza.xml");

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren("Raza");

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);
                String nombre = nodo.getText();

                EquipoPC equipoPC = new EquipoPC();
                listaDePCRazas.put(nombre, equipoPC);

                System.out.println(" Raza : " + nombre);
            }
            System.out.println();
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de PixieRazas."+e); }
    }

    public void cargarCuerpos()
    {
        System.out.println("[CARGANDO CUERPOS]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero("Data/PixieCuerpo.xml");

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren("Cuerpo");

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);

                String raza     = nodo.getChildText("Raza");
                String nombre   = nodo.getChildText("Textura");


                PixieRecursos.PixieCuerpo pixieCuerpo = new PixieRecursos.PixieCuerpo(nombre);
                listaDePCRazas.get(raza).listaDeCuerpos.put(nombre, pixieCuerpo);

                System.out.println(" Raza :          " + raza);
                System.out.println(" Textura Cuerpo: " + nombre);
            }
            System.out.println();
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de PixieCuerpos."+e); }
    }

    public void cargarSlot(String slot)
    {
        System.out.println("[CARGANDO "+slot.toUpperCase()+"S]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero("Data/Pixie"+slot+".xml");

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren(slot);

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);

                String raza     = nodo.getChildText("Raza");
                String nombre   = nodo.getChildText("Textura");

                PixieRecursos.PixieArmadura pixieArmadura = new PixieRecursos.PixieArmadura(nombre);
                listaDePCRazas.get(raza).getSlot(slot).put(nombre, pixieArmadura);

                System.out.println(" Raza :          " + raza);
                System.out.println(" Textura Cuerpo: " + nombre);
            }
            System.out.println();
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de Pixie"+slot+"s."+e); }
    }
}
