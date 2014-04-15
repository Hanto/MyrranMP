package zMain;

import Controller.Controlador;
import Model.Mobiles.MundoModel;

public class MyrranServer
{
    public static void main (String[] arg)
    {
        Controlador controlador = new Controlador(new MundoModel());
        while (true)
        {

        }
    }
}
