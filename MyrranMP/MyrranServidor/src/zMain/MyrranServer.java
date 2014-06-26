package zMain;

import Controller.Controlador;
import Core.ConsolaOutputStream;
import DB.Datos.BDebuff.BDebuffLocalDB;
import DB.Datos.Spell.SpellLocalDB;
import DB.Datos.Terreno.TerrenoLocalDB;
import DB.Datos.TipoBDebuff.TipoBDebuffLocalDB;
import DB.Datos.TipoSpell.TipoSpellLocalDB;
import Model.GameState.Consolas;
import Model.GameState.Mundo;

import java.io.PrintStream;

public class MyrranServer
{
    public static void main (String[] arg)
    {
        PrintStream printStream = new PrintStream(new ConsolaOutputStream(Consolas.get().consolaPrincipal));
        System.setOut(printStream);
        System.setErr(printStream);

        TipoBDebuffLocalDB.get();
        BDebuffLocalDB.get();
        TipoSpellLocalDB.get();
        SpellLocalDB.get();
        TerrenoLocalDB.get();

        Controlador controlador = new Controlador(new Mundo());
        while (true) {}
    }
}
