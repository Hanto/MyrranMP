package View.Classes.Mobiles;// Created by Hanto on 07/04/2014.

import Controller.Controlador;
import DTO.NetDTO;
import Data.Misc.MiscData;
import Interfaces.EntidadesTipos.MobPC;
import Model.Classes.Mobiles.PC;
import Model.GameState.Mundo;
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

    public MapaView mapaView;

    public PcView(PC PC, Vista vista)
    {
        this.PC = PC;
        this.vista = vista;
        this.controlador = vista.controlador;
        mundo = vista.mundo;

        connectionID = PC.getConnectionID();
        x = PC.getX();
        y = PC.getY();

        mundo.getMapa().añadirObservador(this);
        PC.añadirObservador(this);

        NetDTO.ActualizarPlayer actualizarPlayer = new NetDTO.ActualizarPlayer(PC, PC);
        controlador.enviarACliente(connectionID, actualizarPlayer);

        quienMeVe();

        mapaView = new MapaView(PC, mundo, controlador);
    }

    public void netUpdate()
    {
        if (visible)
        {
            if (positionChanged)
            {
                NetDTO.CambiarPosicionPC cambiarPosicionPC = new NetDTO.CambiarPosicionPC(connectionID, x, y);
                actualizarPlayersCercanos(cambiarPosicionPC);
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
                if (Math.abs(PCCercano.getX()- PC.getX()) <=  MiscData.SERVIDOR_DistanciaVisionMobs*MiscData.MAPTILE_Horizontal_Resolution /2 &&
                    Math.abs(PCCercano.getY()- PC.getY()) <=  MiscData.SERVIDOR_DistanciaVisionMobs*MiscData.MAPTILE_Vertical_Resolution /2     )
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

        mapaView.comprobarVistaMapa();
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
            NetDTO.AñadirPC añadirPC = new NetDTO.AñadirPC(pcview.PC);
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

        mundo.getMapa().eliminarObservador(this);
        PC.eliminarObservador(this);
        vista.listaPcViews.remove(this);

    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        //MOBILES:
        if (evt.getNewValue() instanceof NetDTO.CambiarPosicionPC)
        {
            x = ((NetDTO.CambiarPosicionPC) evt.getNewValue()).x;
            y = ((NetDTO.CambiarPosicionPC) evt.getNewValue()).y;
            setPosition(x, y);
        }

        if (evt.getNewValue() instanceof NetDTO.EliminarPC)
        {   eliminar(); }

        if (visible)
        {
            if (evt.getNewValue() instanceof NetDTO.CambiarAnimacionPC)
            {
                int numAnimacion = ((NetDTO.CambiarAnimacionPC) evt.getNewValue()).numAnimacion;
                setAnimacion(numAnimacion);
            }
        }

        //TERRENOS:
        if (evt.getNewValue() instanceof NetDTO.SetTerreno)
        {
            int celdaX = ((NetDTO.SetTerreno) evt.getNewValue()).celdaX;
            int celdaY = ((NetDTO.SetTerreno) evt.getNewValue()).celdaY;
            int numCapa = ((NetDTO.SetTerreno) evt.getNewValue()).numCapa;
            short iDTerreno = ((NetDTO.SetTerreno) evt.getNewValue()).iDTerreno;
            mapaView.cambioTerreno(celdaX, celdaY, numCapa, iDTerreno);
        }
    }
}
