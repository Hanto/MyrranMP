package Controller;// Created by Hanto on 08/04/2014.

import Controller.Network.Net;
import Controller.Network.NetClient;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Cliente extends Client implements NetClient
{
    public ControladorI controlador;
    public String host;

    public Cliente (ControladorI controladorI)
    {
        controlador = controladorI;

        Net.register(this);
        this.start();

        this.addListener(new Listener.ThreadedListener(new Listener()
        {
            public void connected (Connection con)   { }
            public void received (Connection con, Object obj) { procesarMensajeServidor(con, obj);}
            public void disconnected (Connection con) { }
        }));

        host = "localhost";
        //(String) JOptionPane.showInputDialog(null, "Host:", "Connect to server", JOptionPane.QUESTION_MESSAGE, null, null, "localhost");

        try { this.connect(Net.timeout, host, Net.puerto); }
        catch (Exception IOException) { System.out.println("ERROR: Imposible conectar cliente: "+IOException); }
    }

    public void procesarMensajeServidor (Connection con, Object obj)
    {
        if (obj instanceof Net.MoverPC)
        {
            int conID = ((Net.MoverPC) obj).connectionID;
            float x = ((Net.MoverPC) obj).x;
            float y = ((Net.MoverPC) obj).y;

            controlador.moverPC(conID, x, y);
        }

        if (obj instanceof Net.AñadirPC)
        {
            int conID = ((Net.AñadirPC) obj).connectionID;
            float x = ((Net.AñadirPC) obj).x;
            float y = ((Net.AñadirPC) obj).y;
            System.out.println("recibido añadir Player con ID: "+conID);
            controlador.añadirPC(conID, x, y);
        }

        if (obj instanceof Net.EliminarPC)
        {
            int conID = ((Net.EliminarPC) obj).connectionID;
            controlador.eliminarPC(conID);
        }
    }

    @Override public void enviarAServidor(Object obj)
    {   this.sendTCP(obj); }
}
