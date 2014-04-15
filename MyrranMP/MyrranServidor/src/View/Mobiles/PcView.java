package View.Mobiles;// Created by Hanto on 07/04/2014.

import Controller.Controlador;
import Model.DTO.NetDTO;
import Model.DTO.PcDTO;
import Model.Mobiles.MundoModel;
import Model.Mobiles.PcModel;
import View.Vista;
import zMain.MiscData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class PcView implements PropertyChangeListener
{
    public PcModel pcModel;
    public Vista vista;
    public MundoModel mundoModel;
    public Controlador controlador;

    private List<PcModel> listaPCsCercanos = new ArrayList<>();

    public boolean visible = false;

    public int connectionID;
    public float x;
    public float y;
    public boolean positionChanged = false;
    public int numAnimacion = 0;

    public PcView(PcModel pcModel, Vista vista)
    {
        this.pcModel = pcModel;
        this.vista = vista;
        this.controlador = vista.controlador;
        mundoModel = vista.mundoModel;

        connectionID = pcModel.getConnectionID();
        x = pcModel.getX();
        y = pcModel.getY();

        vista.listaPcViews.add(this);
        pcModel.añadirObservador(this);
        pcModel.eliminarObservador(vista);

        NetDTO.ActualizarPlayer actualizarPlayer = new NetDTO.ActualizarPlayer(pcModel);
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
                System.out.println(x+"-"+y);
            }
            positionChanged = false;
        }
    }

    public void actualizarPlayersCercanos (Object obj)
    {
        for (PcModel pcModelCercanos : listaPCsCercanos)
            controlador.enviarACliente(pcModelCercanos.getConnectionID(), obj);
    }

    public void quienMeVe()
    {
        for (PcView pcCercanos : vista.listaPcViews)
        {
            PcModel pcModelCercano = pcCercanos.pcModel;

            if (pcModelCercano.getConnectionID() != pcModel.getConnectionID())
            {
                if (Math.abs(pcModelCercano.getX()- pcModel.getX()) <= 2*MiscData.GDX_Window_Horizontal_Resolution &&
                    Math.abs(pcModelCercano.getY()- pcModel.getY()) <= 2*MiscData.GDX_Window_Vertical_Resolution     )
                {
                    if (!listaPCsCercanos.contains(pcModelCercano))
                    {
                        listaPCsCercanos.add(pcModelCercano);
                        System.out.println("Añadido PcModel ID: "+ pcModel.getConnectionID());
                        NetDTO.AñadirPC añadirPC = new NetDTO.AñadirPC(pcModel);
                        controlador.enviarACliente(pcModelCercano.getConnectionID(), añadirPC);
                    }
                }
                else
                {
                    if (listaPCsCercanos.contains(pcModelCercano))
                    {
                        listaPCsCercanos.remove(pcModelCercano);
                        NetDTO.EliminarPC eliminarPC = new NetDTO.EliminarPC(pcModel);
                        controlador.enviarACliente(pcModelCercano.getConnectionID(), eliminarPC);
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
            PcModel pcModel = ((PcDTO.EliminarPC) evt.getNewValue()).pcModel;
            NetDTO.EliminarPC eliminarPC = new NetDTO.EliminarPC(pcModel);
            pcModel.eliminarObservador(this);
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
