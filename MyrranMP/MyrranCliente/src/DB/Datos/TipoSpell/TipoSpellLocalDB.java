package DB.Datos.TipoSpell;// Created by Hanto on 17/04/2014.

import Core.AbrirFichero;
import Core.SkillStat;
import Data.MiscData;
import Interfaces.Spell.TipoSpellI;
import Model.Classes.Skill.Spell.TipoSpell;
import Model.Classes.Skill.Spell.TipoSpellFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TipoSpellLocalDB
{
    private static class Singleton          { private static final TipoSpellLocalDB get = new TipoSpellLocalDB(); }
    public static TipoSpellLocalDB get()    { return Singleton.get; }

    public Map<String, TipoSpellI>  listaDeTipoSpells = new HashMap<>();

    private TipoSpellLocalDB()
    {   cargarDatos(); }

    public void cargarDatos()
    {
        for (TipoSpellFactory tipoSpellFactory: TipoSpellFactory.values())
        {
            TipoSpell tipoSpell = tipoSpellFactory.nuevo();
            tipoSpell.setID(tipoSpellFactory.name());
            listaDeTipoSpells.put(tipoSpell.getID(), tipoSpell);
        }

        System.out.println("[CARGANDO TIPOSPELLS]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero(MiscData.RECURSOS_XML + MiscData.XML_DataTipoSpells);

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren("TipoSpell");

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);

                String iD           = nodo.getChildText("iD");
                String nombre       = nodo.getChildText("nombre");
                String descripcion  = nodo.getChildText("descripcion");

                TipoSpellI tipoSpell =  listaDeTipoSpells.get(iD);
                tipoSpell.setID(iD);
                tipoSpell.setNombre(nombre);
                tipoSpell.setDescripcion(descripcion);

                System.out.println(" iD:            " + iD);
                System.out.println(" nombre:        " + nombre);
                System.out.println(" Descripcion:   " + descripcion);

                Element skillStats = nodo.getChild("SkillStats");
                List listaStats= skillStats.getChildren("Stat");

                for (int j = 0; j < listaStats.size(); j++)
                {
                    Element stat = (Element) listaStats.get(j);

                    int numStat         = Integer.parseInt(stat.getChildText("numStat"));
                    String nombreStat   = stat.getChildText("nombre");
                    float valorBase     = Float.parseFloat(stat.getChildText("valorBase"));
                    boolean isMejorable = Boolean.parseBoolean(stat.getChildText("isMejorable"));
                    int talentoMaximo   = Integer.parseInt(stat.getChildText("talentoMaximo"));
                    int costeTalento    = Integer.parseInt(stat.getChildText("costeTalento"));
                    int bonoTalento     = Integer.parseInt(stat.getChildText("bonoTalento"));

                    tipoSpell.skillStats()[numStat] = new SkillStat(nombreStat, valorBase);
                    if (isMejorable) tipoSpell.skillStats()[numStat].setTalentos(talentoMaximo, costeTalento, bonoTalento);
                    else tipoSpell.skillStats()[numStat].setIsMejorable(isMejorable);

                    System.out.println("\n  numStat:      " + numStat);
                    System.out.println("  nombreStat:   " + nombreStat);
                    System.out.println("  valorBase:    " + valorBase);
                    System.out.println("  isMejorable:  " + isMejorable);

                    if (isMejorable)
                    {
                        System.out.println("  talentoMaximo:" + talentoMaximo);
                        System.out.println("  costeTalento: " + costeTalento);
                        System.out.println("  bonoTalento:  " + bonoTalento);
                    }
                    System.out.println();
                }

                listaDeTipoSpells.put(tipoSpell.getID(), tipoSpell);
            }
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de "+MiscData.XML_DataTipoSpells+": "+e); }
    }
}
