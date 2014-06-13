package DB.Recursos.SkillRecursos;// Created by Hanto on 30/04/2014.

import Core.AbrirFichero;
import DB.RSC;
import DB.Recursos.SkillRecursos.DTO.SpellRecursos;
import DB.Recursos.SkillRecursos.DTO.TipoSpellRecursos;
import Data.MiscData;
import View.Classes.Graficos.Pixie;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillRecursosLocalDB
{
    private static class Singleton              { private static final SkillRecursosLocalDB get = new SkillRecursosLocalDB(); }
    public static SkillRecursosLocalDB get()    { return Singleton.get; }

    public Map<String, TextureRegion> listaDeTexturasIconosSpells = new HashMap<>();
    public Map<String, Pixie> listaDeAnimaciones = new HashMap<>();
    public Map<String, SpellRecursos> listaSpell = new HashMap<>();
    public Map<String, TipoSpellRecursos> listaTipoSpell = new HashMap<>();


    private SkillRecursosLocalDB()
    {   cargarDatos(); }

    public void cargarDatos()
    {
        cargarTexturasIconos();
        cargarAnimacionesCasteo();
        cargarAnimacionesProyectil();

        cargarSpellRecursos();
    }


    public void cargarTexturasIconos()
    {
        System.out.println("[CARGANDO TEXTURAS ICONOS]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero(MiscData.RECURSOS_XML+"TexturasIconosSpells.xml");

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
                listaDeTexturasIconosSpells.put(nombre, textura);

                System.out.println(" TexturaIconoSpell : " + nombre);
            }
            System.out.println();
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de TexturasIconosSpells."+e); }
    }

    public void cargarAnimacionesCasteo()
    {
        System.out.println("[CARGANDO ANIMACIONES CASTEO]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero(MiscData.RECURSOS_XML+"AnimacionesCasteo.xml");

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren("Animacion");

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);
                String nombre = nodo.getText();

                Pixie pixie = new Pixie(RSC.atlasRecursosDAO.getAtlasRecursosDAO().getAtlas().findRegion(MiscData.ATLAS_AnimacionesSpells_LOC + nombre),1,3);
                pixie.añadirAnimacion("Casteo", new int[]{0, 1, 2}, 0.15f, false);
                pixie.animaciones().get(0).animarYEliminar = true;
                listaDeAnimaciones.put(nombre, pixie);

                System.out.println(" AnimacionCasteo : " + nombre);
            }
            System.out.println();
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de AnimacionesCasteos."+e); }
    }

    public void cargarAnimacionesProyectil()
    {
        System.out.println("[CARGANDO ANIMACIONES PROYECTIL]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero(MiscData.RECURSOS_XML+"AnimacionesProyectil.xml");

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren("Animacion");

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);
                String nombre = nodo.getText();

                Pixie pixie = new Pixie(RSC.atlasRecursosDAO.getAtlasRecursosDAO().getAtlas().findRegion(MiscData.ATLAS_AnimacionesSpells_LOC + nombre),1,3);
                pixie.añadirAnimacion("Proyectil", new int[]{0, 1, 2}, 0.15f, false);
                listaDeAnimaciones.put(nombre, pixie);

                System.out.println(" AnimacionProyectil : " + nombre);
            }
            System.out.println();
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de AnimacionesCasteos."+e); }
    }

    public void cargarSpellRecursos()
    {
        System.out.println("[CARGANDO SPELL RECURSOS]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero(MiscData.RECURSOS_XML+"Spells.xml");

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren("Spell");

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);

                String iD           = nodo.getChildText("iD");
                String nombreIcono  = nodo.getChildText("icono");

                SpellRecursos spellRecursos =  new SpellRecursos(iD, listaDeTexturasIconosSpells.get(nombreIcono));
                listaSpell.put(iD, spellRecursos);

                System.out.println(" iD :          " + iD);
                System.out.println(" Icono       : " + nombreIcono);
            }
            System.out.println();
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de Spells."+e); }
    }
}
