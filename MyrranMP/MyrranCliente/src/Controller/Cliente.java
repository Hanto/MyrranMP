package Controller;// Created by Hanto on 08/04/2014.

import Controller.Network.DTO;
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

        DTO.register(this);
        this.start();

        this.addListener(new Listener.ThreadedListener(new Listener()
        {
            public void connected (Connection con)   { }
            public void received (Connection con, Object obj) { procesarMensajeServidor(con, obj);}
            public void disconnected (Connection con) { }
        }));

        host = "localhost";
        //(String) JOptionPane.showInputDialog(null, "Host:", "Connect to server", JOptionPane.QUESTION_MESSAGE, null, null, "localhost");

        try { this.connect(DTO.timeout, host, DTO.puerto); }
        catch (Exception IOException) { System.out.println("ERROR: Imposible conectar cliente: "+IOException); }
    }

    public void procesarMensajeServidor (Connection con, Object obj)
    {
        if (obj instanceof DTO.MoverPC)
        {
            int conID = ((DTO.MoverPC) obj).connectionID;
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

        if (obj instanceof DTO.AñadirPC)
        {
            int conID = ((DTO.AñadirPC) obj).connectionID;
            float x = ((DTO.AñadirPC) obj).x;
            float y = ((DTO.AñadirPC) obj).y;
            System.out.println("recibido añadir Player con ID: "+conID);
            controlador.añadirPC(conID, x, y);
        }

        if (obj instanceof DTO.EliminarPC)
        {
            int conID = ((DTO.EliminarPC) obj).connectionID;
            controlador.eliminarPC(conID);
        }
    }

    @Override public void enviarAServidor(Object obj)
    {   this.sendTCP(obj); }
}
