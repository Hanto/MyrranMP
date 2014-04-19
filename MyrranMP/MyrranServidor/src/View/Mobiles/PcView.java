package View.Mobiles;// Created by Hanto on 07/04/2014.

import Controller.Controlador;
import Data.MiscData;
import Model.DTO.NetDTO;
import Model.DTO.PcDTO;
import Model.Mobiles.Mundo;
import Model.Mobiles.PC;
import View.Vista;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class PcView implements PropertyChangeListener
{
    public PC PC;
    public Vista vista;
    public Mundo mundo;
    public Controlador controlador;

    public List<PC> listaPCsCercanos = new ArrayList<>();

    public boolean visible = false;

    public int connectionID;
    public float x;
    public float y;
    public boolean positionChanged = false;
    public int numAnimacion = 0;

    public PcView(PC PC, Vista vista)
    {
        this.PC = PC;
        this.vista = vista;
        this.controlador = vista.controlador;
        mundo = vista.mundo;

        connectionID = PC.getConnectionID();
        x = PC.getX();
        y = PC.getY();

        vista.listaPcViews.add(this);
        PC.añadirObservador(this);
        PC.eliminarObservador(vista);

        NetDTO.ActualizarPlayer actualizarPlayer = new NetDTO.ActualizarPlayer(PC);
        controlador.enviarACliente(connectionID, actualizarPlayer);
    }

    public void netUpdate()
    {
        if (visible)
        {
            if (positionChanged)
            {
                NetDTO.MoverPC moverPC = new NetDTO.MoverPC(connectionID, x, y);
                actualizarPlayersCercanos(moverPC);
            }
            positionChanged = false;
        }
    }

    public void actualizarPlayersCercanos (Object obj)
    {
        for (PC PCCercanos : listaPCsCercanos)
            controlador.enviarACliente(PCCercanos.getConnectionID(), obj);
    }

    public void quienMeVe()
    {
        for (PcView pcCercanos : vista.listaPcViews)
        {
            PC PCCercano = pcCercanos.PC;

            if (PCCercano.getConnectionID() != PC.getConnectionID())
            {
                if (Math.abs(PCCercano.getX()- PC.getX()) <=  MiscData.GDX_Window_Horizontal_Resolution/4 &&
                    Math.abs(PCCercano.getY()- PC.getY()) <= MiscData.GDX_Window_Vertical_Resolution/4     )
                {
                    if (!listaPCsCercanos.contains(PCCercano))
                    {
                        listaPCsCercanos.add(PCCercano);
                        NetDTO.AñadirPC añadirPC = new NetDTO.AñadirPC(PCCercano, PCCercano.getNumAnimacion());
                        controlador.enviarACliente(PC.getConnectionID(), añadirPC);
                        System.out.println("Añadir PC: ["+ PC.getConnectionID()+"]");

                        pcCercanos.listaPCsCercanos.add(PC);
                        añadirPC = new NetDTO.AñadirPC(PC, PC.getNumAnimacion());
                        controlador.enviarACliente(PCCercano.getConnectionID(), añadirPC);
                    }
                }
                else
                {
                    if (listaPCsCercanos.contains(PCCercano))
                    {
                        listaPCsCercanos.remove(PCCercano);
                        NetDTO.EliminarPC eliminarPC = new NetDTO.EliminarPC(PCCercano);
                        controlador.enviarACliente(PC.getConnectionID(), eliminarPC);
                        System.out.println("Eliminar PC: ["+eliminarPC.connectionID+"]");

                        pcCercanos.listaPCsCercanos.add(PC);
                        eliminarPC = new NetDTO.EliminarPC(PC);
                        controlador.enviarACliente(PCCercano.getConnectionID(), eliminarPC);
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
        if (evt.getNewValue() instanceof PcDTO.PositionPC)
        {
            x = ((PcDTO.PositionPC) evt.getNewValue()).x;
            y = ((PcDTO.PositionPC) evt.getNewValue()).y;
            setPosition(x, y);
        }

        if (evt.getNewValue() instanceof PcDTO.EliminarPC)
        {
            PC PC = ((PcDTO.EliminarPC) evt.getNewValue()).PC;
            NetDTO.EliminarPC eliminarPC = new NetDTO.EliminarPC(PC);
            PC.eliminarObservador(this);
            vista.listaPcViews.remove(this);
            actualizarPlayersCercanos(eliminarPC);
        }

        if (visible)
        {
            if (evt.getNewValue() instanceof PcDTO.AnimacionPC)
            {
                int numAnimacion = ((PcDTO.AnimacionPC) evt.getNewValue()).numAnimacion;
                setAnimacion(numAnimacion);
            }
        }
    }
}
