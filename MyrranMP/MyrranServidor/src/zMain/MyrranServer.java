package zMain;

import Controller.Controlador;
import DB.Datos.BDebuff.BDebuffLocalDB;
import DB.Datos.Spell.SpellLocalDB;
import DB.Datos.Terreno.TerrenoLocalDB;
import DB.Datos.TipoBDebuff.TipoBDebuffLocalDB;
import DB.Datos.TipoSpell.TipoSpellLocalDB;
import Model.GameState.Mundo;

public class MyrranServer
{
    public static void main (String[] arg)
    {
        TipoBDebuffLocalDB.get();
        BDebuffLocalDB.get();
        TipoSpellLocalDB.get();
        SpellLocalDB.get();
        TerrenoLocalDB.get();

        Controlador controlador = new Controlador(new Mundo());
        while (true) {}
    }
}
