package Controller;// Created by Hanto on 07/04/2014.

import Controller.Network.DTO;
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

        DTO.register(this);
        this.start();

        this.addListener(new Listener.ThreadedListener(new Listener()
        {
            public void connected (Connection con)              { controlador.añadirPC(con.getID(),0f,0f); }
            public void disconnected (Connection con)           { controlador.eliminarPC(con.getID()); }
            public void received (Connection con, Object obj)   { procesarMensajeCliente(con, obj); }
        }));

        try { this.bind(DTO.puerto); }
        catch (Exception e) { System.out.println("ERROR: Inicio Servidor: "+e); }
    }

    public void procesarMensajeCliente(Connection con, Object obj)
    {
        if (obj instanceof DTO.MoverPC)
        {
            int conID = con.getID();
            float x = ((DTO.MoverPC) obj).x;
            float y = ((DTO.MoverPC) obj).y;

            controlador.moverPC(conID, x, y);
        }

        if (obj instanceof DTO.CambiarAnimacionPC)
        {
            int conID = ((DTO.CambiarAnimacionPC) obj).connectionID;
            int numAnimacion = ((DTO.CambiarAnimacionPC) obj).numAnimacion;
            controlador.cambiarAnimacionPC(conID, numAnimacion);
        }
    }

    @Override public void enviarACliente(int connectionID, Object obj)
    {   this.sendToTCP(connectionID, obj); }

    @Override public void enviarATodosClientes(Object obj)
    {   this.sendToAllTCP(obj); }

    @Override public void enviarATodosClientesMenosUno(int connectionID, Object obj)
    {   this.sendToAllExceptTCP(connectionID, obj);}
}
