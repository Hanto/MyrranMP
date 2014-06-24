package DB.Datos.TipoBDebuff;// Created by Hanto on 10/06/2014.

import Core.AbrirFichero;
import Core.SkillStat;
import Data.MiscData;
import Interfaces.BDebuff.TipoBDebuffI;
import Model.Classes.Skill.BDebuff.TipoBDebuff;
import Model.Classes.Skill.BDebuff.TipoBDebuffFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TipoBDebuffLocalDB
{
    private static class Singleton          { private static final TipoBDebuffLocalDB get = new TipoBDebuffLocalDB(); }
    public static TipoBDebuffLocalDB get()  { return Singleton.get; }

    public Map<String, TipoBDebuffI> listaDeTipoBDebuffs = new HashMap<>();

    private TipoBDebuffLocalDB()
    {   cargarDatos(); }

    public void cargarDatos()
    {
        for (TipoBDebuffFactory tipoBDebuffFactory: TipoBDebuffFactory.values())
        {
            TipoBDebuff tipoBDebuff = tipoBDebuffFactory.nuevo();
            tipoBDebuff.setID(tipoBDebuffFactory.name());
            listaDeTipoBDebuffs.put(tipoBDebuff.getID(), tipoBDebuff);
        }

        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero(MiscData.RECURSOS_XML + MiscData.XML_DataTipoBDebuffs);

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren("TipoBDebuff");

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);

                String iD           = nodo.getChildText("iD");
                String nombre       = nodo.getChildText("nombre");
                String descripcion  = nodo.getChildText("descripcion");
                boolean isDebuff    = Boolean.parseBoolean(nodo.getChildText("isDebuff"));
                byte stacksMaximos  = Byte.parseByte(nodo.getChildText("stacksMaximos"));

                TipoBDebuffI tipoDebuff =  listaDeTipoBDebuffs.get(iD);
                tipoDebuff.setID(iD);
                tipoDebuff.setNombre(nombre);
                tipoDebuff.setDescripcion(descripcion);
                tipoDebuff.setIsDebuff(isDebuff);
                tipoDebuff.setStacksMaximos(stacksMaximos);

                System.out.println("[TIPODEBUFF]:");
                System.out.println(" iD :           " + iD);
                System.out.println(" nombre:        " + nombre);
                System.out.println(" Descripcion:   " + descripcion);
                System.out.println(" isDebuff:      " + isDebuff);
                System.out.println(" stacksMaximos: " + stacksMaximos);

                Element skillStats = nodo.getChild("SkillStats");
                List listaStats= skillStats.getChildren("Stat");

                for (int j = 0; j < listaStats.size(); j++)
                {
                    if (listaStats.size() < tipoDebuff.getNumSkillStats()) System.out.println("ERROR: Faltan SkillStats por Definir.");

                    Element stat = (Element) listaStats.get(j);

                    int numStat         = Integer.parseInt(stat.getChildText("numStat"));
                    String nombreStat   = stat.getChildText("nombre");
                    float valorBase     = Float.parseFloat(stat.getChildText("valorBase"));
                    boolean isMejorable = Boolean.parseBoolean(stat.getChildText("isMejorable"));
                    int talentoMaximo   = Integer.parseInt(stat.getChildText("talentoMaximo"));
                    int costeTalento    = Integer.parseInt(stat.getChildText("costeTalento"));
                    float bonoTalento   = Float.parseFloat(stat.getChildText("bonoTalento"));

                    tipoDebuff.setSkillStat(new SkillStat(nombreStat, valorBase), numStat);
                    if (isMejorable) tipoDebuff.getSkillStat(numStat).setTalentos(talentoMaximo, costeTalento, bonoTalento);
                    else tipoDebuff.getSkillStat(numStat).setIsMejorable(isMejorable);

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
                }
                System.out.println();

                listaDeTipoBDebuffs.put(tipoDebuff.getID(), tipoDebuff);
            }
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de "+ MiscData.XML_DataTipoBDebuffs+": "+e); }
    }
}
