package zMain;

import Controller.Controlador;
import Model.GameState.Mundo;

public class MyrranServer
{
    public static void main (String[] arg)
    {
        Controlador controlador = new Controlador(new Mundo());
        while (true)
        {

        }
    }
}
