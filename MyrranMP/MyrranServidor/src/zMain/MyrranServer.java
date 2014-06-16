package zMain;

import Controller.Controlador;
import DB.Datos.BDebuff.BDebuffLocalDB;
import DB.Datos.Spell.SpellLocalDB;
import Model.GameState.Mundo;

public class MyrranServer
{
    public static void main (String[] arg)
    {
        System.out.println(BDebuffLocalDB.get().listaDeBDebuffs);
        System.out.println(SpellLocalDB.get().listaDeSpells);


        Controlador controlador = new Controlador(new Mundo());
        while (true) {}
    }
}
