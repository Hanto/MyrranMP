package Controller.Network;// Created by Hanto on 09/04/2014.

import Controller.ControladorI;
import zMain.MiscData;

public class NetUpdater implements Runnable
{
    ControladorI controlador;

    public NetUpdater(ControladorI controlador)
    {
        this.controlador = controlador;
        new Thread(this).start();
    }

    @Override public void run()
    {
        while (true)
        {
            update();
            try { Thread.sleep(MiscData.NETWORK_Update_Time); }
            catch (InterruptedException e) { System.out.println("ERROR: Updateando la red: "+e); return; }
        }
    }

    public void update()    { controlador.netUpdater(); }
}
