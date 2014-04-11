package View.Actores;// Created by Hanto on 08/04/2014.

import Controller.ControladorCliente;
import DTOs.ActorDTO;
import Modelo.DTO.ClienteDTO;
import Modelo.Mobiles.MundoModelC;
import Models.PCModel;
import View.Graficos.PixiePC;
import View.Vista;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import zMain.MiscData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PCView extends Group implements PropertyChangeListener
{
    public PCModel pc;
    public Vista vista;
    public ControladorCliente controlador;
    //Datos derivados:
    public MundoModelC mundo;

    public int connectionID;
    public PixiePC actor;

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
        pc.añadirObservador(this);

        crearActor();
    }

    public void crearActor ()
    {
        actor = new PixiePC("Golem");
        this.addActor(actor);
        vista.stageMundo.addActor(this);
        actor.setAnimacion(5, false);
    }

    public void eliminar()
    {
        pc.eliminarObservador(this);
        vista.stageMundo.getRoot().removeActor(this);
        vista.listaPCViews.removeValue(this, true);
    }

    public void mover(float x, float y)
    {   this.addAction(Actions.moveTo(x, y, MiscData.NETWORK_Update_Time / 1000f, Interpolation.linear));
        //setPosition(x,y);
    }

    public void setAnimacion (int numAnimacion)
    {   actor.setAnimacion(numAnimacion, false); }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof ActorDTO.MoverPC)
        {
            float x = ((ActorDTO.MoverPC) evt.getNewValue()).x;
            float y = ((ActorDTO.MoverPC) evt.getNewValue()).y;
            mover(x, y);
        }

        if (evt.getNewValue() instanceof ClienteDTO.CambiarAnimacionPlayer)
        {
            int numAnimacion = ((ClienteDTO.CambiarAnimacionPlayer) evt.getNewValue()).numAnimacion;
            setAnimacion(numAnimacion);
        }

        if (evt.getNewValue() instanceof ActorDTO.EliminarPC)
        {   eliminar(); }
    }
}
