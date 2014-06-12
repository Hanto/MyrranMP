package Controller;// Created by Hanto on 09/04/2014.

import Data.MiscData;
import Model.GameState.Mundo;
import Model.Classes.Mobiles.PC;

import java.util.Iterator;

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
            mundoUpdate(MiscData.SERVIDOR_Delta_Time);
            netUpdate();
            try { Thread.sleep(MiscData.NETWORK_Update_Time); }
            catch (InterruptedException e) { System.out.println("ERROR: Updateando la red: "+e); return; }
        }
    }

    public void netUpdate()
    {   controlador.netUpdater(); }

    public void mundoUpdate(float delta)
    {
        //actualizar PCs
        Iterator<PC> iteratorPCs = mundo.getIteratorListaPlayers();
        while (iteratorPCs.hasNext())
        {   iteratorPCs.next().actualizar(delta); }
    }
}
