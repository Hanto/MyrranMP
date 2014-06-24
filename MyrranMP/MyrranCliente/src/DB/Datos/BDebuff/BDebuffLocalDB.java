package DB.Datos.BDebuff;// Created by Hanto on 16/06/2014.

import Core.AbrirFichero;
import DB.DAO;
import Data.MiscData;
import Interfaces.BDebuff.BDebuffI;
import Interfaces.BDebuff.TipoBDebuffI;
import Model.Classes.Skill.BDebuff.BDebuff;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BDebuffLocalDB
{
    private static class Singleton      { private static final BDebuffLocalDB get = new BDebuffLocalDB(); }
    public static BDebuffLocalDB get()  { return Singleton.get; }

    public Map<String, BDebuffI> listaDeBDebuffs = new HashMap<>();

    private BDebuffLocalDB()
    {   cargarDatos(); }

    public void cargarDatos()
    {
        SAXBuilder builder = new SAXBuilder();
        InputStream fichero = AbrirFichero.abrirFichero(MiscData.RECURSOS_XML + MiscData.XML_DataBDebuffs);

        try
        {
            Document documento = builder.build(fichero);
            Element rootNode = documento.getRootElement();
            List listaNodos = rootNode.getChildren("BDebuff");

            for (int i = 0; i < listaNodos.size(); i++)
            {
                Element nodo = (Element) listaNodos.get(i);

                String iD           = nodo.getChildText("iD");
                String nombre       = nodo.getChildText("nombre");
                String descripcion  = nodo.getChildText("descripcion");
                boolean isDebuff    = Boolean.parseBoolean(nodo.getChildText("isDebuff"));
                byte stacksMaximos  = Byte.parseByte(nodo.getChildText("stacksMaximos"));
                String tipoBDebuff  = nodo.getChildText("tipoBDebuff");

                TipoBDebuffI tipoBDebuffI =  DAO.tipoBDebuffDAOFactory.getTipoBDebuffDAO().getTipoBDebuff(tipoBDebuff);
                BDebuffI debuff = new BDebuff(tipoBDebuffI);
                debuff.setID(iD);
                debuff.setNombre(nombre);
                debuff.setDescripcion(descripcion);
                debuff.setIsDebuff(isDebuff);
                debuff.setStacksMaximos(stacksMaximos);

                System.out.println("[DEBUFF]:");
                System.out.println(" iD :           " + iD);
                System.out.println(" nombre:        " + nombre);
                System.out.println(" Descripcion:   " + descripcion);
                System.out.println(" isDebuff:      " + isDebuff);
                System.out.println(" stacksMaximos: " + stacksMaximos);
                System.out.println(" TipoSpell:     " + tipoBDebuff);

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
                    float bonoTalento   = Float.parseFloat(stat.getChildText("bonoTalento"));

                    debuff.getSkillStat(numStat).setStat(nombreStat, valorBase);
                    if (isMejorable) debuff.getSkillStat(numStat).setTalentos(talentoMaximo, costeTalento, bonoTalento);
                    else debuff.getSkillStat(numStat).setIsMejorable(isMejorable);

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

                listaDeBDebuffs.put(debuff.getID(), debuff);
            }
        }
        catch (Exception e) { System.out.println("ERROR: con el fichero XML de datos de "+MiscData.XML_DataBDebuffs+": "+e); }
    }
}
