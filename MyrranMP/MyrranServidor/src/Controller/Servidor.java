package Controller;// Created by Hanto on 07/04/2014.

import Controller.Network.Net;
import Controller.Network.NetServer;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class Servidor extends Server implements NetServer
{
    public ControladorI controlador;

    public Servidor (ControladorI controladorI)
    {
        controlador = controladorI;

        Net.register(this);
        this.start();

        this.addListener(new Listener.ThreadedListener(new Listener()
        {
            public void connected (Connection con)              { controlador.añadirPC(con.getID(),0f,0f); }
            public void disconnected (Connection con)           { controlador.eliminarPC(con.getID()); }
            public void received (Connection con, Object obj)   { procesarMensajeCliente(con, obj); }
        }));

        try { this.bind(Net.puerto); }
        catch (Exception e) { System.out.println("ERROR: Inicio Servidor: "+e); }
    }

    public void procesarMensajeCliente(Connection con, Object obj)
    {
        if (obj instanceof Net.MoverPC)
        {
            int conID = con.getID();
            float x = ((Net.MoverPC) obj).x;
            float y = ((Net.MoverPC) obj).y;

            controlador.moverPC(conID, x, y);
        }
    }

    @Override public void enviarACliente(int connectionID, Object obj)
    {   this.sendToTCP(connectionID, obj); }

    @Override public void enviarATodosClientes(Object obj)
    {   this.sendToAllTCP(obj); }

    @Override public void enviarATodosClientesMenosUno(int connectionID, Object obj)
    {   this.sendToAllExceptTCP(connectionID, obj);}
}
