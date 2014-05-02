package zMain;

import Controller.Controlador;
import Model.Classes.Mobiles.Mundo;

public class MyrranServer
{
    public static void main (String[] arg)
    {
        LoadGameData.cargarTodo();

        Controlador controlador = new Controlador(new Mundo());
        while (true)
        {

        }
    }
}
