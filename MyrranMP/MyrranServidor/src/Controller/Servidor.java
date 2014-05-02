package Controller;// Created by Hanto on 07/04/2014.

import Model.DTO.NetDTO;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class Servidor extends Server
{
    public Controlador controlador;

    public Servidor (Controlador control)
    {
        this.controlador = control;

        NetDTO.register(this);
        this.start();

        //Para activar el log completo de mensajes:
        //Log.TRACE();

        this.addListener(new Listener.ThreadedListener(new Listener()
        {
            public void connected (Connection con)              { controlador.añadirPC(con.getID(),0f,0f); }
            public void disconnected (Connection con)           { controlador.eliminarPC(con.getID()); }
            public void received (Connection con, Object obj)   { procesarMensajeCliente(con, obj); }
        }));

        try { this.bind(NetDTO.puerto); }
        catch (Exception e) { System.out.println("ERROR: Inicio Servidor: "+e); }
    }

    private void procesarMensajeCliente(Connection con, Object obj)
    {
        if (obj instanceof NetDTO.MoverPC)
        {
            int conID = con.getID();
            float x = ((NetDTO.MoverPC) obj).x;
            float y = ((NetDTO.MoverPC) obj).y;

            controlador.moverPC(conID, x, y);
        }

        if (obj instanceof NetDTO.CambiarAnimacionPC)
        {
            int conID = ((NetDTO.CambiarAnimacionPC) obj).connectionID;
            int numAnimacion = ((NetDTO.CambiarAnimacionPC) obj).numAnimacion;
            controlador.cambiarAnimacionPC(conID, numAnimacion);
        }

        if (obj instanceof NetDTO.CastearPC)
        {
            int conID = con.getID();
            boolean castear = ((NetDTO.CastearPC) obj).castear;
            int targetX = ((NetDTO.CastearPC) obj).targetX;
            int targetY = ((NetDTO.CastearPC) obj).targetY;
            controlador.castear(conID, castear, targetX, targetY);
        }
    }

    public void enviarACliente(int connectionID, Object obj)
    {   this.sendToTCP(connectionID, obj); }

    public void enviarATodosClientes(Object obj)
    {   this.sendToAllTCP(obj); }

    public void enviarATodosClientesMenosUno(int connectionID, Object obj)
    {   this.sendToAllExceptTCP(connectionID, obj);}
}
