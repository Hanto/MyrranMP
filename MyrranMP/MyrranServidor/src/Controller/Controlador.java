package Controller;// Created by Hanto on 07/04/2014.

import Model.Mobiles.Mundo;
import View.Vista;

public class Controlador
{
    protected Servidor servidor = new Servidor(this);                  //Input principal de la simulacion
    protected Updater updater;

    protected Mundo mundo;
    protected Vista vista;


    public Controlador (Mundo mundo)
    {
        this.mundo = mundo;
        vista = new Vista(this, mundo);
        updater = new Updater(this, mundo);
    }

    public void enviarACliente(int connectionID, Object obj)            { servidor.enviarACliente(connectionID, obj); }

    public void añadirPC (int connectionID, float x, float y)           { mundo.añadirPC(connectionID, x, y); }
    public void moverPC (int connectionID, float x, float y)            { mundo.moverPC(connectionID, x, y); }
    public void eliminarPC (int connectionID)                           { mundo.eliminarPC(connectionID); }

    public void cambiarAnimacionPC(int connectionID, int numAnimacion)
    {   mundo.getPC(connectionID).setAnimacion(numAnimacion); }

    public void castear(int connectionID, boolean castear, int targetX, int targetY)
    {   mundo.getPC(connectionID).setCastear(targetX, targetY); }


    public void netUpdater ()                                           { vista.netUpdate(); }
}
