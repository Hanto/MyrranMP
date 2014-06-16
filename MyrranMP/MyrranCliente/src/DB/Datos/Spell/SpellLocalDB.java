package DB.Datos.Spell;// Created by Hanto on 17/04/2014.

import Core.AbrirFichero;
import Data.MiscData;
import Interfaces.Spell.SpellI;
import Interfaces.Spell.TipoSpellI;
import Model.Classes.Skill.Spell.Spell;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellLocalDB
{
    private static class Singleton      { private static final SpellLocalDB get = new SpellLocalDB(); }
    public static SpellLocalDB get()    { return Singleton.get; }

    public Map<String, SpellI> listaDeSpells = new HashMap<>();

    private SpellLocalDB()
    {   cargarDatos(); }

    public void cargarDatos()
    {
        System.out.println("[CARGANDO SPELLS]:");
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero(MiscData.RECURSOS_XML+ MiscData.XML_DataSpells);

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren("Spell");

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);

                String iD           = nodo.getChildText("iD");
                String nombre       = nodo.getChildText("nombre");
                String descripcion  = nodo.getChildText("descripcion");
                String tipoSpell    = nodo.getChildText("tipoSpell");

                TipoSpellI tipoSpellI =  DB.DAO.tipoSpellDAOFactory.getTipoSpellDAO().getTipoSpell(tipoSpell);
                SpellI spell = new Spell(tipoSpellI);
                spell.setID(iD);
                spell.setNombre(nombre);
                spell.setDescripcion(descripcion);

                System.out.println(" iD :           " + iD);
                System.out.println(" nombre:        " + nombre);
                System.out.println(" Descripcion:   " + descripcion);
                System.out.println(" TipoSpell:     " + tipoSpell);

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

                    spell.skillStats()[numStat].setStat(nombreStat, valorBase);
                    if (isMejorable) spell.skillStats()[numStat].setTalentos(talentoMaximo, costeTalento, bonoTalento);
                    else spell.skillStats()[numStat].setIsMejorable(isMejorable);

                    System.out.println(" numStat:       " + numStat);
                    System.out.println(" nombreStat:    " + nombreStat);
                    System.out.println(" valorBase:     " + valorBase);
                    System.out.println(" isMejorable:   " + isMejorable);

                    if (isMejorable)
                    {
                        System.out.println(" talentoMaximo: " + talentoMaximo);
                        System.out.println(" costeTalento:  " + costeTalento);
                        System.out.println(" bonoTalento:   " + bonoTalento);
                    }
                    System.out.println();
                }

                listaDeSpells.put(spell.getID(), spell);
            }
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de "+MiscData.XML_DataSpells+": "+e); }
    }
}
