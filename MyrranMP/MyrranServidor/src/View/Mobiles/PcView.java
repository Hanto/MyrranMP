package View.Mobiles;// Created by Hanto on 07/04/2014.

import Controller.Controlador;
import Data.MiscData;
import Interfaces.MobPC;
import Model.DTO.MapaDTO;
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

    private List<MobPC> listaPCsCercanos = new ArrayList<>();

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
        mundo.mapa.añadirObservador(this);
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
        for (MobPC PCCercanos : listaPCsCercanos)
            controlador.enviarACliente(PCCercanos.getConnectionID(), obj);
    }

    public void quienMeVe()
    {
        for (PcView pcCercanos : vista.listaPcViews)
        {
            MobPC PCCercano = pcCercanos.PC;

            if (PCCercano.getConnectionID() != PC.getConnectionID())
            {
                if (Math.abs(PCCercano.getX()- PC.getX()) <=  1.5*MiscData.GDX_Window_Horizontal_Resolution/2 &&
                    Math.abs(PCCercano.getY()- PC.getY()) <=  1.5*MiscData.GDX_Window_Vertical_Resolution/2     )
                {
                    añadirPCVisible(pcCercanos);
                    pcCercanos.añadirPCVisible(this);
                }
                else
                {
                    eliminarPCVisible(pcCercanos);
                    pcCercanos.eliminarPCVisible(this);
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

    public void añadirPCVisible (PcView pcview)
    {
        if (!listaPCsCercanos.contains(pcview.PC))
        {
            listaPCsCercanos.add(pcview.PC);
            NetDTO.AñadirPC añadirPC = new NetDTO.AñadirPC(pcview.PC, pcview.PC.getNumAnimacion());
            controlador.enviarACliente(PC.getConnectionID(), añadirPC);
        }
    }

    public void eliminarPCVisible (PcView pcView)
    {
        if (listaPCsCercanos.contains(pcView.PC))
        {
            listaPCsCercanos.remove(pcView.PC);
            NetDTO.EliminarPC eliminarPC = new NetDTO.EliminarPC(pcView.PC);
            controlador.enviarACliente(PC.getConnectionID(), eliminarPC);
        }
    }

    public void eliminar()
    {
        NetDTO.EliminarPC eliminarPC = new NetDTO.EliminarPC(PC);
        actualizarPlayersCercanos(eliminarPC);

        mundo.mapa.eliminarObservador(this);
        PC.eliminarObservador(this);
        vista.listaPcViews.remove(this);

    }

    public void cambioTerreno (int x, int y, int numCapa, int iDTerreno)
    {
        NetDTO.SetTerreno setTerreno = new NetDTO.SetTerreno(x,y,numCapa,iDTerreno);
        controlador.enviarACliente(PC.getConnectionID(), setTerreno);
    }


    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        //MOBILES:
        if (evt.getNewValue() instanceof PcDTO.PositionPC)
        {
            x = ((PcDTO.PositionPC) evt.getNewValue()).x;
            y = ((PcDTO.PositionPC) evt.getNewValue()).y;
            setPosition(x, y);
        }

        if (evt.getNewValue() instanceof PcDTO.EliminarPC)
        {   eliminar(); }

        if (visible)
        {
            if (evt.getNewValue() instanceof PcDTO.AnimacionPC)
            {
                int numAnimacion = ((PcDTO.AnimacionPC) evt.getNewValue()).numAnimacion;
                setAnimacion(numAnimacion);
            }
        }

        //TERRENOS:
        if (evt.getNewValue() instanceof MapaDTO.SetTerreno)
        {
            int celdaX = ((MapaDTO.SetTerreno) evt.getNewValue()).celdaX;
            int celdaY = ((MapaDTO.SetTerreno) evt.getNewValue()).celdaY;
            int numCapa = ((MapaDTO.SetTerreno) evt.getNewValue()).numCapa;
            int iDTerreno = ((MapaDTO.SetTerreno) evt.getNewValue()).iDTerreno;
            cambioTerreno(celdaX, celdaY, numCapa, iDTerreno);
        }
    }
}
