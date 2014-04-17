package Controller;// Created by Hanto on 09/04/2014.

import zMain.MiscData;

public class Updater implements Runnable
{
    Controlador controlador;

    public Updater(Controlador controlador)
    {
        this.controlador = controlador;
        new Thread(this).start();
    }

    @Override public void run()
    {
        while (true)
        {
            mundoUpdate();
            netUpdate();
            try { Thread.sleep((int)MiscData.NETWORK_Update_Time); }
            catch (InterruptedException e) { System.out.println("ERROR: Updateando la red: "+e); return; }
        }
    }

    public void mundoUpdate()   { controlador.mundoUpdate(); }
    public void netUpdate()     { controlador.netUpdater(); }
}
