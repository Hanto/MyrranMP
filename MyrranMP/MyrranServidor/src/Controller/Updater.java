package Controller;// Created by Hanto on 09/04/2014.

import Data.MiscData;
import Model.Classes.Mobiles.Mundo;
import Model.Classes.Mobiles.PC;

public class Updater implements Runnable
{
    private Controlador controlador;
    private Mundo mundo;

    public Updater(Controlador controlador, Mundo mundo)
    {
        this.controlador = controlador;
        this.mundo = mundo;

        new Thread(this).start();
    }

    @Override public void run()
    {
        while (true)
        {
            mundoUpdate();
            netUpdate();
            try { Thread.sleep((int) MiscData.NETWORK_Update_Time); }
            catch (InterruptedException e) { System.out.println("ERROR: Updateando la red: "+e); return; }
        }
    }

    public void netUpdate()
    {   controlador.netUpdater(); }

    public void mundoUpdate()
    {
        //actualizar PCs
        for (PC players: mundo.listaPlayers)
        {   players.actualizar(); }
    }
}
