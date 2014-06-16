package DB.Datos.TipoBdDebuff;// Created by Hanto on 10/06/2014.

import Interfaces.BDebuff.TipoBDebuffI;
import Model.Classes.Skill.BDebuff.TipoBDebuff;
import Model.Classes.Skill.BDebuff.TipoBDebuffFactory;

import java.util.HashMap;
import java.util.Map;

public class TipoBDebuffLocalDB
{
    private static class Singleton          { private static final TipoBDebuffLocalDB get = new TipoBDebuffLocalDB(); }
    public static TipoBDebuffLocalDB get()  { return Singleton.get; }

    public Map<String, TipoBDebuffI>listaDeTipoBDebuffs = new HashMap<>();

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
    }
}
