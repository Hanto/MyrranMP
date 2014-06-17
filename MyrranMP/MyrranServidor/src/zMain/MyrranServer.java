package zMain;

import Controller.Controlador;
import DB.Datos.BDebuff.BDebuffLocalDB;
import Model.GameState.Mundo;

public class MyrranServer
{
    public static void main (String[] arg)
    {
        System.out.println(BDebuffLocalDB.get().listaDeBDebuffs);
        Controlador controlador = new Controlador(new Mundo());
        while (true) {}
    }
}
