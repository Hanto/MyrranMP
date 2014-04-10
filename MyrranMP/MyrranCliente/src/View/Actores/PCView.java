package View.Actores;// Created by Hanto on 08/04/2014.

import Controller.Input.ControladorCliente;
import Modelo.Mobiles.DTO;
import Modelo.Mobiles.MundoModel;
import Modelo.Mobiles.PCModel;
import View.PantallaJuego;
import View.Vista;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PCView extends Group implements PropertyChangeListener
{
    public PCModel pc;
    public Vista vista;
    public ControladorCliente controlador;
    //Datos derivados:
    public MundoModel mundo;

    public int connectionID;
    public Image actor;

    public PCView (PCModel pc, Vista vista, ControladorCliente controlador)
    {
        this.pc = pc;
        this.vista = vista;
        this.controlador = controlador;
        this.mundo = vista.mundo;

        connectionID = pc.getConnectionID();
        this.setPosition(pc.getX(), pc.getY());

        vista.listaPCViews.add(this);
        pc.eliminarObservador(vista);
        crearActor();
        pc.añadirObservador(this);
    }

    public void crearActor ()
    {
        actor = new Image(PantallaJuego.texture);
        this.addActor(actor);
        vista.stageMundo.addActor(this);
    }

    @Override public void setPosition (float x, float y)
    {
        super.setPosition(x, y);
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof DTO.PCPosition)
        {
            float x = ((DTO.PCPosition) evt.getNewValue()).x;
            float y = ((DTO.PCPosition) evt.getNewValue()).y;
            setPosition(x, y);
        }

        if (evt.getNewValue() instanceof DTO.PCEliminar)
        {
            pc.eliminarObservador(this);
            vista.stageMundo.getRoot().removeActor(this);
            vista.listaPCViews.removeValue(this, true);
        }
    }
}
