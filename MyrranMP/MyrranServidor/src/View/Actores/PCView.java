package View.Actores;// Created by Hanto on 07/04/2014.

import DTOs.NetDTO;
import Controller.Network.NetServer;
import DTOs.ActorDTO;
import Models.MundoModel;
import Models.PCModel;
import View.Vista;
import zMain.MiscData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class PCView implements PropertyChangeListener
{
    public PCModel pc;
    public Vista vista;
    public MundoModel mundo;
    public NetServer servidor;

    private ArrayList<PCModel>listaPCsCercanos = new ArrayList<>();

    public boolean visible = false;

    public int connectionID;
    public float x;
    public float y;
    public boolean positionChanged = false;
    public int numAnimacion = 0;

    public PCView (PCModel pc, Vista vista)
    {
        this.pc = pc;
        this.vista = vista;
        mundo = vista.mundo;
        servidor = vista.controlador.getNetIO();

        connectionID = pc.getConnectionID();
        x = pc.getX();
        y = pc.getY();

        vista.listaPCViews.add(this);
        pc.añadirObservador(this);
        pc.eliminarObservador(vista);
    }

    public void netUpdate()
    {
        if (visible)
        {
            if (positionChanged)
            {
                NetDTO.MoverPC moverPC = new NetDTO.MoverPC(connectionID, x, y);
                actualizarPlayersCercanos(moverPC);
                System.out.println(x+"-"+y);
            }
            positionChanged = false;
        }
    }

    public void actualizarPlayersCercanos (Object obj)
    {
        for (PCModel pcCercanos: listaPCsCercanos)
            servidor.enviarACliente(pcCercanos.getConnectionID(), obj);
    }

    public void quienMeVe()
    {
        for (PCView pcCercanos : vista.listaPCViews)
        {
            PCModel pcCercano = pcCercanos.pc;

            if (pcCercano.getConnectionID() != pc.getConnectionID())
            {
                if (Math.abs(pcCercano.getX()-pc.getX()) <= 2*MiscData.GDX_Window_Horizontal_Resolution &&
                    Math.abs(pcCercano.getY()-pc.getY()) <= 2*MiscData.GDX_Window_Vertical_Resolution     )
                {
                    if (!listaPCsCercanos.contains(pcCercano))
                    {
                        listaPCsCercanos.add(pcCercano);
                        System.out.println("Añadido PC ID: "+pc.getConnectionID());
                        NetDTO.AñadirPC añadirPC = new NetDTO.AñadirPC(pc);
                        servidor.enviarACliente(pcCercano.getConnectionID(), añadirPC);
                    }
                }
                else
                {
                    if (listaPCsCercanos.contains(pcCercano))
                    {
                        listaPCsCercanos.remove(pcCercano);
                        NetDTO.EliminarPC eliminarPC = new NetDTO.EliminarPC(pc);
                        servidor.enviarACliente(pcCercano.getConnectionID(), eliminarPC);
                    }
                }
            }
        }
        if (listaPCsCercanos.size()>0) visible = true;
        else visible = false;
    }

    public void setPosition (float x, float y)
    {
        this.x = x; this.y = y;
        quienMeVe();
        if (visible) positionChanged = true;
        else positionChanged = false;
    }

    public void setAnimacion(int numAnimacion)
    {
        this.numAnimacion = numAnimacion;
        NetDTO.CambiarAnimacionPC cambiarAnimacionPC = new NetDTO.CambiarAnimacionPC(connectionID, numAnimacion);
        actualizarPlayersCercanos(cambiarAnimacionPC);
        System.out.println("Servidor envia animacion ["+numAnimacion+"]");
    }


    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof ActorDTO.MoverPC)
        {
            x = ((ActorDTO.MoverPC) evt.getNewValue()).x;
            y = ((ActorDTO.MoverPC) evt.getNewValue()).y;
            setPosition(x, y);
        }

        if (evt.getNewValue() instanceof ActorDTO.EliminarPC)
        {
            PCModel pc = ((ActorDTO.EliminarPC) evt.getNewValue()).pc;
            NetDTO.EliminarPC eliminarPC = new NetDTO.EliminarPC(pc);
            pc.eliminarObservador(this);
            vista.listaPCViews.remove(this);
            actualizarPlayersCercanos(eliminarPC);
        }

        if (visible)
        {
            if (evt.getNewValue() instanceof ActorDTO.CambiarAnimacionPC)
            {
                int numAnimacion = ((ActorDTO.CambiarAnimacionPC) evt.getNewValue()).numAnimacion;
                setAnimacion(numAnimacion);
            }
        }
    }
}
