package View.Actores;// Created by Hanto on 07/04/2014.

import Controller.Network.Net;
import Controller.Network.NetServer;
import Modelo.Mobiles.MundoModel;
import Modelo.Mobiles.PCModel;
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
                Net.MoverPC moverPC = new Net.MoverPC(connectionID, x, y);
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
                        Net.AñadirPC añadirPC = new Net.AñadirPC(pc);
                        servidor.enviarACliente(pcCercano.getConnectionID(), añadirPC);
                    }
                }
                else
                {
                    if (listaPCsCercanos.contains(pcCercano))
                    {
                        listaPCsCercanos.remove(pcCercano);
                        Net.EliminarPC eliminarPC = new Net.EliminarPC(pc);
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
        if (visible) { positionChanged = true; }
        else { visible = false; }
    }


    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof Modelo.Mobiles.DTO.PCPosition)
        {
            x = ((Modelo.Mobiles.DTO.PCPosition) evt.getNewValue()).x;
            y = ((Modelo.Mobiles.DTO.PCPosition) evt.getNewValue()).y;
            setPosition(x, y);
        }

        if (evt.getNewValue() instanceof Modelo.Mobiles.DTO.PCEliminar)
        {
            PCModel pc = ((Modelo.Mobiles.DTO.PCEliminar) evt.getNewValue()).pc;
            Net.EliminarPC eliminarPC = new Net.EliminarPC(pc);
            pc.eliminarObservador(this);
            vista.listaPCViews.remove(this);
            actualizarPlayersCercanos(eliminarPC);
        }

        if (visible)
        {

        }
    }
}
