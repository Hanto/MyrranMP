package Controller;// Created by Hanto on 08/04/2014.

import DTO.NetDTO;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Cliente extends Client
{
    public Controlador controlador;
    public String host;

    public Cliente (Controlador controlador)
    {
        super(8*1024, 32*1024);
        this.controlador = controlador;

        NetDTO.register(this);
        this.start();

        //Para activar el log completo de mensajes:
        //Log.TRACE();

        this.addListener(new Listener.ThreadedListener(new Listener()
        {
            public void connected (Connection con)   { }
            public void received (Connection con, Object obj) { procesarMensajeServidor(con, obj);}
            public void disconnected (Connection con) { }
        }));

        host = "localhost"; //(String) JOptionPane.showInputDialog(null, "Host:", "Connect to server", JOptionPane.QUESTION_MESSAGE, null, null, "localhost");

        try { this.connect(NetDTO.timeout, host, NetDTO.puerto); }
        catch (Exception IOException) { System.out.println("ERROR: Imposible conectar cliente: "+IOException); }
    }

    private void procesarMensajeServidor (Connection con, Object obj)
    {
        if (obj instanceof NetDTO.ActualizarPlayer)
        {

            controlador.actualizarPlayer((NetDTO.ActualizarPlayer)obj);
        }

        if (obj instanceof NetDTO.CambiarPosicionPC)
        {
            int conID = ((NetDTO.CambiarPosicionPC) obj).connectionID;
            float x = ((NetDTO.CambiarPosicionPC) obj).x;
            float y = ((NetDTO.CambiarPosicionPC) obj).y;

            controlador.moverPC(conID, x, y);
        }

        if (obj instanceof NetDTO.CambiarAnimacionPC)
        {
            int conID = ((NetDTO.CambiarAnimacionPC) obj).connectionID;
            int numAnimacion = ((NetDTO.CambiarAnimacionPC) obj).numAnimacion;

            controlador.cambiarAnimacionPC(conID, numAnimacion);
        }

        if (obj instanceof NetDTO.AñadirPC)
        {
            int conID = ((NetDTO.AñadirPC) obj).connectionID;
            float x = ((NetDTO.AñadirPC) obj).x;
            float y = ((NetDTO.AñadirPC) obj).y;
            int numAnimacion = ((NetDTO.AñadirPC) obj).numAnimacion;
            System.out.println("recibido añadir Player con ID: "+conID);
            controlador.añadirPC(conID, x, y, numAnimacion);
        }

        if (obj instanceof NetDTO.EliminarPC)
        {
            int conID = ((NetDTO.EliminarPC) obj).connectionID;
            controlador.eliminarPC(conID);
        }

        if (obj instanceof NetDTO.SetTerreno)
        {
            int celdaX = ((NetDTO.SetTerreno) obj).celdaX;
            int celdaY = ((NetDTO.SetTerreno) obj).celdaY;
            int numCapa = ((NetDTO.SetTerreno) obj).numCapa;
            int iDTerreno = ((NetDTO.SetTerreno) obj).iDTerreno;
            controlador.setTerreno(celdaX, celdaY, numCapa, iDTerreno);
        }
        if (obj instanceof NetDTO.ActualizarMapa)
        {
            controlador.actualizarMapa((NetDTO.ActualizarMapa)obj);
        }
    }

    public void enviarAServidor(Object obj)
    {   this.sendTCP(obj); }
}
